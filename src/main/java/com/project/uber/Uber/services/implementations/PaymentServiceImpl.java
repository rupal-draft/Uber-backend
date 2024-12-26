package com.project.uber.Uber.services.implementations;

import com.project.uber.Uber.entities.Payment;
import com.project.uber.Uber.entities.Ride;
import com.project.uber.Uber.entities.enums.PaymentStatus;
import com.project.uber.Uber.exceptions.ResourceNotFoundException;
import com.project.uber.Uber.repositories.PaymentRepository;
import com.project.uber.Uber.services.PaymentService;
import com.project.uber.Uber.strategies.mangers.PaymentStrategyManager;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentStrategyManager paymentStrategyManager;

    public PaymentServiceImpl(PaymentRepository paymentRepository, PaymentStrategyManager paymentStrategyManager) {
        this.paymentRepository = paymentRepository;
        this.paymentStrategyManager = paymentStrategyManager;
    }

    @Override
    public void processPayment(Ride ride) {

        Payment payment = paymentRepository.findByRide(ride).orElseThrow(()-> new ResourceNotFoundException("Payment not found!"));

        paymentStrategyManager
                .paymentStrategy(payment.getPaymentMethod())
                .processPayment(payment);

        updatePaymentStatus(payment, PaymentStatus.CONFIRMED);
    }

    @Override
    public Payment createNewPayment(Ride ride) {

        Payment payment = new Payment
                .PaymentBuilder()
                .amount(ride.getFare())
                .paymentMethod(ride.getPaymentMethod())
                .paymentStatus(PaymentStatus.PENDING)
                .ride(ride)
                .build();

        return paymentRepository.save(payment);
    }

    @Override
    public void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus) {
        payment.setPaymentStatus(paymentStatus);
        paymentRepository.save(payment);
    }
}
