package com.project.uber.Uber.services.implementations;

import com.project.uber.Uber.dto.DriverDto;
import com.project.uber.Uber.dto.RideDto;
import com.project.uber.Uber.dto.RideRequestDto;
import com.project.uber.Uber.dto.RiderDto;
import com.project.uber.Uber.entities.RideRequest;
import com.project.uber.Uber.entities.Rider;
import com.project.uber.Uber.entities.User;
import com.project.uber.Uber.entities.enums.RideRequestStatus;
import com.project.uber.Uber.repositories.RideRequestRepository;
import com.project.uber.Uber.repositories.RiderRepository;
import com.project.uber.Uber.services.RiderService;
import com.project.uber.Uber.strategies.DriverMatchingStrategy;
import com.project.uber.Uber.strategies.RideFareCalculation;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RiderServiceImpl implements RiderService {

    private final Logger log = Logger.getLogger(RiderServiceImpl.class);

    private final ModelMapper modelMapper;
    private final RideFareCalculation rideFareCalculation;
    private final RideRequestRepository rideRequestRepository;
    private final DriverMatchingStrategy driverMatchingStrategy;
    private final RiderRepository riderRepository;

    public RiderServiceImpl(ModelMapper modelMapper, RideFareCalculation rideFareCalculation, RideRequestRepository rideRequestRepository, DriverMatchingStrategy driverMatchingStrategy, RiderRepository riderRepository) {
        this.modelMapper = modelMapper;
        this.rideFareCalculation = rideFareCalculation;
        this.rideRequestRepository = rideRequestRepository;
        this.driverMatchingStrategy = driverMatchingStrategy;
        this.riderRepository = riderRepository;
    }

    @Override
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {

        RideRequest rideRequest = modelMapper.map(rideRequestDto,RideRequest.class);
        rideRequest.setStatus(RideRequestStatus.PENDING);
        Double fare = rideFareCalculation.calculateFare(rideRequest);
        rideRequest.setFare(fare);
        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);
        driverMatchingStrategy.findMatchingDrivers(rideRequest);
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
}
