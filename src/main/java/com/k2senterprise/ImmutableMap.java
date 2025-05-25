package com.k2senterprise;

import java.util.List;
import java.util.Map;

public class ImmutableMap {

    public static void main(String[] args) {
        Map<String, String> immutableMap = Map.of(
                "key1", "value1",
                "key2", "value2",
                "key3", "value3"
        );
        System.out.println(immutableMap);
/*        immutableMap.put("k4","v4");
        System.out.println(immutableMap);*/

        List<String> immutableList = List.of(
                "value1",
                "value2",
                "value3"
        );
        System.out.println(immutableList);
        immutableList.add("v4");
        System.out.println(immutableList);
    }
}
