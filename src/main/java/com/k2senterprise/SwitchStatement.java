package com.k2senterprise;

public class SwitchStatement {
    public static void main(String[] args) {
        String day = getDayOfWeek();
        String result = switch (day){
            case "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY" -> "Weekday";
            case "SATURDAY", "SUNDAY" -> "WeekEnd";
            default -> "Invalid";

        };
        System.out.println("Today is " + result);

        Object obj = null;
        switch (obj) {
            case Integer i -> System.out.println("Integer: " + i);
            case String s -> System.out.println("String: " + s);
            case Double d -> System.out.println("Double: " + d);
            case null-> System.out.println("Null value");
            default -> System.out.println("Unknown type");
        }
    }

    private static String getDayOfWeek() {
        return java.time.LocalDate.now().getDayOfWeek().toString();
    }
}
