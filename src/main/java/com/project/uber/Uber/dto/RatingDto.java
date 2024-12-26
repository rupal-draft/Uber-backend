package com.project.uber.Uber.dto;

public class RatingDto {

    private Long rideId;
    private Double rating;

    public RatingDto() {
    }

    public RatingDto(Double rating, Long rideId) {
        this.rideId = rideId;
        this.rating = rating;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Long getRideId() {
        return rideId;
    }

    public void setRideId(Long rideId) {
        this.rideId = rideId;
    }
}
