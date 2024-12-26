package com.project.uber.Uber.dto;

public class DriverDto {

    private Long id;
    private UserDto user;
    private Double rating;
    private Boolean available;
    private Long vehicleId;


    public DriverDto() {
    }

    public DriverDto(Long id, UserDto user, Double rating, Boolean available, Long vehicleId) {
        this.id = id;
        this.user = user;
        this.rating = rating;
        this.available = available;
        this.vehicleId = vehicleId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }
}
