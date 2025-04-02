package org.apoorv.problems.parkinglot.models;

import java.util.Date;

public class ParkingTicket {
    private final String ticketId;
    private final String entranceGateId;

    public ParkingTicket(Vehicle vehicle, int floorNumber, int parkingSlotId, String entranceGateId) {
        this.ticketId = String.format("%d_%d_%s_%d", floorNumber, parkingSlotId, vehicle.getVehicleNumber(), new Date().getTime());
        this.entranceGateId = entranceGateId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getEntranceGateId() {
        return entranceGateId;
    }
}
