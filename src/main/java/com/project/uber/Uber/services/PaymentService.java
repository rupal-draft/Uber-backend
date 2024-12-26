package com.project.uber.Uber.services;

import com.project.uber.Uber.entities.Payment;
import com.project.uber.Uber.entities.Ride;
import com.project.uber.Uber.entities.enums.PaymentStatus;

public interface PaymentService {

    void processPayment(Ride ride);

    Payment createNewPayment(Ride ride);

    void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus);
}
