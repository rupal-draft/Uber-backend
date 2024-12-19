package com.project.uber.Uber.strategies;

import com.project.uber.Uber.dto.RideRequestDto;

public interface RideFareCalculation {

    double calculateFare(RideRequestDto rideRequestDto);
}
