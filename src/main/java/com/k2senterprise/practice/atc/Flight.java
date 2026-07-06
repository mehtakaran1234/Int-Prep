package com.k2senterprise.practice.atc;

public class Flight implements Comparable<Flight> {
    private final String flightNumber; // Changed to String for typical ATC callsigns (e.g., AI101)
    private final FlightStatus flightStatus;
    private final int fuelLevel;
    private final FlightDirection flightDirection;

    public Flight(String flightNumber, FlightStatus flightStatus, int fuelLevel, FlightDirection flightDirection) {
        this.flightNumber = flightNumber;
        this.flightStatus = flightStatus;
        this.fuelLevel = fuelLevel;
        this.flightDirection = flightDirection;
    }

    public String getFlightNumber() { return flightNumber; }
    public FlightStatus getFlightStatus() { return flightStatus; }
    public int getFuelLevel() { return fuelLevel; }
    public FlightDirection getFlightDirection() { return flightDirection; }

    @Override
    public int compareTo(Flight o) {
        if (o == null) {
            throw new NullPointerException("Cannot compare to null Flight");
        }

        // 1. Status
        int statusCompare = this.getFlightStatus().compareTo(o.getFlightStatus());
        if (statusCompare != 0) return statusCompare;

        // 2. Direction: INBOUND before OUTBOUND
        if (this.getFlightDirection() != o.getFlightDirection()) {
            return this.getFlightDirection() == FlightDirection.INBOUND ? -1 : 1;
        }

        // 3. Lower fuel level gets priority
        return Integer.compare(this.getFuelLevel(), o.getFuelLevel());
    }

    @Override
    public String toString() {
        return String.format("Flight %s [%s, Direction: %s, Fuel: %d%%]",
                flightNumber, flightStatus, flightDirection, fuelLevel);
    }
}
