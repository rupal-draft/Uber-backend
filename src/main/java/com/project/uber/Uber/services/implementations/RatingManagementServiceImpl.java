package com.project.uber.Uber.services.implementations;

import com.project.uber.Uber.dto.DriverDto;
import com.project.uber.Uber.dto.RatingDto;
import com.project.uber.Uber.dto.RiderDto;
import com.project.uber.Uber.entities.Driver;
import com.project.uber.Uber.entities.Ride;
import com.project.uber.Uber.entities.Rider;
import com.project.uber.Uber.entities.enums.RideStatus;
import com.project.uber.Uber.services.DriverService;
import com.project.uber.Uber.services.RatingManagementService;
import com.project.uber.Uber.services.RideService;
import com.project.uber.Uber.services.RiderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
public class RatingManagementServiceImpl implements RatingManagementService {

    private final RiderService riderService;
    private final DriverService driverService;
    private final RideService rideService;
    private final ModelMapper modelMapper;

    public RatingManagementServiceImpl(RiderService riderService, DriverService driverService, RideService rideService, ModelMapper modelMapper) {
        this.riderService = riderService;
        this.driverService = driverService;
        this.rideService = rideService;
        this.modelMapper = modelMapper;
    }

    @Override
    public DriverDto rateDriver(RatingDto rating) {
        Ride ride = rideService.getRideById(rating.getRideId());
        Rider rider = riderService.getCurrentRider();

        RiderServiceImpl.validateRide(ride, rider, RideStatus.ENDED);

        Driver driver = driverService.updateRating(ride.getDriver(),rating.getRating());

        return modelMapper.map(driver,DriverDto.class);
    }

    @Override
    public RiderDto rateRider(RatingDto rating) {

        Ride ride = rideService.getRideById(rating.getRideId());
        Driver driver = driverService.getCurrentDriver();

        DriverServiceImpl.validateRide(ride, driver, RideStatus.ENDED);

        Rider rider = riderService.updateRating(ride.getRider(), rating.getRating());

        return modelMapper.map(rider, RiderDto.class);
    }
}
