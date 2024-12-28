package com.project.uber.Uber.services.implementations;

import com.project.uber.Uber.dto.DriverDto;
import com.project.uber.Uber.dto.RideDto;
import com.project.uber.Uber.dto.RideRequestDto;
import com.project.uber.Uber.dto.RiderDto;
import com.project.uber.Uber.entities.*;
import com.project.uber.Uber.entities.enums.RideRequestStatus;
import com.project.uber.Uber.entities.enums.RideStatus;
import com.project.uber.Uber.exceptions.ResourceNotFoundException;
import com.project.uber.Uber.exceptions.RuntimeConflictException;
import com.project.uber.Uber.repositories.RideRequestRepository;
import com.project.uber.Uber.repositories.RiderRepository;
import com.project.uber.Uber.services.*;
import com.project.uber.Uber.strategies.mangers.DriverMatchingStrategyManager;
import com.project.uber.Uber.strategies.mangers.RideFareCalculationStrategyManager;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RiderServiceImpl implements RiderService {

    private final Logger log = Logger.getLogger(RiderServiceImpl.class);

    private final ModelMapper modelMapper;
    private final RideRequestRepository rideRequestRepository;
    private final DriverMatchingStrategyManager driverMatchingStrategyManager;
    private final RideFareCalculationStrategyManager rideFareCalculationStrategyManager;
    private final RiderRepository riderRepository;
    private final RideService rideService;
    private final DriverService driverService;
    private final RatingManagementService ratingManagementService;
    private final NotificationService notificationService;

    public RiderServiceImpl(ModelMapper modelMapper, RideRequestRepository rideRequestRepository, RatingManagementService ratingManagementService, DriverMatchingStrategyManager driverMatchingStrategyManager, NotificationService notificationService, RiderRepository riderRepository, RideService rideService, DriverService driverService, RideFareCalculationStrategyManager rideFareCalculationStrategyManager) {
        this.modelMapper = modelMapper;
        this.rideRequestRepository = rideRequestRepository;
        this.driverMatchingStrategyManager = driverMatchingStrategyManager;
        this.riderRepository = riderRepository;
        this.rideService = rideService;
        this.driverService = driverService;
        this.rideFareCalculationStrategyManager = rideFareCalculationStrategyManager;
        this.ratingManagementService = ratingManagementService;
        this.notificationService = notificationService;
    }

    @Override
    @Transactional
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {

        Rider rider = getCurrentRider();
        RideRequest rideRequest = modelMapper.map(rideRequestDto,RideRequest.class);
        rideRequest.setStatus(RideRequestStatus.PENDING);

        Double fare = rideFareCalculationStrategyManager
                .rideFareCalculation()
                .calculateFare(rideRequest);
        rideRequest.setFare(fare);

        rideRequest.setRider(rider);
        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);

        List<Driver> matchingDrivers = driverMatchingStrategyManager
                .driverMatchingStrategy(rider.getRating())
                .findMatchingDrivers(rideRequest);
        String[] emails = matchingDrivers.stream()
                .map(driver -> driver.getUser().getEmail())
                .toArray(String[]::new);
        notificationService.sendEmail(emails, "New Ride Request", "A new ride request is available. Please check your app.");

        return modelMapper.map(savedRideRequest,RideRequestDto.class);

    }

    @Override
    public RideDto cancelRide(Long rideId) {
        Ride ride = rideService.getRideById(rideId);
        Rider rider = getCurrentRider();

        validateRide(ride, rider, RideStatus.CONFIRMED);

        driverService.updateDriverAvailability(ride.getDriver(), true);
        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.CANCELLED);

        return modelMapper.map(savedRide,RideDto.class);
    }

    @Override
    public RiderDto getRiderProfile() {
        Rider rider = getCurrentRider();
        return modelMapper.map(rider,RiderDto.class);
    }

    @Override
    public Page<RideDto> getAllMyRides(Pageable pageRequest) {
        Rider rider = getCurrentRider();
        return rideService
                .getAllRidesOfRider(rider,pageRequest)
                .map(ride -> modelMapper.map(ride,RideDto.class));
    }

    @Override
    public DriverDto rateDriver(Long rideId, Double rating) {

        Ride ride = rideService.getRideById(rideId);
        Rider rider = getCurrentRider();

        validateRide(ride,rider,RideStatus.ENDED);

        DriverDto driverDto = ratingManagementService.rateDriver(ride, ride.getDriver(), rating);

        return driverDto;
    }

    @Override
    public void createNewRider(User savedUser) {
        Rider rider = new Rider
                .RiderBuilder()
                .setUser(savedUser)
                .setRating(0.0)
                .build();
        riderRepository.save(rider);
    }

    @Override
    public Rider getCurrentRider() {

        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return riderRepository
                .findByUser(user)
                .orElseThrow(()-> new AuthenticationServiceException("No Rider was found with ID: "+1l));
    }

    @Override
    public Rider getRiderById(Long id) {
        return riderRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No Rider was found with ID: "+id));
    }

    @Override
    public Rider updateRating(Rider rider, Double rating) {
        rider.setRating(rating);
        Rider savedRider = riderRepository.save(rider);
        return savedRider;
    }

    public static void validateRide(Ride ride, Rider rider, RideStatus expectedStatus) {
        if (ride == null) {
            throw new ResourceNotFoundException("Ride cannot be null.");
        }
        if (rider == null) {
            throw new ResourceNotFoundException("Rider cannot be null.");
        }
        if (!ride.getStatus().equals(expectedStatus)) {
            throw new RuntimeConflictException(
                    String.format("Invalid ride status! Expected: %s, Found: %s", expectedStatus, ride.getStatus())
            );
        }
        if (ride.getRider() == null || !ride.getRider().equals(rider)) {
            throw new RuntimeConflictException("The provided rider does not own this ride.");
        }
    }

}
