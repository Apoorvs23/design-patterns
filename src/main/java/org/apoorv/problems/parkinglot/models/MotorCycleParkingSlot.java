package org.apoorv.problems.parkinglot.models;

public class MotorCycleParkingSlot extends ParkingSlot{
    public MotorCycleParkingSlot(int id, int floorNumber) {
        super(id, floorNumber);
    }

    @Override
    public boolean canPark(Vehicle vehicle) {
        return vehicle instanceof MotorCycle;
    }


}
