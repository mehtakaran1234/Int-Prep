package com.k2senterprise;


import java.util.*;
import java.util.function.Consumer;
import java.lang.Integer;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Java8ForEachExample {

    public static void main(String[] args) {

        //creating sample Collection
        List<Integer> myList = new ArrayList<Integer>();
        for(int i=0; i<10; i++) myList.add(i);

        myList.stream().distinct().sorted(Comparator.reverseOrder()).forEach(System.out::println);

        /*//traversing using Iterator
        Iterator<Integer> it = myList.iterator();
        while(it.hasNext()){
            Integer i = it.next();
            System.out.println("Iterator Value::"+i);
        }

        //traversing through forEach method of Iterable with anonymous class
        myList.forEach(new Consumer<Integer>() {

            public void accept(Integer t) {
                System.out.println("forEach anonymous class Value::"+t);
            }

        });

        //traversing with Consumer interface implementation
        MyConsumer action = new MyConsumer();
        myList.forEach(action);*/

    }

}

//Consumer implementation that can be reused
class MyConsumer implements Consumer<Integer>{

    public void accept(Integer t) {
        System.out.println("Consumer impl Value::"+t);
    }
}
