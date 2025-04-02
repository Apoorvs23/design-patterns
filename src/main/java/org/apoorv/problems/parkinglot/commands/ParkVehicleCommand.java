package org.apoorv.problems.parkinglot.commands;

import org.apoorv.problems.parkinglot.models.*;

public interface ParkVehicleCommand {
    public String parkVehicle(Vehicle vehicle, int floorNumber) throws Exception;
}
