package org.apoorv.problems.parkinglot;

public class Main {
    public static void main(String[] args) throws Exception {
        ParkingLot parkingLot = ParkingLot.getParkingLot();

        parkingLot.addFloor(1, 3);
        parkingLot.addFloor(2, 3);

        Vehicle car1 = VehicleFactory.createVehicle("CAR", "ABCD");
        String ticketNumber1 = parkingLot.parkVehicle(car1, 2);

        Vehicle car2 = VehicleFactory.createVehicle("CAR", "FGHI");
        String ticketNumber2 = parkingLot.parkVehicle(car2, 1);

        Vehicle car3 = VehicleFactory.createVehicle("CAR", "XYZ");
        String ticketNumber3 = parkingLot.parkVehicle(car3, 2);

        PaymentStrategy debitCardPayment = PaymentStrategyFactory.createPaymentStrategy("DEBIT_CARD", "689248967", "123");
        parkingLot.unparkVehicle(ticketNumber1, debitCardPayment);

        Vehicle car4 = VehicleFactory.createVehicle("CAR", "NEW");
        // Will be parked at slot 0 at floor 2 since car1 is unparked now
        String ticketNumber4 = parkingLot.parkVehicle(car4, 2);
    }
}
