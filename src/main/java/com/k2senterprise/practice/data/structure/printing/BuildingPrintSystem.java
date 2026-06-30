package com.k2senterprise.practice.data.structure.printing;

import java.util.HashMap;
import java.util.Map;

public class BuildingPrintSystem {

    private final Map<Integer, Boolean> floorPrinters;
    private final int totalFloors;

    public BuildingPrintSystem(int totalFloors) {
        this.totalFloors = totalFloors;
        this.floorPrinters = new HashMap<>();

        // Setup initial printer statuses for the building
        floorPrinters.put(1, true);   // Floor 1: Working
        floorPrinters.put(2, false);  // Floor 2: Out of order
        floorPrinters.put(3, true);   // Floor 3: Working
        floorPrinters.put(4, false);  // Floor 4: Out of order
    }

    // Method to find the nearest working printer
    public int findNearestWorkingPrinter(int startFloor) {
        if (startFloor < 1 || startFloor > totalFloors) {
            throw new IllegalArgumentException("Invalid floor number");
        }

        int distance = 0;
        while (distance < totalFloors) {
            // Check floor above
            int upwardFloor = startFloor + distance;
            if (upwardFloor <= totalFloors && isPrinterWorking(upwardFloor)) {
                return upwardFloor;
            }

            // Check floor below
            int downwardFloor = startFloor - distance;
            if (downwardFloor >= 1 && isPrinterWorking(downwardFloor)) {
                return downwardFloor;
            }

            distance++;
        }

        return -1; // No working printers in the entire building
    }

    private boolean isPrinterWorking(int floor) {
        return floorPrinters.getOrDefault(floor, false);
    }

    public void printDocument(int originalFloor, String documentName) {
        if (isPrinterWorking(originalFloor)) {
            System.out.println("Success: Printed '" + documentName + "' on Floor " + originalFloor);
        } else {
            System.out.println("Warning: Printer broken on Floor " + originalFloor + ". Searching for nearest alternative...");
            int workingFloor = findNearestWorkingPrinter(originalFloor);

            if (workingFloor != -1) {
                System.out.println("Redirected: '" + documentName + "' successfully routed to Floor " + workingFloor);
            } else {
                System.out.println("Error: No functioning printers available in the building right now.");
            }
        }
    }

    public static void main(String[] args) {
        BuildingPrintSystem system = new BuildingPrintSystem(4);

        System.out.println("--- Submitting Document A from Floor 2 ---");
        system.printDocument(2, "Document_A.pdf");

        System.out.println("\n--- Submitting Document B from Floor 4 ---");
        system.printDocument(4, "Document_B.docx");
    }
}
