package com.project.uber.Uber.strategies.implementations;

import com.project.uber.Uber.entities.Driver;
import com.project.uber.Uber.entities.RideRequest;
import com.project.uber.Uber.strategies.DriverMatchingStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverMatchingHighestRatedDriverImpl implements DriverMatchingStrategy {
    @Override
    public List<Driver> findMatchingDrivers(RideRequest rideRequest) {
        return List.of();
    }
}
