package com.project.uber.Uber.controllers.driver;

import com.project.uber.Uber.dto.DriverRideDto;
import com.project.uber.Uber.dto.RatingDto;
import com.project.uber.Uber.dto.RideStartDto;
import com.project.uber.Uber.dto.RiderDto;
import com.project.uber.Uber.services.DriverService;
import com.project.uber.Uber.services.RatingManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/driver")
public class DriverPostMapping {

    private final DriverService driverService;


    public DriverPostMapping(DriverService driverService, RatingManagementService ratingManagementService) {
        this.driverService = driverService;
    }

    @PostMapping("/acceptRide/{rideRequestId}")
    public ResponseEntity<DriverRideDto> acceptRide(@PathVariable Long rideRequestId) {
        return ResponseEntity.ok(driverService.acceptRide(rideRequestId));
    }

    @PostMapping("/startRide/{rideId}")
    public ResponseEntity<DriverRideDto> startRide(@PathVariable Long rideId, @RequestBody RideStartDto rideStartDto) {
        return ResponseEntity.ok(driverService.startRide(rideId, rideStartDto));
    }

    @PostMapping("/endRide/{rideId}")
    public ResponseEntity<DriverRideDto> endRide(@PathVariable Long rideId) {
        return ResponseEntity.ok(driverService.endRide(rideId));
    }

    @PostMapping("/cancelRide/{rideId}")
    public ResponseEntity<DriverRideDto> cancelRide(@PathVariable Long rideId){
        return ResponseEntity.ok(driverService.cancelRide(rideId));
    }

    @PostMapping("/rateRider/{rideId}")
    public ResponseEntity<RiderDto> rateRider(@PathVariable Long rideId, @RequestBody RatingDto ratingDto){
        return ResponseEntity.ok(driverService.rateRider(rideId, ratingDto.getRating()));
    }
}
