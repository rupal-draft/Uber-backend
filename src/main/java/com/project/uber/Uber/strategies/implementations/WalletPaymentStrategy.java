package com.project.uber.Uber.strategies.implementations;

import com.project.uber.Uber.entities.Driver;
import com.project.uber.Uber.entities.Payment;
import com.project.uber.Uber.entities.Rider;
import com.project.uber.Uber.entities.enums.TransactionMethod;
import com.project.uber.Uber.services.WalletService;
import com.project.uber.Uber.strategies.PaymentStrategy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WalletPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;

    public WalletPaymentStrategy(WalletService walletService) {
        this.walletService = walletService;
    }

    @Override
    @Transactional
    public void processPayment(Payment payment) {

        Driver driver= payment.getRide().getDriver();
        Rider rider = payment.getRide().getRider();

        double paymentAddedToWallet = payment.getAmount() - (payment.getAmount() * PLATFORM_FEE);

        walletService.addMoneyToWallet(driver.getUser(),
                paymentAddedToWallet,
                null,
                payment.getRide(),
                TransactionMethod.RIDE);

        walletService.subtractMoneyFromWallet(rider.getUser(),
                payment.getAmount(),
                null,
                payment.getRide(),
                TransactionMethod.RIDE);

    }
}
