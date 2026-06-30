package com.k2senterprise.practice.data.structure.atc;

import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Java 6 compatible ATCController using anonymous Comparator and java.util.Date tie-breaker.
 */
public class ATCController {
    private final PriorityQueue<Flight> queue;

    public ATCController() {
        Comparator<Flight> comparator = new Comparator<Flight>() {
            public int compare(Flight a, Flight b) {
                double diff = b.getPriorityScore() - a.getPriorityScore();
                if (diff > 0.0) return 1;
                if (diff < 0.0) return -1;
                long ta = a.getScheduledTime() == null ? 0L : a.getScheduledTime().getTime();
                long tb = b.getScheduledTime() == null ? 0L : b.getScheduledTime().getTime();
                if (ta < tb) return -1;
                if (ta > tb) return 1;
                // final tie-breaker: id lexicographic
                if (a.getId() == null && b.getId() == null) return 0;
                if (a.getId() == null) return -1;
                if (b.getId() == null) return 1;
                return a.getId().compareTo(b.getId());
            }
        };
        queue = new PriorityQueue<Flight>(11, comparator);
    }

    public void scheduleFlight(Flight flight) {
        queue.add(flight);
    }

    public Flight nextFlight() {
        return queue.poll();
    }

    public Flight peekNext() {
        return queue.peek();
    }

    public boolean cancelFlight(String flightId) {
        Iterator<Flight> it = queue.iterator();
        boolean removed = false;
        while (it.hasNext()) {
            Flight f = it.next();
            String id = f.getId();
            if (id != null && id.equals(flightId)) {
                it.remove();
                removed = true;
                break;
            }
        }
        return removed;
    }

    public List<Flight> listScheduled() {
        List<Flight> list = new ArrayList<Flight>(queue);
        java.util.Collections.sort(list, queue.comparator());
        return list;
    }
}
