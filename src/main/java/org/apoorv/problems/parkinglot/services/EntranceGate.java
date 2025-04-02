package org.apoorv.problems.parkinglot.services;

import java.util.List;
import java.util.ArrayList;
import org.apoorv.problems.parkinglot.models.*;
import org.apoorv.problems.parkinglot.strategies.ParkingStrategy;
import org.apoorv.problems.parkinglot.commands.ParkVehicleCommand;
import org.apoorv.problems.parkinglot.commands.ParkVehicleCommandImpl;

public class EntranceGate {
    private final String gateId;
    private final ParkingLot parkingLot;
    private boolean isAvailable;

    public EntranceGate(String gateId, ParkingLot parkingLot) {
        this.gateId = gateId;
        this.parkingLot = parkingLot;
        this.isAvailable = true;
    }

    public String getGateId() {
        return gateId;
    }

    public String registerVehicle(Vehicle vehicle) throws Exception {
        if (!isAvailable) {
            throw new Exception("Gate is not available");
        }

        // Try each floor until we find a spot
        List<ParkingLotFloor> floors = new ArrayList<>(parkingLot.getParkingLotFloors());
        for (ParkingLotFloor floor : floors) {
            try {
                return parkingLot.parkVehicle(vehicle, floor.getFloorNumber(), gateId);
            } catch (Exception e) {
                // If this floor is full, continue to next floor
                continue;
            }
        }
        
        // If we get here, no spots were found on any floor
        throw new Exception("No available parking spots found on any floor");
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
} 