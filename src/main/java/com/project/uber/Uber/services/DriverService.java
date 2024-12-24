package com.project.uber.Uber.services;


import com.project.uber.Uber.dto.DriverDto;
import com.project.uber.Uber.dto.RideDto;
import com.project.uber.Uber.dto.RideStartDto;
import com.project.uber.Uber.dto.RiderDto;
import com.project.uber.Uber.entities.Driver;

import java.util.List;

public interface DriverService {

    RideDto acceptRide(Long rideRequestId);

    RideDto cancelRide(Long rideId);

    RideDto startRide(Long rideId, RideStartDto rideStartDto);

    RideDto endRide(Long rideId);

    RiderDto rateRider(Long rideId, Integer rating);

    DriverDto getDriverProfile();

    List<RideDto> getAllMyRides();

    Driver getCurrentDriver();

}
