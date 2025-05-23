package com.project.uber.Uber.strategies.implementations;

import com.project.uber.Uber.entities.Driver;
import com.project.uber.Uber.entities.RideRequest;
import com.project.uber.Uber.repositories.DriverRepository;
import com.project.uber.Uber.strategies.DriverMatchingStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverMatchingHighestRatedDriver implements DriverMatchingStrategy {

    private final DriverRepository driverRepository;

    public DriverMatchingHighestRatedDriver(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public List<Driver> findMatchingDrivers(RideRequest rideRequest) {
        return driverRepository.findTenNearbyTopRatedDrivers(rideRequest.getPickUpLocation());
    }
}
