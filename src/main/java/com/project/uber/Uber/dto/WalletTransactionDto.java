package com.project.uber.Uber.dto;

import com.project.uber.Uber.entities.enums.TransactionMethod;
import com.project.uber.Uber.entities.enums.TransactionType;

import java.time.LocalDateTime;

public class WalletTransactionDto {

    private Long id;
    private Double amount;
    private TransactionType transactionType;
    private TransactionMethod transactionMethod;
    private RideDto ride;
    private String transactionId;
    private WalletDto wallet;
    private LocalDateTime timestamp;

    public WalletTransactionDto() {
    }

    public WalletTransactionDto(WalletTransactionDtoBuilder builder) {
        this.id = builder.id;
        this.amount = builder.amount;
        this.transactionType = builder.transactionType;
        this.transactionMethod = builder.transactionMethod;
        this.ride = builder.ride;
        this.transactionId = builder.transactionId;
        this.wallet = builder.wallet;
        this.timestamp = builder.timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public TransactionMethod getTransactionMethod() {
        return transactionMethod;
    }

    public void setTransactionMethod(TransactionMethod transactionMethod) {
        this.transactionMethod = transactionMethod;
    }

    public RideDto getRide() {
        return ride;
    }

    public void setRide(RideDto ride) {
        this.ride = ride;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public WalletDto getWallet() {
        return wallet;
    }

    public void setWallet(WalletDto wallet) {
        this.wallet = wallet;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public static class WalletTransactionDtoBuilder {
        private Long id;
        private Double amount;
        private TransactionType transactionType;
        private TransactionMethod transactionMethod;
        private RideDto ride;
        private String transactionId;
        private WalletDto wallet;
        private LocalDateTime timestamp;

        public WalletTransactionDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public WalletTransactionDtoBuilder amount(Double amount) {
            this.amount = amount;
            return this;
        }

        public WalletTransactionDtoBuilder transactionType(TransactionType transactionType) {
            this.transactionType = transactionType;
            return this;
        }

        public WalletTransactionDtoBuilder transactionMethod(TransactionMethod transactionMethod) {
            this.transactionMethod = transactionMethod;
            return this;
        }

        public WalletTransactionDtoBuilder ride(RideDto ride) {
            this.ride = ride;
            return this;
        }

        public WalletTransactionDtoBuilder transactionId(String transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        public WalletTransactionDtoBuilder wallet(WalletDto wallet) {
            this.wallet = wallet;
            return this;
        }

        public WalletTransactionDtoBuilder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public WalletTransactionDto build() {
            return new WalletTransactionDto(this);
        }
    }
}
