package com.project.uber.Uber.strategies;


import com.project.uber.Uber.entities.RideRequest;

public interface RideFareCalculation {

    double calculateFare(RideRequest rideRequest);
}
