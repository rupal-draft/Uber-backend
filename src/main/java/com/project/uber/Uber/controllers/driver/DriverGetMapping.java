package com.project.uber.Uber.controllers.driver;

import com.project.uber.Uber.dto.DriverDto;
import com.project.uber.Uber.dto.DriverRideDto;
import com.project.uber.Uber.services.DriverService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/driver")
@Secured("ROLE_DRIVER")
public class DriverGetMapping {

    private final DriverService driverService;
    private final int PAGE_SIZE = 4;

    public DriverGetMapping(DriverService driverService) {
        this.driverService = driverService;
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
