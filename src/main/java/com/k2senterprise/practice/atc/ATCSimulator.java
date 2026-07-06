package com.k2senterprise.practice.atc;

import java.util.concurrent.PriorityBlockingQueue;

public class ATCSimulator {

    // Thread-safe bounding and sorting collection using explicit comparator policy
    private final PriorityBlockingQueue<Flight> priorityQueue = new PriorityBlockingQueue<>(11);

    // Producers (e.g., Radar/Radio incoming threads) call this
    public void requestLanding(Flight flight) {
        System.out.println("[ATC] Incoming Request: " + flight);
        priorityQueue.put(flight); // Thread-safe insertion
    }

    // Consumers (Runway threads) call this to wait for and process planes
    public void processNextFlight(String runwayName) throws InterruptedException {
        // take() blocks the runway thread cleanly until an aircraft arrives
        Flight nextFlight = priorityQueue.take();

        System.out.println("[" + runwayName + "] >>> CLEARED FOR ACTION: " + nextFlight);

        // Simulate real-world runway usage time
        Thread.sleep(1500);
        System.out.println("[" + runwayName + "] --- Runway cleared after handling " + nextFlight.getFlightNumber());
    }
}
