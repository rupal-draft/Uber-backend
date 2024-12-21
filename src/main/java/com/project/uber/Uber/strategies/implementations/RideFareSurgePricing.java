package com.project.uber.Uber.strategies.implementations;

import com.project.uber.Uber.entities.RideRequest;
import com.project.uber.Uber.services.DistanceCalculationService;
import com.project.uber.Uber.strategies.RideFareCalculation;
import org.springframework.stereotype.Service;

@Service
public class RideFareSurgePricing implements RideFareCalculation {

    private final DistanceCalculationService distanceCalculationService;

    public RideFareSurgePricing(DistanceCalculationService distanceCalculationService) {
        this.distanceCalculationService = distanceCalculationService;
    }

    private static final double SURGE_FACTOR = 2;

    @Override
    public double calculateFare(RideRequest rideRequest) {

        Double distance = distanceCalculationService.calculateDistance(rideRequest.getPickUpLocation(),rideRequest.getDropOffLocation());
        return distance * RIDE_FARE_MULTIPLIER * SURGE_FACTOR;

    }
}
