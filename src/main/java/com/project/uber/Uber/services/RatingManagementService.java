package com.project.uber.Uber.services;

import com.project.uber.Uber.dto.DriverDto;
import com.project.uber.Uber.dto.RiderDto;
import com.project.uber.Uber.entities.Driver;
import com.project.uber.Uber.entities.Ride;
import com.project.uber.Uber.entities.Rider;

public interface RatingManagementService {
    DriverDto rateDriver(Ride ride, Driver driver, Double rating);
    RiderDto rateRider(Ride ride, Rider rider, Double rating);
    void createNewRating(Ride ride);
}
