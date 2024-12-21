package com.project.uber.Uber.strategies;


import com.project.uber.Uber.entities.RideRequest;

public interface RideFareCalculation {

    double RIDE_FARE_MULTIPLIER = 10;

    double calculateFare(RideRequest rideRequest);
}
