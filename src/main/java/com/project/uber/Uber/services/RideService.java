package com.project.uber.Uber.services;

import com.project.uber.Uber.entities.Driver;
import com.project.uber.Uber.entities.Ride;
import com.project.uber.Uber.entities.RideRequest;
import com.project.uber.Uber.entities.Rider;
import com.project.uber.Uber.entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RideService {

    Ride getRideById(Long rideId);

    Ride createNewRide(RideRequest rideRequest , Driver driver);

    Ride updateRideStatus(Ride ride, RideStatus rideStatus);

    Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest);

    Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest);
}
