package com.project.uber.Uber.entities;

import com.project.uber.Uber.entities.enums.PaymentMethod;
import com.project.uber.Uber.entities.enums.RideStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Entity
public class Ride {

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
    private LocalDateTime createdTime;

    @NotNull(message = "Rider is required")
    @ManyToOne(fetch = FetchType.LAZY)
    private Rider rider;

    @NotNull(message = "Driver is required")
    @ManyToOne(fetch = FetchType.LAZY)
    private Driver driver;

    @NotNull(message = "Payment method is required")
    @Enumerated(value = EnumType.STRING)
    private PaymentMethod paymentMethod;

    @NotNull(message = "Ride status is required")
    @Enumerated(value = EnumType.STRING)
    private RideStatus status;

    @NotNull(message = "Fare is required")
    @Positive(message = "Fare must be greater than zero")
    private Double fare;

    @NotBlank(message = "OTP is required")
    @Size(min = 4, max = 6, message = "OTP must be between 4 and 6 characters")
    private String otp;

    private LocalDateTime startedAt;
    private LocalDateTime endedAt;

    public Ride() {
    }

    public Ride(Long id, Point pickUpLocation, Point dropOffLocation, LocalDateTime createdTime, Rider rider, Driver driver, PaymentMethod paymentMethod, RideStatus status, Double fare, LocalDateTime startedAt, LocalDateTime endedAt, String otp) {
        this.id = id;
        this.pickUpLocation = pickUpLocation;
        this.dropOffLocation = dropOffLocation;
        this.createdTime = createdTime;
        this.rider = rider;
        this.driver = driver;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.fare = fare;
        this.otp = otp;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
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

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
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

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public RideStatus getStatus() {
        return status;
    }

    public void setStatus(RideStatus status) {
        this.status = status;
    }

    public Double getFare() {
        return fare;
    }

    public void setFare(Double fare) {
        this.fare = fare;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(LocalDateTime endedAt) {
        this.endedAt = endedAt;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
