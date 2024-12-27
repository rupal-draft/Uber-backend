package com.project.uber.Uber.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.locationtech.jts.geom.Point;

@Entity
@Table(indexes = {
        @Index(name = "idx_driver_vehicle_id", columnList = "vehicleId"),
        @Index(name = "idx_driver_user", columnList = "user_id")
})
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    @NotNull(message = "User must be associated with the driver")
    private User user;

    @NotNull(message = "Rating is required")
    @Min(value = 0, message = "Rating cannot be less than 0")
    @Max(value = 5, message = "Rating cannot be more than 5")
    private Double rating;

    @NotNull(message = "Availability status is required")
    private Boolean available;

    @Column(columnDefinition = "Geometry(Point, 4326)")
    private Point currentLocation;

    @Positive(message = "Vehicle ID must be a positive number")
    private Long vehicleId;

    public Driver() {
    }

    public Driver(DriverBuilder builder) {
        this.id = builder.id;
        this.user = builder.user;
        this.rating = builder.rating;
        this.available = builder.available;
        this.currentLocation = builder.currentLocation;
        this.vehicleId = builder.vehicleId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Point getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Point currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public static class DriverBuilder {

        private Long id;
        private User user;
        private Double rating;
        private Boolean available;
        private Point currentLocation;
        private Long vehicleId;

        public DriverBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public DriverBuilder user(User user) {
            this.user = user;
            return this;
        }

        public DriverBuilder rating(Double rating) {
            this.rating = rating;
            return this;
        }

        public DriverBuilder available(Boolean available) {
            this.available = available;
            return this;
        }

        public DriverBuilder currentLocation(Point currentLocation) {
            this.currentLocation = currentLocation;
            return this;
        }

        public DriverBuilder vehicleId(Long vehicleId) {
            this.vehicleId = vehicleId;
            return this;
        }

        public Driver build() {
            return new Driver(this);
        }
    }
}
