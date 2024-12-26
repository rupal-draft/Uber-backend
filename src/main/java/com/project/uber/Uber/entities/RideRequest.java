package com.project.uber.Uber.entities;

import com.project.uber.Uber.entities.enums.PaymentMethod;
import com.project.uber.Uber.entities.enums.RideRequestStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Entity
public class RideRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Pick-up location is required")
    @Column(columnDefinition = "Geometry(Point, 4326)")
    private Point pickUpLocation;

    @NotNull(message = "Drop-off location is required")
    @Column(columnDefinition = "Geometry(Point, 4326)")
    private Point dropOffLocation;

    @CreationTimestamp
    private LocalDateTime requestTime;

    @NotNull(message = "Rider is required")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Rider rider;

    @NotNull(message = "Fare is required")
    @Positive(message = "Fare must be greater than zero")
    private Double fare;

    @NotNull(message = "Payment method is required")
    @Enumerated(value = EnumType.STRING)
    private PaymentMethod paymentMethod;

    @NotNull(message = "Request status is required")
    @Enumerated(value = EnumType.STRING)
    private RideRequestStatus status;

    public RideRequest() {
    }

    public RideRequest(Long id, Double fare, Point pickUpLocation, Point dropOffLocation, LocalDateTime requestTime, Rider rider, PaymentMethod paymentMethod, RideRequestStatus status) {
        this.id = id;
        this.pickUpLocation = pickUpLocation;
        this.dropOffLocation = dropOffLocation;
        this.requestTime = requestTime;
        this.rider = rider;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.fare = fare;
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

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
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

    public Double getFare() {
        return fare;
    }

    public void setFare(Double fare) {
        this.fare = fare;
    }
}
