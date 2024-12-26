package com.project.uber.Uber.entities;

import com.project.uber.Uber.entities.enums.TransactionMethod;
import com.project.uber.Uber.entities.enums.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class WalletTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive(message = "Transaction amount must be positive")
    private Double amount;

    @NotNull(message = "Transaction type is required")
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @NotNull(message = "Transaction method is required")
    @Enumerated(EnumType.STRING)
    private TransactionMethod transactionMethod;

    @ManyToOne
    private Ride ride;

    private String transactionId;

    @NotNull(message = "Wallet is required")
    @ManyToOne(fetch = FetchType.LAZY)
    private Wallet wallet;

    @CreationTimestamp
    private LocalDateTime timestamp;

    public WalletTransaction() {
    }

    public WalletTransaction(WalletTransactionBuilder builder) {
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

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public static class WalletTransactionBuilder{
        private Long id;
        private Double amount;
        private TransactionType transactionType;
        private TransactionMethod transactionMethod;
        private Ride ride;
        private String transactionId;
        private Wallet wallet;
        private LocalDateTime timestamp;

        public WalletTransactionBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public WalletTransactionBuilder amount(Double amount) {
            this.amount = amount;
            return this;
        }

        public WalletTransactionBuilder transactionType(TransactionType transactionType) {
            this.transactionType = transactionType;
            return this;
        }

        public WalletTransactionBuilder transactionMethod(TransactionMethod transactionMethod) {
            this.transactionMethod = transactionMethod;
            return this;
        }

        public WalletTransactionBuilder ride(Ride ride) {
            this.ride = ride;
            return this;
        }

        public WalletTransactionBuilder transactionId(String transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        public WalletTransactionBuilder wallet(Wallet wallet) {
            this.wallet = wallet;
            return this;
        }

        public WalletTransactionBuilder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public WalletTransaction build() {
            return new WalletTransaction(this);
        }
    }
}
