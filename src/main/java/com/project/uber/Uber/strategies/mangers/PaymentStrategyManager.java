package com.project.uber.Uber.strategies.mangers;

import com.project.uber.Uber.entities.enums.PaymentMethod;
import com.project.uber.Uber.strategies.PaymentStrategy;
import com.project.uber.Uber.strategies.implementations.CashPaymentStrategy;
import com.project.uber.Uber.strategies.implementations.WalletPaymentStrategy;
import org.springframework.stereotype.Component;

@Component
public class PaymentStrategyManager {

    private final WalletPaymentStrategy walletPaymentStrategy;
    private final CashPaymentStrategy cashPaymentStrategy;

    public PaymentStrategyManager(WalletPaymentStrategy walletPaymentStrategy, CashPaymentStrategy cashPaymentStrategy) {
        this.walletPaymentStrategy = walletPaymentStrategy;
        this.cashPaymentStrategy = cashPaymentStrategy;
    }

    public PaymentStrategy paymentStrategy(PaymentMethod paymentMethod){
        return switch (paymentMethod){
            case CASH -> cashPaymentStrategy;
            case WALLET -> walletPaymentStrategy;
        };
    }
}
