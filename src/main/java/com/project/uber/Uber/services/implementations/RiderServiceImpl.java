package com.project.uber.Uber.services.implementations;

import com.project.uber.Uber.dto.DriverDto;
import com.project.uber.Uber.dto.RideDto;
import com.project.uber.Uber.dto.RideRequestDto;
import com.project.uber.Uber.dto.RiderDto;
import com.project.uber.Uber.entities.RideRequest;
import com.project.uber.Uber.entities.Rider;
import com.project.uber.Uber.entities.User;
import com.project.uber.Uber.entities.enums.RideRequestStatus;
import com.project.uber.Uber.exceptions.ResourceNotFoundException;
import com.project.uber.Uber.repositories.RideRequestRepository;
import com.project.uber.Uber.repositories.RiderRepository;
import com.project.uber.Uber.services.RiderService;
import com.project.uber.Uber.strategies.StrategyManager;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RiderServiceImpl implements RiderService {

    private final Logger log = Logger.getLogger(RiderServiceImpl.class);

    private final ModelMapper modelMapper;
    private final RideRequestRepository rideRequestRepository;
    private final StrategyManager strategyManager;
    private final RiderRepository riderRepository;

    public RiderServiceImpl(ModelMapper modelMapper, RideRequestRepository rideRequestRepository, StrategyManager strategyManager, RiderRepository riderRepository) {
        this.modelMapper = modelMapper;
        this.rideRequestRepository = rideRequestRepository;
        this.strategyManager = strategyManager;
        this.riderRepository = riderRepository;
    }

    @Override
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {

        Rider rider = getCurrentRider();
        RideRequest rideRequest = modelMapper.map(rideRequestDto,RideRequest.class);
        rideRequest.setStatus(RideRequestStatus.PENDING);
        Double fare = strategyManager.rideFareCalculation().calculateFare(rideRequest);
        rideRequest.setFare(fare);
        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);
        strategyManager.driverMatchingStrategy(rider.getRating()).findMatchingDrivers(rideRequest);
        return modelMapper.map(savedRideRequest,RideRequestDto.class);

    }

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public DriverDto rateDriver(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public RiderDto getRiderProfile() {
        return null;
    }

    @Override
    public List<RideDto> getAllMyRides() {
        return List.of();
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
        return riderRepository
                .findById(1l)
                .orElseThrow(()-> new ResourceNotFoundException("No Rider was found with ID: "+1l));
    }
}
