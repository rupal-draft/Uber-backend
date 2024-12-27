package com.project.uber.Uber.controllers.rider;

import com.project.uber.Uber.dto.*;
import com.project.uber.Uber.services.RiderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rider")
public class RiderGetMapping {

    private final RiderService riderService;
    private final int PAGE_SIZE = 4;

    public RiderGetMapping(RiderService riderService) {
        this.riderService = riderService;
    }

    @GetMapping("/getMyProfile")
    public ResponseEntity<RiderDto> getRiderProfile(){
        return ResponseEntity.ok(riderService.getRiderProfile());
    }

    @GetMapping("/getMyRides")
    public ResponseEntity<List<RideDto>> getAllMyRides(@RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "1") Integer pageNumber){
        Pageable pageRequest = PageRequest.of(pageNumber,PAGE_SIZE, Sort.by(sortBy).ascending());
        Page<RideDto> rides = riderService.getAllMyRides(pageRequest);
        return ResponseEntity.ok(rides.getContent());
    }
}
