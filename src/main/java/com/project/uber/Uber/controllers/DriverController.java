package com.project.uber.Uber.controllers;

import com.project.uber.Uber.dto.*;
import com.project.uber.Uber.services.DriverService;
import com.project.uber.Uber.services.RatingManagementService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/driver")
public class DriverController {

    private final DriverService driverService;
    private final RatingManagementService ratingManagementService;
    private final int PAGE_SIZE = 4;

    public DriverController(DriverService driverService, RatingManagementService ratingManagementService) {
        this.driverService = driverService;
        this.ratingManagementService = ratingManagementService;
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

    @PostMapping("/rateRider")
    public ResponseEntity<RiderDto> rateRider(@RequestBody RatingDto ratingDto){
        return ResponseEntity.ok(ratingManagementService.rateRider(ratingDto));
    }

    @GetMapping("/getMyProfile")
    public ResponseEntity<DriverDto> getDriverProfile(){
        return ResponseEntity.ok(driverService.getDriverProfile());
    }

    @GetMapping("/getMyRides")
    public ResponseEntity<List<DriverRideDto>> getAllMyRides(@RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "1") Integer pageNumber){
        Pageable pageRequest = PageRequest.of(pageNumber,PAGE_SIZE, Sort.by(sortBy).ascending());
        Page<DriverRideDto> rides = driverService.getAllMyRides(pageRequest);
        return ResponseEntity.ok(rides.getContent());
    }
}
