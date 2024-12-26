package com.project.uber.Uber.services.implementations;

import com.project.uber.Uber.dto.DriverDto;
import com.project.uber.Uber.dto.DriverRideDto;
import com.project.uber.Uber.dto.RideStartDto;
import com.project.uber.Uber.entities.Driver;
import com.project.uber.Uber.entities.Ride;
import com.project.uber.Uber.entities.RideRequest;
import com.project.uber.Uber.entities.enums.RideRequestStatus;
import com.project.uber.Uber.entities.enums.RideStatus;
import com.project.uber.Uber.exceptions.ResourceNotFoundException;
import com.project.uber.Uber.exceptions.RuntimeConflictException;
import com.project.uber.Uber.repositories.DriverRepository;
import com.project.uber.Uber.services.DriverService;
import com.project.uber.Uber.services.PaymentService;
import com.project.uber.Uber.services.RideRequestService;
import com.project.uber.Uber.services.RideService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class DriverServiceImpl implements DriverService {

    private final RideRequestService rideRequestService;
    private final DriverRepository driverRepository;
    private final RideService rideService;
    private final ModelMapper modelMapper;
    private final PaymentService paymentService;

    public DriverServiceImpl(RideRequestService rideRequestService, DriverRepository driverRepository, RideService rideService, ModelMapper modelMapper, PaymentService paymentService) {
        this.rideRequestService = rideRequestService;
        this.driverRepository = driverRepository;
        this.rideService = rideService;
        this.modelMapper = modelMapper;
        this.paymentService = paymentService;
    }

    @Override
    @Transactional
    public DriverRideDto acceptRide(Long rideRequestId) {

        RideRequest rideRequest = rideRequestService.findRideRequestById(rideRequestId);
        Driver driver = getCurrentDriver();

        validateRequest(rideRequest, driver);

        Driver savedDriver = updateDriverAvailability(driver,false);

        Ride ride = rideService.createNewRide(rideRequest, savedDriver);

        return modelMapper.map(ride, DriverRideDto.class);
    }

    @Override
    public DriverRideDto cancelRide(Long rideId) {

        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();

        validateRide(ride, driver, RideStatus.CONFIRMED);
        updateDriverAvailability(driver,true);

        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.CANCELLED);


        return modelMapper.map(savedRide, DriverRideDto.class);
    }

    @Override
    @Transactional
    public DriverRideDto startRide(Long rideId, RideStartDto rideStartDto) {

        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();

        validateRide(ride, driver, RideStatus.CONFIRMED);
        if(!rideStartDto.getOtp().equals(ride.getOtp())) {
            throw new RuntimeConflictException("Invalid OTP");
        }

        ride.setStartedAt(LocalDateTime.now());
        Ride savedRide = rideService.updateRideStatus(ride , RideStatus.ONGOING);

        paymentService.createNewPayment(savedRide);

        return modelMapper.map(savedRide, DriverRideDto.class);
    }

    @Override
    @Transactional
    public DriverRideDto endRide(Long rideId) {

        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();

        validateRide(ride, driver, RideStatus.ONGOING);

        ride.setEndedAt(LocalDateTime.now());
        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.ENDED);
        updateDriverAvailability(driver, true);
        paymentService.processPayment(savedRide);
        return modelMapper.map(savedRide, DriverRideDto.class);
    }

    @Override
    public DriverDto getDriverProfile() {
        Driver driver = getCurrentDriver();
        return modelMapper.map(driver, DriverDto.class);
    }

    @Override
    public Page<DriverRideDto> getAllMyRides(Pageable pageRequest) {
        Driver driver = getCurrentDriver();
        return rideService
                .getAllRidesOfDriver(driver,pageRequest)
                .map(ride -> modelMapper.map(ride, DriverRideDto.class));
    }

    @Override
    public Driver getCurrentDriver() {
        return driverRepository
                .findById(2L)
                .orElseThrow(()-> new ResourceNotFoundException("No driver was found!"));
    }

    @Override
    public Driver getDriverById(Long driverId) {
        return driverRepository
                .findById(driverId)
                .orElseThrow(()-> new ResourceNotFoundException("No driver was found with ID: "+driverId));
    }

    @Override
    public Driver updateRating(Driver driver, Double rating) {
        driver.setRating(rating);
        Driver savedDriver = driverRepository.save(driver);
        return savedDriver;
    }

    @Override
    public Driver updateDriverAvailability(Driver driver, boolean available) {
        driver.setAvailable(available);
        Driver savedDriver = driverRepository.save(driver);
        return savedDriver;
    }

    @Override
    public Driver createNewDriver(Driver createDriver) {
        return driverRepository.save(createDriver);
    }

    private void validateRequest(RideRequest rideRequest, Driver driver) {
        if(!rideRequest.getStatus().equals(RideRequestStatus.PENDING)) {
            throw new RuntimeConflictException("Ride request is not pending");
        }
        if (!driver.getAvailable()){
            throw new RuntimeConflictException("Driver is not available");
        }
    }

    public static void validateRide(Ride ride, Driver driver, RideStatus expectedStatus) {
        if (ride == null) {
            throw new ResourceNotFoundException("Ride cannot be null.");
        }
        if (driver == null) {
            throw new ResourceNotFoundException("Driver cannot be null.");
        }
        if (!ride.getStatus().equals(expectedStatus)) {
            throw new RuntimeConflictException(
                    String.format("Invalid ride status! Expected: %s, Found: %s", expectedStatus, ride.getStatus())
            );
        }
        if (ride.getDriver() == null || !ride.getDriver().equals(driver)) {
            throw new RuntimeConflictException("The provided driver does not own this ride.");
        }
    }


}
