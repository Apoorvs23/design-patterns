package org.apoorv.problems.parkinglot.models;

public class CarParkingSlot extends ParkingSlot{

    public CarParkingSlot(int id, int floorNumber) {
        super(id, floorNumber);
    }

    @Override
    public boolean canPark(Vehicle vehicle) {
        return vehicle instanceof Car;
    }
}
