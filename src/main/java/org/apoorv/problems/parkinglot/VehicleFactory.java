package org.apoorv.problems.parkinglot;

public class VehicleFactory {
    public static Vehicle createVehicle(String type, String vehicleNumber) {
        switch (type.toUpperCase()) {
            case "CAR":
                return new Car(vehicleNumber);
            case "MOTORCYCLE":
                return new MotorCycle(vehicleNumber);
            default:
                throw new IllegalArgumentException("Invalid vehicle type: " + type);
        }
    }
} 