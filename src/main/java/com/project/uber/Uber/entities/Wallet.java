package com.project.uber.Uber.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    private Double balance;

    @OneToMany(mappedBy = "wallet",fetch = FetchType.LAZY)
    private List<WalletTransaction> transactions;

    public Wallet() {
    }

    public Wallet(Long id, User user, Double balance, List<WalletTransaction> transactions) {
        this.id = id;
        this.user = user;
        this.balance = balance;
        this.transactions = transactions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<WalletTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<WalletTransaction> transactions) {
        this.transactions = transactions;
    }
}
