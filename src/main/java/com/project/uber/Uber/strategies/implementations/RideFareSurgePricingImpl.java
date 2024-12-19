package com.project.uber.Uber.strategies.implementations;

import com.project.uber.Uber.entities.RideRequest;
import com.project.uber.Uber.strategies.RideFareCalculation;
import org.springframework.stereotype.Service;

@Service
public class RideFareSurgePricingImpl implements RideFareCalculation {
    @Override
    public double calculateFare(RideRequest rideRequest) {
        return 0;
    }
}
