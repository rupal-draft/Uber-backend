package com.project.uber.Uber.services;


import com.project.uber.Uber.dto.DriverDto;
import com.project.uber.Uber.dto.RideDto;
import com.project.uber.Uber.dto.RiderDto;

import java.util.List;

public interface DriverService {

    RideDto acceptRide(Long rideId);

    RideDto cancelRide(Long rideId);

    RideDto startRide(Long rideId);

    RideDto endRide(Long rideId);

    RiderDto rateRider(Long rideId, Integer rating);

    DriverDto getDriverProfile();

    List<RideDto> getAllMyRides();

}
