package com.project.uber.Uber.services;


import com.project.uber.Uber.dto.DriverDto;
import com.project.uber.Uber.dto.DriverRideDto;
import com.project.uber.Uber.dto.RideStartDto;
import com.project.uber.Uber.entities.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DriverService {

    DriverRideDto acceptRide(Long rideRequestId);

    DriverRideDto cancelRide(Long rideId);

    DriverRideDto startRide(Long rideId, RideStartDto rideStartDto);

    DriverRideDto endRide(Long rideId);

    DriverDto getDriverProfile();

    Page<DriverRideDto> getAllMyRides(Pageable pageRequest);

    Driver getCurrentDriver();

    Driver getDriverById(Long driverId);

    Driver updateRating(Driver driver, Double rating);

    Driver updateDriverAvailability(Driver driver, boolean available);

    Driver createNewDriver(Driver createDriver);
}
