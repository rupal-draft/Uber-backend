package com.project.uber.Uber.services;

import com.project.uber.Uber.entities.RideRequest;

public interface RideRequestService {

    RideRequest findRideRequestById(Long id);

    void updateRideRequest(RideRequest rideRequest);
}
