package com.project.uber.Uber.entities;

import com.project.uber.Uber.entities.enums.PaymentMethod;
import com.project.uber.Uber.entities.enums.RideRequestStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Entity
public class RideRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "Geometry(Point, 4326)")
    private Point pickUpLocation;
    @Column(columnDefinition = "Geometry(Point, 4326)")
    private Point dropOffLocation;

    @CreationTimestamp
    private LocalDateTime requestTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private Rider rider;

    @Enumerated(value = EnumType.STRING)
    private PaymentMethod paymentMethod;
    @Enumerated(value = EnumType.STRING)
    private RideRequestStatus status;

    public RideRequest() {
    }

    public RideRequest(Long id, Point pickUpLocation, Point dropOffLocation, LocalDateTime requestTime, Rider rider, PaymentMethod paymentMethod, RideRequestStatus status) {
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
}
