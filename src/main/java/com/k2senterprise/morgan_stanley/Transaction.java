package com.k2senterprise.morgan_stanley;

/*
Transaction objects, where each transaction has a userId, amount, and timestamp. You need to compute, using Java 8 Streams, the following:
        "For each user, find the maximum transaction amount made in each month, and return a Map<Integer, Map<YearMonth, Double>> where key is userId, and value is another map of YearMonth → maxAmount."
*/

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
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

    public static void main(String[] args) {
        List<Transaction> txns = Arrays.asList(
                new Transaction(1, 200.0, LocalDateTime.of(2023, 1, 15, 10, 0)),
                new Transaction(1, 400.0, LocalDateTime.of(2023, 1, 20, 12, 0)),
                new Transaction(1, 100.0, LocalDateTime.of(2023, 1, 20, 12, 0)),
                new Transaction(1, 150.0, LocalDateTime.of(2023, 2, 10, 9, 0)),
                new Transaction(2, 300.0, LocalDateTime.of(2023, 1, 25, 14, 0)),
                new Transaction(2, 100.0, LocalDateTime.of(2023, 2, 1, 10, 0))
        );
        /*Map<Integer, Map<Integer, Double>> map = new HashMap<>();
        for(Transaction t: txns){

            Map<Integer, Double> innerMap = new HashMap<>();
            if(map.containsKey(t.getUserId())){

                innerMap = map.get(t.getUserId());

                map.put(t.getUserId(), )

            }
            else {
                innerMap.put(t.getTimestamp().getMonth(), t.getAmount());
            }
        }

        Map<Integer, Map<YearMonth, Double>> result = getMonthlyMaxTransactions(txns);
        result.forEach((userId, monthMap) -> {
            System.out.println("User: " + userId);
            monthMap.forEach((month, amount) -> {
                System.out.println("  " + month + " -> " + amount);
            });
        });*/
    }
}


