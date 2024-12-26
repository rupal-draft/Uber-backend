package com.project.uber.Uber.dto;

public class RiderDto {

    private Long id;
    private UserDto user;
    private Double rating;

    public RiderDto() {
    }

    public RiderDto(UserDto user, Double rating, Long id) {
        this.id = id;
        this.user = user;
        this.rating = rating;
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
}
