package org.apoorv.problems.parkinglot.models;

public abstract class Vehicle {
    private final String vehicleNumber;

    public Vehicle(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }
}
