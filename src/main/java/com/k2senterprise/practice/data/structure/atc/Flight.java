package com.k2senterprise.practice.data.structure.atc;

import java.util.Date;
import java.util.Calendar;

/**
 * Java 6 compatible Flight class: uses java.util.Date and no java.time/Objects APIs.
 */
public class Flight {
    private final String id;
    private final FlightType type;
    private final Date scheduledTime;
    private final int fuelMinutesRemaining; // minutes of fuel left
    private final boolean medicalEmergency;

    public Flight(String id, FlightType type, Date scheduledTime) {
        this(id, type, scheduledTime, 180, false);
    }

    public Flight(String id, FlightType type, Date scheduledTime, int fuelMinutesRemaining, boolean medicalEmergency) {
        this.id = id;
        this.type = type;
        this.scheduledTime = scheduledTime;
        this.fuelMinutesRemaining = fuelMinutesRemaining;
        this.medicalEmergency = medicalEmergency;
    }

    public String getId() { return id; }
    public FlightType getType() { return type; }
    public Date getScheduledTime() { return scheduledTime; }
    public int getFuelMinutesRemaining() { return fuelMinutesRemaining; }
    public boolean isMedicalEmergency() { return medicalEmergency; }

    public double getPriorityScore() {
        double score = 0.0;
        score += (type == FlightType.EMERGENCY) ? 100.0 : 0.0;
        if (medicalEmergency) {
            score += 50.0;
        }
        if (fuelMinutesRemaining <= 0) {
            score += 120.0; // immediate
        } else {
            double fuelBonus = Math.max(0.0, 180.0 - fuelMinutesRemaining) / 180.0 * 60.0;
            score += fuelBonus;
        }
        return score;
    }

    @Override
    public String toString() {
        return String.format("Flight[id=%s,type=%s,scheduled=%s,fuel=%d,medical=%s,score=%.1f]",
                id, type, scheduledTime, fuelMinutesRemaining, medicalEmergency, getPriorityScore());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        if (id == null) return flight.id == null;
        return id.equals(flight.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }

    // Helper to create Date offsets (convenience for older code)
    public static Date minutesFromNow(int minutes) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE, minutes);
        return c.getTime();
    }
}
