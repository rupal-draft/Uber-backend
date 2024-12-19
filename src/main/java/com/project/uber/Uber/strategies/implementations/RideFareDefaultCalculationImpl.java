package com.project.uber.Uber.strategies.implementations;


import com.project.uber.Uber.entities.RideRequest;
import com.project.uber.Uber.services.DistanceCalculationService;
import com.project.uber.Uber.strategies.RideFareCalculation;
import org.springframework.stereotype.Service;

@Service
public class RideFareDefaultCalculationImpl implements RideFareCalculation {

    private final DistanceCalculationService distanceCalculationService;

    public RideFareDefaultCalculationImpl(DistanceCalculationService distanceCalculationService) {
        this.distanceCalculationService = distanceCalculationService;
    }

    @Override
    public double calculateFare(RideRequest rideRequest) {
        Double distance = distanceCalculationService.calculateDistance(rideRequest.getPickUpLocation(),rideRequest.getDropOffLocation());
        return distance*10;
    }
}
