package com.project.uber.Uber.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(indexes = {
        @Index(name = "idx_rating_rider", columnList = "rider_id"),
        @Index(name = "idx_rating_driver", columnList = "driver_id"),
        @Index(name = "idx_rating_ride", columnList = "ride_id")
})
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Ride ride;

    @ManyToOne
    private Rider rider;

    @ManyToOne
    private Driver driver;

    @NotNull(message = "Rating is required")
    @Min(value = 0, message = "Rating cannot be less than 0")
    @Max(value = 5, message = "Rating cannot be more than 5")
    private Double riderRating;

    @NotNull(message = "Rating is required")
    @Min(value = 0, message = "Rating cannot be less than 0")
    @Max(value = 5, message = "Rating cannot be more than 5")
    private Double driverRating;

    public Rating() {
    }

    public Rating(RatingBuilder builder) {
        this.id = builder.id;
        this.ride = builder.ride;
        this.rider = builder.rider;
        this.driver = builder.driver;
        this.riderRating = builder.riderRating;
        this.driverRating = builder.driverRating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public @NotNull(message = "Rating is required") @Min(value = 0, message = "Rating cannot be less than 0") @Max(value = 5, message = "Rating cannot be more than 5") Double getRiderRating() {
        return riderRating;
    }

    public void setRiderRating(@NotNull(message = "Rating is required") @Min(value = 0, message = "Rating cannot be less than 0") @Max(value = 5, message = "Rating cannot be more than 5") Double riderRating) {
        this.riderRating = riderRating;
    }

    public @NotNull(message = "Rating is required") @Min(value = 0, message = "Rating cannot be less than 0") @Max(value = 5, message = "Rating cannot be more than 5") Double getDriverRating() {
        return driverRating;
    }

    public void setDriverRating(@NotNull(message = "Rating is required") @Min(value = 0, message = "Rating cannot be less than 0") @Max(value = 5, message = "Rating cannot be more than 5") Double driverRating) {
        this.driverRating = driverRating;
    }

    public static class RatingBuilder {
        private Long id;
        private Ride ride;
        private Rider rider;
        private Driver driver;
        private Double riderRating;
        private Double driverRating;

        public RatingBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public RatingBuilder ride(Ride ride) {
            this.ride = ride;
            return this;
        }

        public RatingBuilder rider(Rider rider) {
            this.rider = rider;
            return this;
        }

        public RatingBuilder driver(Driver driver) {
            this.driver = driver;
            return this;
        }

        public RatingBuilder riderRating(@NotNull(message = "Rating is required")
                                         @Min(value = 0, message = "Rating cannot be less than 0")
                                         @Max(value = 5, message = "Rating cannot be more than 5") Double riderRating) {
            this.riderRating = riderRating;
            return this;
        }

        public RatingBuilder driverRating(@NotNull(message = "Rating is required")
                                          @Min(value = 0, message = "Rating cannot be less than 0")
                                          @Max(value = 5, message = "Rating cannot be more than 5") Double driverRating) {
            this.driverRating = driverRating;
            return this;
        }

        public Rating build() {
            return new Rating(this);
        }
    }
}
