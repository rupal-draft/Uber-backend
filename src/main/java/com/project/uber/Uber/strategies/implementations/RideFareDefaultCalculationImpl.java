package com.project.uber.Uber.strategies.implementations;

import com.project.uber.Uber.dto.RideRequestDto;
import com.project.uber.Uber.strategies.RideFareCalculation;
import org.springframework.stereotype.Service;

@Service
public class RideFareDefaultCalculationImpl implements RideFareCalculation {
    @Override
    public double calculateFare(RideRequestDto rideRequestDto) {
        return 0;
    }
}
