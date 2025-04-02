package org.apoorv.problems.parkinglot.factories;

import org.apoorv.problems.parkinglot.models.*;

public class ParkingSlotFactory {
    public static ParkingSlot createParkingSlot(String type, int id, int floorNumber) {
        switch (type.toUpperCase()) {
            case "CAR":
                return new CarParkingSlot(id, floorNumber);
            case "MOTORCYCLE":
                return new MotorCycleParkingSlot(id, floorNumber);
            default:
                throw new IllegalArgumentException("Invalid parking slot type: " + type);
        }
    }
} 