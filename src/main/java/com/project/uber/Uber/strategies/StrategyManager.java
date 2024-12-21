package com.project.uber.Uber.strategies;

import com.project.uber.Uber.strategies.implementations.DriverMatchingHighestRatedDriver;
import com.project.uber.Uber.strategies.implementations.DriverMatchingNearestDrivers;
import com.project.uber.Uber.strategies.implementations.RideFareDefaultCalculation;
import com.project.uber.Uber.strategies.implementations.RideFareSurgePricing;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class StrategyManager {

    private final DriverMatchingHighestRatedDriver driverMatchingHighestRatedDriver;
    private final DriverMatchingNearestDrivers driverMatchingNearestDrivers;
    private final RideFareDefaultCalculation rideFareDefaultCalculation;
    private final RideFareSurgePricing rideFareSurgePricing;

    public StrategyManager(DriverMatchingHighestRatedDriver driverMatchingHighestRatedDriver, DriverMatchingNearestDrivers driverMatchingNearestDrivers, RideFareDefaultCalculation rideFareDefaultCalculation, RideFareSurgePricing rideFareSurgePricing) {
        this.driverMatchingHighestRatedDriver = driverMatchingHighestRatedDriver;
        this.driverMatchingNearestDrivers = driverMatchingNearestDrivers;
        this.rideFareDefaultCalculation = rideFareDefaultCalculation;
        this.rideFareSurgePricing = rideFareSurgePricing;
    }

    public DriverMatchingStrategy driverMatchingStrategy(double riderRating){

        if (riderRating > 4.8){
            return driverMatchingHighestRatedDriver;
        }
        else {
            return driverMatchingNearestDrivers;
        }
    }

    public RideFareCalculation rideFareCalculation(){

        LocalTime surgeStartTime = LocalTime.of(18,0);
        LocalTime surgeEndTime = LocalTime.of(21,0);
        LocalTime currentTime = LocalTime.now();

        boolean isSurge = currentTime.isAfter(surgeStartTime) && currentTime.isBefore(surgeEndTime);

        if (isSurge){
            return rideFareSurgePricing;
        }
        else {
            return rideFareDefaultCalculation;
        }
    }
}
