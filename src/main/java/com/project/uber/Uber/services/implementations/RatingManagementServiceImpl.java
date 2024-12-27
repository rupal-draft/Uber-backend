package com.project.uber.Uber.services.implementations;

import com.project.uber.Uber.dto.DriverDto;
import com.project.uber.Uber.dto.RiderDto;
import com.project.uber.Uber.entities.Driver;
import com.project.uber.Uber.entities.Rating;
import com.project.uber.Uber.entities.Ride;
import com.project.uber.Uber.entities.Rider;
import com.project.uber.Uber.exceptions.ResourceNotFoundException;
import com.project.uber.Uber.exceptions.RuntimeConflictException;
import com.project.uber.Uber.repositories.DriverRepository;
import com.project.uber.Uber.repositories.RatingRepository;
import com.project.uber.Uber.repositories.RiderRepository;
import com.project.uber.Uber.services.RatingManagementService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
public class RatingManagementServiceImpl implements RatingManagementService {


    private final RatingRepository ratingRepository;
    private final DriverRepository driverRepository;
    private final RiderRepository riderRepository;
    private final ModelMapper modelMapper;

    public RatingManagementServiceImpl(RatingRepository ratingRepository, ModelMapper modelMapper, DriverRepository driverRepository, RiderRepository riderRepository) {
        this.ratingRepository = ratingRepository;
        this.driverRepository = driverRepository;
        this.riderRepository = riderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DriverDto rateDriver(Ride ride, Driver driver, Double rating) {
        Rating ratingObj = ratingRepository
                .findByRide(ride)
                .orElseThrow(()-> new ResourceNotFoundException("Rating not found!"));

        if(ratingObj.getDriverRating() != null) throw new RuntimeConflictException("Cannot rate driver again!");

        ratingObj.setDriverRating(rating);
        ratingRepository.save(ratingObj);

        Double newRating = ratingRepository
                .findByDriver(driver)
                .stream()
                .mapToDouble(rating1 -> rating1.getDriverRating())
                .average()
                .orElse(0.0);
        driver.setRating(newRating);
        Driver driverSaved = driverRepository.save(driver);
        return modelMapper.map(driverSaved, DriverDto.class);
    }

    @Override
    public RiderDto rateRider(Ride ride, Rider rider, Double rating) {

        Rating ratingObj = ratingRepository
                .findByRide(ride)
                .orElseThrow(()-> new ResourceNotFoundException("Rating not found!"));

        if(ratingObj.getRiderRating() != null) throw new RuntimeConflictException("Cannot rate rider again!");

        ratingObj.setRiderRating(rating);
        ratingRepository.save(ratingObj);

        Double newRating = ratingRepository
                .findByRider(rider)
                .stream()
                .mapToDouble(rating1 -> rating1.getRiderRating())
                .average()
                .orElse(0.0);
        rider.setRating(newRating);
        Rider savedRider = riderRepository.save(rider);
        return modelMapper.map(savedRider, RiderDto.class);
    }

    @Override
    public void createNewRating(Ride ride) {
        Rating rating = new Rating
                .RatingBuilder()
                .ride(ride)
                .rider(ride.getRider())
                .driver(ride.getDriver())
                .build();
        ratingRepository.save(rating);
    }


}
