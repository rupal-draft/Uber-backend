package com.project.uber.Uber.dto;

public class DriverDto {

    private UserDto user;
    private Double rating;

    public DriverDto() {
    }

    public DriverDto(UserDto user, Double rating) {
        this.user = user;
        this.rating = rating;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
