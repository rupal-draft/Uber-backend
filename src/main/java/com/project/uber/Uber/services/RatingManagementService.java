package com.project.uber.Uber.services;

import com.project.uber.Uber.dto.DriverDto;
import com.project.uber.Uber.dto.RatingDto;
import com.project.uber.Uber.dto.RiderDto;

public interface RatingManagementService {
    DriverDto rateDriver(RatingDto rating);
    RiderDto rateRider(RatingDto rating);
}
