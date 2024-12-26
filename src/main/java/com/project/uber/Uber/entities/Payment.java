package com.project.uber.Uber.entities;

import com.project.uber.Uber.entities.enums.PaymentMethod;
import com.project.uber.Uber.entities.enums.PaymentStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @OneToOne(fetch = FetchType.LAZY)
    private Ride ride;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private Double amount;

    @CreationTimestamp
    private LocalDateTime paymentTime;

    public Payment() {
    }

    public Payment(PaymentBuilder builder) {
        this.id = builder.id;
        this.paymentMethod = builder.paymentMethod;
        this.ride = builder.ride;
        this.paymentStatus = builder.paymentStatus;
        this.amount = builder.amount;
        this.paymentTime = builder.paymentTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(LocalDateTime paymentTime) {
        this.paymentTime = paymentTime;
    }

    public static class PaymentBuilder {
        private Long id;
        private PaymentMethod paymentMethod;
        private Ride ride;
        private PaymentStatus paymentStatus;
        private Double amount;
        private LocalDateTime paymentTime;

        public PaymentBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PaymentBuilder paymentMethod(PaymentMethod paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public PaymentBuilder ride(Ride ride) {
            this.ride = ride;
            return this;
        }

        public PaymentBuilder paymentStatus(PaymentStatus paymentStatus) {
            this.paymentStatus = paymentStatus;
            return this;
        }

        public PaymentBuilder amount(Double amount) {
            this.amount = amount;
            return this;
        }

        public PaymentBuilder paymentTime(LocalDateTime paymentTime) {
            this.paymentTime = paymentTime;
            return this;
        }

        public Payment build() {
            return new Payment(this);
        }
    }
}
