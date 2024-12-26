package com.project.uber.Uber.dto;

public class OnboardDriverDto {

    private Long vehicleId;

    public OnboardDriverDto(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public OnboardDriverDto() {
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }
}
