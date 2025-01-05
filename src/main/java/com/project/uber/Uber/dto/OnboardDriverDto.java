package com.project.uber.Uber.dto;

public class OnboardDriverDto {

    private Long vehicleId;
    private PointDto currentLocation;

    public OnboardDriverDto(Long vehicleId, PointDto currentLocation) {
        this.vehicleId = vehicleId;
        this.currentLocation = currentLocation;
    }

    public OnboardDriverDto() {
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public PointDto getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(PointDto currentLocation) {
        this.currentLocation = currentLocation;
    }
}
