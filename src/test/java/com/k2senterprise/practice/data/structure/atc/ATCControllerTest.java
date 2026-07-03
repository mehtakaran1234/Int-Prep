package com.k2senterprise.practice.data.structure.atc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ATCController (Java 6 version)
 */
public class ATCControllerTest {
    private ATCController atc;
    private Date now;
    private Date fiveMinsLater;
    private Date tenMinsLater;

    @BeforeEach
    public void setUp() {
        atc = new ATCController();
        now = new Date();
        fiveMinsLater = new Date(now.getTime() + 5 * 60 * 1000);
        tenMinsLater = new Date(now.getTime() + 10 * 60 * 1000);
    }

    @Test
    public void testEmergencyBeforeNormal() {
        Flight normal = new Flight("N1", FlightType.NORMAL, tenMinsLater, 120, false);
        Flight emergency = new Flight("E1", FlightType.EMERGENCY, fiveMinsLater, 120, false);

        atc.scheduleFlight(normal);
        atc.scheduleFlight(emergency);

        Flight first = atc.nextFlight();
        assertEquals("E1", first.getId(), "Emergency should be served first");
        
        Flight second = atc.nextFlight();
        assertEquals("N1", second.getId(), "Normal should be served second");
    }

    @Test
    public void testLowFuelHighPriority() {
        Flight goodFuel = new Flight("G1", FlightType.NORMAL, tenMinsLater, 200, false);
        Flight lowFuel = new Flight("L1", FlightType.NORMAL, tenMinsLater, 10, false);

        atc.scheduleFlight(goodFuel);
        atc.scheduleFlight(lowFuel);

        Flight first = atc.nextFlight();
        assertEquals("L1", first.getId(), "Low fuel should be prioritized");
    }

    @Test
    public void testMedicalEmergencyHighPriority() {
        Flight normal = new Flight("N1", FlightType.NORMAL, fiveMinsLater, 120, false);
        Flight medical = new Flight("M1", FlightType.NORMAL, tenMinsLater, 120, true);

        atc.scheduleFlight(normal);
        atc.scheduleFlight(medical);

        Flight first = atc.nextFlight();
        assertEquals("M1", first.getId(), "Medical emergency should be prioritized");
    }

    @Test
    public void testCancelFlight() {
        atc.scheduleFlight(new Flight("F1", FlightType.NORMAL, tenMinsLater, 120, false));
        atc.scheduleFlight(new Flight("F2", FlightType.NORMAL, tenMinsLater, 120, false));
        atc.scheduleFlight(new Flight("F3", FlightType.NORMAL, tenMinsLater, 120, false));

        boolean cancelled = atc.cancelFlight("F2");
        assertTrue(cancelled, "Flight should be cancelled");

        List scheduled = atc.listScheduled();
        assertEquals(2, scheduled.size(), "Should have 2 flights after cancellation");
    }

    @Test
    public void testPeekNextDoesNotRemove() {
        Flight f1 = new Flight("F1", FlightType.NORMAL, tenMinsLater, 120, false);
        atc.scheduleFlight(f1);

        Flight peeked = atc.peekNext();
        assertNotNull(peeked);
        assertEquals("F1", peeked.getId());

        Flight next = atc.nextFlight();
        assertNotNull(next);
        assertEquals("F1", next.getId());

        Flight empty = atc.nextFlight();
        assertNull(empty, "Queue should be empty");
    }

    @Test
    public void testEmptyQueue() {
        assertNull(atc.nextFlight(), "Empty queue should return null");
        assertNull(atc.peekNext(), "Empty queue peek should return null");
        assertFalse(atc.cancelFlight("nonexistent"), "Cancel on empty queue should return false");
    }

    @Test
    public void testListScheduledOrdering() {
        atc.scheduleFlight(new Flight("F1", FlightType.NORMAL, tenMinsLater, 120, false));
        atc.scheduleFlight(new Flight("F2", FlightType.EMERGENCY, tenMinsLater, 120, false));
        atc.scheduleFlight(new Flight("F3", FlightType.NORMAL, fiveMinsLater, 50, false));

        List scheduled = atc.listScheduled();
        assertEquals(3, scheduled.size());
        
        Flight first = (Flight) scheduled.get(0);
        assertTrue(
            first.getId().equals("F2") || first.getId().equals("F3"),
            "First in list should be emergency or low fuel"
        );
    }
}
