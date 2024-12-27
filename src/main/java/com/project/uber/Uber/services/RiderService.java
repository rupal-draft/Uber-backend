package com.project.uber.Uber.services;

import com.project.uber.Uber.dto.DriverDto;
import com.project.uber.Uber.dto.RideDto;
import com.project.uber.Uber.dto.RideRequestDto;
import com.project.uber.Uber.dto.RiderDto;
import com.project.uber.Uber.entities.Rider;
import com.project.uber.Uber.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RiderService {

    RideRequestDto requestRide(RideRequestDto rideRequestDto);

    RideDto cancelRide(Long rideId);

    RiderDto getRiderProfile();

    Page<RideDto> getAllMyRides(Pageable pageRequest);

    DriverDto rateDriver(Long rideId, Double rating);

    void createNewRider(User savedUser);

    Rider getCurrentRider();

    Rider getRiderById(Long id);

    Rider updateRating(Rider rider, Double rating);
}
