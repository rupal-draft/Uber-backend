package com.project.uber.Uber.repositories;

import com.project.uber.Uber.entities.Driver;
import com.project.uber.Uber.entities.Rating;
import com.project.uber.Uber.entities.Ride;
import com.project.uber.Uber.entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByRider(Rider rider);
    List<Rating> findByDriver(Driver driver);

    Optional<Rating> findByRide(Ride ride);
}