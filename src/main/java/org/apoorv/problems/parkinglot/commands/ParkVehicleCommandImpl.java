package org.apoorv.problems.parkinglot.commands;

import java.util.Optional;
import org.apoorv.problems.parkinglot.models.*;
import org.apoorv.problems.parkinglot.strategies.ParkingStrategy;

public class ParkVehicleCommandImpl implements ParkVehicleCommand{
    private final ParkingStrategy parkingStrategy;
    private final ParkingLot parkingLot;
    private final String entranceGateId;

    public ParkVehicleCommandImpl(ParkingStrategy parkingStrategy, ParkingLot parkingLot, String entranceGateId) {
        this.parkingStrategy = parkingStrategy;
        this.parkingLot = parkingLot;
        this.entranceGateId = entranceGateId;
    }

    @Override
    public String parkVehicle(Vehicle vehicle, int floorNumber) throws Exception {
        ParkingLotFloor parkingLotFloor = parkingLot.getParkingLotFloor(floorNumber);
        Optional<ParkingSlot> availableSpot = parkingStrategy.findAvailableSpot(vehicle, parkingLotFloor);

        if(availableSpot.isEmpty()){
            throw new Exception("No available spot found");
        }else{
            ParkingSlot parkingSlot = availableSpot.get();
            ParkingTicket parkingTicket = parkingSlot.parkVehicle(vehicle, entranceGateId);

            System.out.println("Vehicle with number: "+vehicle.getVehicleNumber()+" parked at floor "+parkingLotFloor.getFloorNumber()+" at parking slot "+parkingSlot.getId());

            parkingLot.addParkingTicket(parkingTicket.getTicketId());

            return parkingTicket.getTicketId();
        }
    }
}
