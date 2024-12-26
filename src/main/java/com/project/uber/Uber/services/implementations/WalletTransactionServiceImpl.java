package com.project.uber.Uber.services.implementations;

import com.project.uber.Uber.entities.WalletTransaction;
import com.project.uber.Uber.repositories.WalletTransactionRepository;
import com.project.uber.Uber.services.WalletTransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class WalletTransactionServiceImpl implements WalletTransactionService {

    private final ModelMapper modelMapper;
    private final WalletTransactionRepository walletTransactionRepository;

    public WalletTransactionServiceImpl(ModelMapper modelMapper, WalletTransactionRepository walletTransactionRepository) {
        this.modelMapper = modelMapper;
        this.walletTransactionRepository = walletTransactionRepository;
    }

    @Override
    public void createNewWalletTransaction(WalletTransaction walletTransaction) {
        walletTransactionRepository.save(walletTransaction);
    }
}
