package com.project.uber.Uber.strategies.implementations;

import com.project.uber.Uber.entities.Driver;
import com.project.uber.Uber.entities.Payment;
import com.project.uber.Uber.entities.enums.TransactionMethod;
import com.project.uber.Uber.services.WalletService;
import com.project.uber.Uber.strategies.PaymentStrategy;
import org.springframework.stereotype.Service;

@Service
public class CashPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;

    public CashPaymentStrategy(WalletService walletService) {
        this.walletService = walletService;
    }

    @Override
    public void processPayment(Payment payment) {

        Driver driver = payment.getRide().getDriver();
        double paymentCommission = payment.getAmount() * PLATFORM_FEE;

        walletService.subtractMoneyFromWallet(driver.getUser(),
                paymentCommission,
                null, // Cash on delivery payments do not have transaction ids
                payment.getRide(),
                TransactionMethod.RIDE);
    }
}
