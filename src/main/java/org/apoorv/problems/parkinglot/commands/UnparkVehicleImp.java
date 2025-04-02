package org.apoorv.problems.parkinglot.commands;

import org.apoorv.problems.parkinglot.models.ParkingLot;
import org.apoorv.problems.parkinglot.models.ParkingLotFloor;
import org.apoorv.problems.parkinglot.models.ParkingSlot;
import org.apoorv.problems.parkinglot.payment.PaymentStrategy;
import java.time.Duration;
import java.time.Instant;

public class UnparkVehicleImp implements UnparkVehicleCommand {
    private final ParkingLot parkingLot;
    private static final double HOURLY_RATE = 100.0;  // Base rate per hour
    private static final double DAILY_RATE = 1000.0;  // Daily rate
    private static final double WEEKLY_RATE = 5000.0; // Weekly rate

    public UnparkVehicleImp(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    @Override
    public void unparkVehicle(String ticketNumber, PaymentStrategy paymentStrategy) throws Exception {
        parkingLot.checkParkingTicket(ticketNumber);
        String[] ticketParts = ticketNumber.split("_");

        int floorNumber = Integer.parseInt(ticketParts[0]);
        int parkingSlotId = Integer.parseInt(ticketParts[1]);
        long entryTimestamp = Long.parseLong(ticketParts[3]);

        ParkingLotFloor parkingLotFloor = parkingLot.getParkingLotFloor(floorNumber);
        ParkingSlot parkingSlot = parkingLotFloor.getParkingSlot(parkingSlotId);

        // Calculate parking duration and fee
        double parkingFee = calculateParkingFee(entryTimestamp);
        paymentStrategy.pay(parkingFee);
        
        parkingSlot.unparkVehicle();
        parkingLot.removeParkingTicket(ticketNumber);
    }

    private double calculateParkingFee(long entryTimestamp) {
        long currentTime = Instant.now().getEpochSecond();
        long durationInSeconds = currentTime - entryTimestamp;
        Duration duration = Duration.ofSeconds(durationInSeconds);

        // Calculate duration in different units
        long hours = duration.toHours();
        long days = duration.toDays();
        long weeks = duration.toDays() / 7;

        // Apply different rates based on duration
        if (weeks > 0) {
            return weeks * WEEKLY_RATE + (days % 7) * DAILY_RATE + (hours % 24) * HOURLY_RATE;
        } else if (days > 0) {
            return days * DAILY_RATE + (hours % 24) * HOURLY_RATE;
        } else {
            return Math.max(1, hours) * HOURLY_RATE; // Minimum charge for 1 hour
        }
    }
}
