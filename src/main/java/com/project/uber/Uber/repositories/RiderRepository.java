package com.project.uber.Uber.repositories;

import com.project.uber.Uber.entities.Rider;
import com.project.uber.Uber.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RiderRepository extends JpaRepository<Rider,Long> {
    Optional<Rider> findByUser(User user);
}
