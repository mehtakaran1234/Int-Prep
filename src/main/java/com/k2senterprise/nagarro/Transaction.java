package com.k2senterprise.nagarro;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Transaction {
    private int userId;
    private double amount;
    private LocalDateTime timestamp;

    public Transaction(int userId, double amount, LocalDateTime timestamp) {
        this.userId = userId;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public int getUserId() { return userId; }
    public double getAmount() { return amount; }
    public LocalDateTime getTimestamp() { return timestamp; }
}

