package com.project.uber.Uber.services.implementations;


import com.project.uber.Uber.entities.Driver;
import com.project.uber.Uber.entities.Ride;
import com.project.uber.Uber.entities.RideRequest;
import com.project.uber.Uber.entities.Rider;
import com.project.uber.Uber.entities.enums.RideRequestStatus;
import com.project.uber.Uber.entities.enums.RideStatus;
import com.project.uber.Uber.exceptions.ResourceNotFoundException;
import com.project.uber.Uber.repositories.RideRepository;
import com.project.uber.Uber.services.NotificationService;
import com.project.uber.Uber.services.RideRequestService;
import com.project.uber.Uber.services.RideService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.project.uber.Uber.utils.GenerateOtp.generateOtp;

@Service
public class RideServiceImpl implements RideService {

    private final ModelMapper modelMapper;
    private final RideRepository rideRepository;
    private final RideRequestService rideRequestService;
    private final NotificationService notificationService;

    public RideServiceImpl(ModelMapper modelMapper, RideRepository rideRepository, RideRequestService rideRequestService, NotificationService notificationService) {
        this.modelMapper = modelMapper;
        this.rideRepository = rideRepository;
        this.rideRequestService = rideRequestService;
        this.notificationService = notificationService;
    }

    @Override
    public Ride getRideById(Long rideId) {
        return rideRepository
                .findById(rideId)
                .orElseThrow(()-> new ResourceNotFoundException("No ride was found with ID: "+rideId));
    }

    @Override
    public Ride createNewRide(RideRequest rideRequest, Driver driver) {

        rideRequest.setStatus(RideRequestStatus.CONFIRMED);
        Ride ride = modelMapper.map(rideRequest,Ride.class);
        ride.setStatus(RideStatus.CONFIRMED);
        ride.setDriver(driver);
        ride.setOtp(generateOtp());
        ride.setId(null);
        rideRequestService.updateRideRequest(rideRequest);
        Ride savedRide = rideRepository.save(ride);
        sendOtpEmailToRider(savedRide
                .getRider()
                .getUser()
                .getEmail(), savedRide
                .getOtp());

        return savedRide;
    }

    @Override
    public Ride updateRideStatus(Ride ride, RideStatus rideStatus) {
        ride.setStatus(rideStatus);
        return rideRepository.save(ride);
    }

    @Override
    public Page<Ride> getAllRidesOfRider(Rider rider, Pageable pageRequest) {
        return rideRepository.findByRider(rider,pageRequest);
    }

    @Override
    public Page<Ride> getAllRidesOfDriver(Driver driver, Pageable pageRequest) {
        return rideRepository.findByDriver(driver,pageRequest);
    }

    private void sendOtpEmailToRider(String riderEmail, String otp) {
        String subject = "Your Ride OTP";
        String body = "Dear Rider,\n\nYour OTP for the confirmed ride is: " + otp +
                "\n\nPlease share this OTP only with your driver upon pickup.\n\nThank you!";
        notificationService.sendEmail(riderEmail, subject, body);
    }

}
