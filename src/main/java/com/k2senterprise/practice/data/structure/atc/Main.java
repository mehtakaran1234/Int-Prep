package com.k2senterprise.practice.data.structure.atc;

import java.util.Date;

public class Main {
    public static void main(String[] args) throws Exception {
        ATCController atc = new ATCController();

        atc.scheduleFlight(new Flight("F1", FlightType.NORMAL, Flight.minutesFromNow(30), 180, false));
        atc.scheduleFlight(new Flight("F2", FlightType.EMERGENCY, Flight.minutesFromNow(60), 120, false));
        atc.scheduleFlight(new Flight("F3", FlightType.NORMAL, Flight.minutesFromNow(10), 25, false));
        atc.scheduleFlight(new Flight("F4", FlightType.NORMAL, Flight.minutesFromNow(5), 90, true));
        atc.scheduleFlight(new Flight("F5", FlightType.EMERGENCY, Flight.minutesFromNow(15), 5, true));

        System.out.println("Serving flights in Java6-compatible priority order:");
        Flight f;
        while ((f = atc.nextFlight()) != null) {
            System.out.println(f);
            Thread.sleep(150);
        }
    }
}
