package com.k2senterprise.practice.atc;

public class ATCDemo {
    public static void main(String[] args) throws InterruptedException {
        ATCSimulator atc = new ATCSimulator();

        System.out.println("=== 1. REGISTERING INCOMING FLIGHTS ===");

        // Adding normal flights
        atc.requestLanding(new Flight("AI101", FlightStatus.NORMAL, 70, FlightDirection.INBOUND));
        atc.requestLanding(new Flight("UA404", FlightStatus.NORMAL, 100, FlightDirection.OUTBOUND));

        // Adding a critical emergency flight and a low-fuel flight
        atc.requestLanding(new Flight("BA202", FlightStatus.EMERGENCY, 12, FlightDirection.INBOUND));
        atc.requestLanding(new Flight("LH303", FlightStatus.NORMAL, 15, FlightDirection.INBOUND));

        System.out.println("\n=== 2. PROCESSING QUEUE BASED ON PRIORITY ===");

        // Process the 4 flights sequentially to verify correct priority ordering
        for (int i = 0; i < 4; i++) {
            atc.processNextFlight("Main-Runway");
        }
    }
}
