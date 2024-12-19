package com.project.uber.Uber.controllers;

import com.project.uber.Uber.dto.RideRequestDto;
import com.project.uber.Uber.services.RiderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rider")
public class RiderController {

    private final RiderService riderService;

    public RiderController(RiderService riderService) {
        this.riderService = riderService;
    }

    @PostMapping("/requestRide")
    public ResponseEntity<RideRequestDto> requestRide(@RequestBody RideRequestDto rideRequestDto){
        RideRequestDto requestedRide = riderService.requestRide(rideRequestDto);
        return ResponseEntity.ok(requestedRide);
    }
}
