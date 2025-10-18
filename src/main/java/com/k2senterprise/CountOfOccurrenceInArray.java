package com.k2senterprise;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CountOfOccurrenceInArray {
    public static void countOccurrence(int[] arr){

        Map<Integer, Long> counts = Arrays.stream(arr)
                .boxed()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        System.out.println("Count of Occurrence in Array: " + counts);

        Map<Integer, Integer> map = new HashMap<>();

        for(int i=0; i < arr.length; i++){
            if(map.containsKey(arr[i])){
                Integer value  = map.get(arr[i]);
                map.put(arr[i], (value + 1));

            }
            else {
                map.put(arr[i], 1);
            }


        }

        System.out.println("Count of Occurrence in Array: " + map);

        BiPredicate<Integer, Integer> p = (a, b) -> a > b;
        System.out.println(p.test(5, 31));


    }

    public static void main(String[] args) {
        int arr[] = {1,1,3,3,5,6,8,7,4};
        CountOfOccurrenceInArray.countOccurrence(arr);
    }


}
