package com.project.uber.Uber.dto;


import com.project.uber.Uber.entities.enums.PaymentMethod;
import com.project.uber.Uber.entities.enums.RideRequestStatus;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

public class RideRequestDto {

    private Long id;
    private Point pickUpLocation;
    private Point dropOffLocation;
    private LocalDateTime requestTime;
    private RiderDto rider;
    private PaymentMethod paymentMethod;
    private RideRequestStatus status;

    public RideRequestDto() {
    }

    public RideRequestDto(Long id, Point pickUpLocation, Point dropOffLocation, LocalDateTime requestTime, RiderDto rider, PaymentMethod paymentMethod, RideRequestStatus status) {
        this.id = id;
        this.pickUpLocation = pickUpLocation;
        this.dropOffLocation = dropOffLocation;
        this.requestTime = requestTime;
        this.rider = rider;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Point getPickUpLocation() {
        return pickUpLocation;
    }

    public void setPickUpLocation(Point pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
    }

    public Point getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(Point dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(LocalDateTime requestTime) {
        this.requestTime = requestTime;
    }

    public RiderDto getRider() {
        return rider;
    }

    public void setRider(RiderDto rider) {
        this.rider = rider;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public RideRequestStatus getStatus() {
        return status;
    }

    public void setStatus(RideRequestStatus status) {
        this.status = status;
    }
}
