package com.project.uber.Uber.strategies;

import com.project.uber.Uber.dto.RideRequestDto;
import com.project.uber.Uber.entities.Driver;

import java.util.List;

public interface DriverMatchingStrategy {

    List<Driver> findMatchingDrivers(RideRequestDto rideRequestDto);
}
