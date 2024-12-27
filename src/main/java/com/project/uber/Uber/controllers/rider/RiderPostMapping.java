package com.project.uber.Uber.controllers.rider;

import com.project.uber.Uber.dto.DriverDto;
import com.project.uber.Uber.dto.RatingDto;
import com.project.uber.Uber.dto.RideDto;
import com.project.uber.Uber.dto.RideRequestDto;
import com.project.uber.Uber.services.RatingManagementService;
import com.project.uber.Uber.services.RiderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rider")
public class RiderPostMapping {

    private final RiderService riderService;

    public RiderPostMapping(RiderService riderService, RatingManagementService ratingManagementService) {
        this.riderService = riderService;
    }

    @PostMapping("/requestRide")
    public ResponseEntity<RideRequestDto> requestRide(@RequestBody RideRequestDto rideRequestDto){
        RideRequestDto requestedRide = riderService.requestRide(rideRequestDto);
        return ResponseEntity.ok(requestedRide);
    }

    @PostMapping("/cancelRide/{rideId}")
    public ResponseEntity<RideDto> cancelRide(@PathVariable Long rideId){
        return ResponseEntity.ok(riderService.cancelRide(rideId));
    }

    @PostMapping("/rateDriver/{rideId}")
    public ResponseEntity<DriverDto> rateDriver(@PathVariable Long rideId, @RequestBody RatingDto ratingDto){
        return ResponseEntity.ok(riderService.rateDriver(rideId, ratingDto.getRating()));
    }
}
