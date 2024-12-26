package com.project.uber.Uber.strategies;

import com.project.uber.Uber.entities.Payment;

public interface PaymentStrategy {
    Double PLATFORM_FEE = 0.3;
    void processPayment(Payment payment);
}
