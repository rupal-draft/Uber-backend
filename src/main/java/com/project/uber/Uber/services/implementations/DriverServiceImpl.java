package com.project.uber.Uber.services.implementations;

import com.project.uber.Uber.dto.DriverDto;
import com.project.uber.Uber.dto.RideDto;
import com.project.uber.Uber.dto.RideStartDto;
import com.project.uber.Uber.dto.RiderDto;
import com.project.uber.Uber.entities.Driver;
import com.project.uber.Uber.entities.Ride;
import com.project.uber.Uber.entities.RideRequest;
import com.project.uber.Uber.entities.enums.RideRequestStatus;
import com.project.uber.Uber.entities.enums.RideStatus;
import com.project.uber.Uber.exceptions.ResourceNotFoundException;
import com.project.uber.Uber.exceptions.RuntimeConflictException;
import com.project.uber.Uber.repositories.DriverRepository;
import com.project.uber.Uber.services.DriverService;
import com.project.uber.Uber.services.RideRequestService;
import com.project.uber.Uber.services.RideService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    private final RideRequestService rideRequestService;
    private final DriverRepository driverRepository;
    private final RideService rideService;
    private final ModelMapper modelMapper;

    public DriverServiceImpl(RideRequestService rideRequestService, DriverRepository driverRepository, RideService rideService, ModelMapper modelMapper) {
        this.rideRequestService = rideRequestService;
        this.driverRepository = driverRepository;
        this.rideService = rideService;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public RideDto acceptRide(Long rideRequestId) {

        RideRequest rideRequest = rideRequestService.findRideRequestById(rideRequestId);
        Driver driver = getCurrentDriver();

        validateRequest(rideRequest, driver);

        driver.setAvailable(false);
        Driver savedDriver = driverRepository.save(driver);

        Ride ride = rideService.createNewRide(rideRequest, savedDriver);

        return modelMapper.map(ride, RideDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    @Transactional
    public RideDto startRide(Long rideId, RideStartDto rideStartDto) {

        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();

        validateRide(ride, driver, rideStartDto.getOtp());

        ride.setStartedAt(LocalDateTime.now());
        Ride savedRide = rideService.updateRideStatus(ride , RideStatus.ONGOING);

        return modelMapper.map(savedRide, RideDto.class);
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

    @Override
    public Driver getCurrentDriver() {
        return driverRepository
                .findById(2L)
                .orElseThrow(()-> new ResourceNotFoundException("No driver was found!"));
    }

    private void validateRequest(RideRequest rideRequest, Driver driver) {
        if(!rideRequest.getStatus().equals(RideRequestStatus.PENDING)) {
            throw new RuntimeConflictException("Ride request is not pending");
        }
        if (!driver.getAvailable()){
            throw new RuntimeConflictException("Driver is not available");
        }
    }

    private void validateRide(Ride ride, Driver driver, String otp) {

        if(!ride.getStatus().equals(RideStatus.CONFIRMED)) {
            throw new RuntimeConflictException("Ride is not confirmed");
        }

        if(!driver.equals(ride.getDriver())) {
            throw new RuntimeConflictException("Driver cannot start own ride");
        }

        if(!otp.equals(ride.getOtp())) {
            throw new RuntimeConflictException("Invalid OTP");
        }
    }

}
