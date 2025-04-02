package org.apoorv.problems.parkinglot.services;

import org.apoorv.problems.parkinglot.models.*;
import org.apoorv.problems.parkinglot.commands.UnparkVehicleCommand;
import org.apoorv.problems.parkinglot.commands.UnparkVehicleImp;
import org.apoorv.problems.parkinglot.payment.PaymentStrategy;

public class ExitGate {
    private final String gateId;
    private final ParkingLot parkingLot;
    private boolean isAvailable;

    public ExitGate(String gateId, ParkingLot parkingLot) {
        this.gateId = gateId;
        this.parkingLot = parkingLot;
        this.isAvailable = true;
    }

    public String getGateId() {
        return gateId;
    }

    public void processExit(String ticketNumber, PaymentStrategy paymentStrategy) throws Exception {
        if (!isAvailable) {
            throw new Exception("Gate is not available");
        }
        parkingLot.unparkVehicle(ticketNumber, paymentStrategy);
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
} 