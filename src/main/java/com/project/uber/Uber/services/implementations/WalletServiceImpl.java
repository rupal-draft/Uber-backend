package com.project.uber.Uber.services.implementations;

import com.project.uber.Uber.entities.Ride;
import com.project.uber.Uber.entities.User;
import com.project.uber.Uber.entities.Wallet;
import com.project.uber.Uber.entities.WalletTransaction;
import com.project.uber.Uber.entities.enums.TransactionMethod;
import com.project.uber.Uber.entities.enums.TransactionType;
import com.project.uber.Uber.exceptions.ResourceNotFoundException;
import com.project.uber.Uber.repositories.WalletRepository;
import com.project.uber.Uber.services.WalletService;
import com.project.uber.Uber.services.WalletTransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final WalletTransactionService walletTransactionService;

    public WalletServiceImpl(WalletRepository walletRepository, WalletTransactionService walletTransactionService) {
        this.walletRepository = walletRepository;
        this.walletTransactionService = walletTransactionService;
    }

    @Override
    @Transactional
    public Wallet addMoneyToWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod) {
        Wallet wallet = findByUser(user);
        Double prevAmount = wallet.getBalance();
        Double newAmount = prevAmount + amount;
        wallet.setBalance(newAmount);

        WalletTransaction walletTransaction = new WalletTransaction
                .WalletTransactionBuilder()
                .transactionId(transactionId)
                .transactionMethod(transactionMethod)
                .transactionType(TransactionType.CREDIT)
                .ride(ride)
                .wallet(wallet)
                .build();
        walletTransactionService.createNewWalletTransaction(walletTransaction);

        return walletRepository.save(wallet);
    }

    @Override
    @Transactional
    public Wallet subtractMoneyFromWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod) {
        Wallet wallet = findByUser(user);
        Double prevAmount = wallet.getBalance();
        Double newAmount = prevAmount - amount;
        wallet.setBalance(newAmount);

        WalletTransaction walletTransaction = new WalletTransaction
                .WalletTransactionBuilder()
                .transactionId(transactionId)
                .transactionMethod(transactionMethod)
                .transactionType(TransactionType.DEBIT)
                .ride(ride)
                .wallet(wallet)
                .build();

        wallet.getTransactions().add(walletTransaction);
        return walletRepository.save(wallet);
    }

    @Override
    public void fetchMoneyFromWallet() {

    }

    @Override
    public Wallet findWalletById(Long walletId) {
        return walletRepository
                .findById(walletId)
                .orElseThrow(()-> new ResourceNotFoundException("No wallet was found with ID: "+walletId));
    }

    @Override
    public Wallet findByUser(User user) {
        return walletRepository
                .findByUser(user)
                .orElseThrow(()-> new ResourceNotFoundException("No wallet was found with ID: "+user.getId()));
    }

    @Override
    public Wallet createNewWallet(User user) {
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        return walletRepository.save(wallet);
    }
}
