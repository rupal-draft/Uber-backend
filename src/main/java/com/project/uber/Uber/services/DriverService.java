package com.project.uber.Uber.services;


import com.project.uber.Uber.dto.DriverDto;
import com.project.uber.Uber.dto.RideDto;
import com.project.uber.Uber.dto.RideStartDto;
import com.project.uber.Uber.entities.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface DriverService {

    RideDto acceptRide(Long rideRequestId);

    RideDto cancelRide(Long rideId);

    RideDto startRide(Long rideId, RideStartDto rideStartDto);

    RideDto endRide(Long rideId);

    DriverDto getDriverProfile();

    Page<RideDto> getAllMyRides(PageRequest pageRequest);

    Driver getCurrentDriver();

    Driver getDriverById(Long driverId);

    Driver updateRating(Driver driver, Double rating);

    Driver updateDriverAvailability(Driver driver, boolean available);
}
