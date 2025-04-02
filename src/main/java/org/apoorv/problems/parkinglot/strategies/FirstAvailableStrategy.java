package org.apoorv.problems.parkinglot.strategies;

import java.util.Optional;
import org.apoorv.problems.parkinglot.models.*;

public class FirstAvailableStrategy implements ParkingStrategy{
    @Override
    public Optional<ParkingSlot> findAvailableSpot(Vehicle vehicle, ParkingLotFloor parkingLotFloor) {
        return parkingLotFloor.getAllParkingSlots().stream().filter(parkingSlot -> parkingSlot.isAvailable() && parkingSlot.canPark(vehicle)).findFirst();
    }
}
