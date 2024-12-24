package com.project.uber.Uber.services.implementations;

import com.project.uber.Uber.entities.RideRequest;
import com.project.uber.Uber.exceptions.ResourceNotFoundException;
import com.project.uber.Uber.repositories.RideRequestRepository;
import com.project.uber.Uber.services.RideRequestService;
import org.springframework.stereotype.Service;

@Service
public class RideRequestServiceImpl implements RideRequestService {

    private final RideRequestRepository rideRequestRepository;

    public RideRequestServiceImpl(RideRequestRepository rideRequestRepository) {
        this.rideRequestRepository = rideRequestRepository;
    }

    @Override
    public RideRequest findRideRequestById(Long id) {
        return rideRequestRepository
                .findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No ride request was found with ID: "+id));
    }

    @Override
    public void updateRideRequest(RideRequest rideRequest) {

        RideRequest request = rideRequestRepository
                .findById(rideRequest.getId())
                .orElseThrow(()-> new ResourceNotFoundException("No ride request was found with ID: "+rideRequest.getId()));

        rideRequestRepository.save(rideRequest);

    }
}
