package org.apoorv.problems.parkinglot;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

public class ParkingLot {
    private final HashMap<Integer, ParkingLotFloor> parkingLotFloors; // floor number -> floor
    private final HashSet<String> parkingTickets; // ticket ids
    private ParkVehicleCommand parkVehicleCommand;
    private final UnparkVehicleCommand unparkVehicleCommand;
    private final List<EntranceGate> entranceGates;
    private final List<ExitGate> exitGates;

    private ParkingLot() {
        this.parkingLotFloors = new HashMap<>();
        this.parkingTickets = new HashSet<>();
        this.unparkVehicleCommand = new UnparkVehicleImp(this);
        this.entranceGates = new ArrayList<>();
        this.exitGates = new ArrayList<>();
    }

    private static final class ParkingLotHolder {
        private static final ParkingLot parkingLot = new ParkingLot();
    }

    public static ParkingLot getParkingLot() {
        return ParkingLotHolder.parkingLot;
    }

    public void addFloor(int floorNumber, int totalSlots) {
        ParkingLotFloor parkingLotFloor = new ParkingLotFloor(floorNumber, totalSlots);
        parkingLotFloors.put(floorNumber, parkingLotFloor);
    }

    public ParkingLotFloor getParkingLotFloor(int floorNumber) throws Exception{
        if(!parkingLotFloors.containsKey(floorNumber)){
            throw new Exception("Invalid floor number");
        }
        return parkingLotFloors.get(floorNumber);
    }

    public Collection<ParkingLotFloor> getParkingLotFloors() {
        return parkingLotFloors.values();
    }

    public void addParkingTicket(String ticketNumber){
        parkingTickets.add(ticketNumber);
    }

    public void checkParkingTicket(String ticketNumber) throws Exception {
        if (!parkingTickets.contains(ticketNumber)) {
            throw new Exception("Invalid parking ticket");
        }
    }

    public void removeParkingTicket(String ticketNumber){
        parkingTickets.remove(ticketNumber);
    }

    public void addEntranceGate(EntranceGate gate) {
        entranceGates.add(gate);
    }

    public void addExitGate(ExitGate gate) {
        exitGates.add(gate);
    }

    public List<EntranceGate> getEntranceGates() {
        return entranceGates;
    }

    public List<ExitGate> getExitGates() {
        return exitGates;
    }

    /*
        Returns parking ticket number
     */
    public String parkVehicle(Vehicle vehicle, int floorNumber, String entranceGateId) throws Exception {
        this.parkVehicleCommand = new ParkVehicleCommandImpl(new FirstAvailableStrategy(), this, entranceGateId);
        return parkVehicleCommand.parkVehicle(vehicle, floorNumber);
    }

    /*
        Unpark vehicle
     */
    public void unparkVehicle(String ticketNumber, PaymentStrategy paymentStrategy) throws Exception {
        unparkVehicleCommand.unparkVehicle(ticketNumber, paymentStrategy);
    }
}
