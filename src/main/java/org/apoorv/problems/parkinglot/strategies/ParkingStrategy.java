package org.apoorv.problems.parkinglot.strategies;

import java.util.Optional;
import org.apoorv.problems.parkinglot.models.*;

public interface ParkingStrategy {
    Optional<ParkingSlot> findAvailableSpot(Vehicle vehicle, ParkingLotFloor parkingLotFloor);
}
