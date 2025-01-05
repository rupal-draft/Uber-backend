package com.project.uber.Uber.services;

import com.project.uber.Uber.entities.Ride;
import com.project.uber.Uber.entities.User;
import com.project.uber.Uber.entities.Wallet;
import com.project.uber.Uber.entities.enums.TransactionMethod;

public interface WalletService {

    Wallet addMoneyToWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod);

    Wallet subtractMoneyFromWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod);

    Wallet findWalletById(Long walletId);

    Wallet findByUser(User user);

    Wallet createNewWallet(User user);
}
