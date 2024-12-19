package com.project.uber.Uber.strategies;

import com.project.uber.Uber.entities.Driver;
import com.project.uber.Uber.entities.RideRequest;

import java.util.List;

public interface DriverMatchingStrategy {

    List<Driver> findMatchingDrivers(RideRequest rideRequestDto);
}
