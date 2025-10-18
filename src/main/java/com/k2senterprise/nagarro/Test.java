package com.k2senterprise.nagarro;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        List<Transaction> txns = Arrays.asList(
                new Transaction(1, 200.0, LocalDateTime.of(2023, 1, 15, 10, 0)),
                new Transaction(1, 400.0, LocalDateTime.of(2023, 1, 20, 12, 0)),
                new Transaction(1, 100.0, LocalDateTime.of(2023, 1, 20, 12, 0)),
                new Transaction(1, 150.0, LocalDateTime.of(2023, 2, 10, 9, 0)),
                new Transaction(2, 300.0, LocalDateTime.of(2023, 1, 25, 14, 0)),
                new Transaction(2, 100.0, LocalDateTime.of(2023, 2, 1, 10, 0))
        );
    /*Map<Integer, Map<Integer, Double>> map = new HashMap();
    for(Transaction t: txns){

        Map<Integer, Double> innerMap = new HashMap<>();
        if(map.containsKey(t.getUserId())){

            innerMap = map.get(t.getUserId());

            map.put(t.getUserId(), )

        }
        else {
            innerMap.put(t.getTimestamp().getMonth(), t.getAmount());
        }
    }*/


        Map<Integer, Map<Integer, Double>> result = txns.stream()
                .collect(
                        Collectors.groupingBy(
                                Transaction::getUserId,
                                Collectors.groupingBy(
                                        t -> t.getTimestamp().getMonthValue(),
                                        Collectors.collectingAndThen(
                                                Collectors.maxBy(Comparator.comparingDouble(Transaction::getAmount)),
                                                opt -> opt.map(Transaction::getAmount).orElse(0.0)
                                        )
                                )
                        )
                );
        result.forEach((userId, monthMap) -> {
            System.out.println("User: " + userId);
            monthMap.forEach((month, amount) -> {
                System.out.println("  " + month + " -> " + amount);
            });
        });

    /*Map<Integer, Map<Integer, Double>> result = getMonthlyMaxTransactions(txns);
    result.forEach((userId, monthMap) -> {
        System.out.println("User: " + userId);
        monthMap.forEach((month, amount) -> {
            System.out.println("  " + month + " -> " + amount);
        });
    });*/
    }
}
