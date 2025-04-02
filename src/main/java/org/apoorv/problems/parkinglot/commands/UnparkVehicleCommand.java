package org.apoorv.problems.parkinglot.commands;

import org.apoorv.problems.parkinglot.models.*;
import org.apoorv.problems.parkinglot.payment.PaymentStrategy;

public interface UnparkVehicleCommand {
    public void unparkVehicle(String ticketNumber, PaymentStrategy paymentStrategy) throws Exception;
}
