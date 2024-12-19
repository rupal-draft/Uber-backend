package com.project.uber.Uber.services.implementations;

import com.project.uber.Uber.dto.DriverDto;
import com.project.uber.Uber.dto.RideDto;
import com.project.uber.Uber.dto.RiderDto;
import com.project.uber.Uber.services.DriverService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {
    @Override
    public RideDto acceptRide(Long rideId) {
        return null;
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public RideDto startRide(Long rideId) {
        return null;
    }

    @Override
    public RideDto endRide(Long rideId) {
        return null;
    }

    @Override
    public RiderDto rateRider(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public DriverDto getDriverProfile() {
        return null;
    }

    @Override
    public List<RideDto> getAllMyRides() {
        return List.of();
    }
}
