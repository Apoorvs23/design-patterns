package org.apoorv.problems.parkinglot;

public class Main {
    public static void main(String[] args) throws Exception {
        ParkingLot parkingLot = ParkingLot.getParkingLot();

        // Add floors
        parkingLot.addFloor(1, 3);
        parkingLot.addFloor(2, 3);

        // Add gates
        EntranceGate entranceGate1 = new EntranceGate("ENTRANCE_1", parkingLot);
        EntranceGate entranceGate2 = new EntranceGate("ENTRANCE_2", parkingLot);
        ExitGate exitGate1 = new ExitGate("EXIT_1", parkingLot);
        ExitGate exitGate2 = new ExitGate("EXIT_2", parkingLot);

        parkingLot.addEntranceGate(entranceGate1);
        parkingLot.addEntranceGate(entranceGate2);
        parkingLot.addExitGate(exitGate1);
        parkingLot.addExitGate(exitGate2);

        // Vehicle entry through different gates
        Vehicle car1 = VehicleFactory.createVehicle("CAR", "ABCD");
        String ticketNumber1 = entranceGate1.registerVehicle(car1);

        Vehicle car2 = VehicleFactory.createVehicle("CAR", "FGHI");
        String ticketNumber2 = entranceGate2.registerVehicle(car2);

        Vehicle car3 = VehicleFactory.createVehicle("CAR", "XYZ");
        String ticketNumber3 = entranceGate1.registerVehicle(car3);

        // Vehicle exit through different gates
        PaymentStrategy debitCardPayment = PaymentStrategyFactory.createPaymentStrategy("DEBIT_CARD", "689248967", "123");
        exitGate1.processExit(ticketNumber1, debitCardPayment);

        // New vehicle entry after a vehicle has exited
        Vehicle car4 = VehicleFactory.createVehicle("CAR", "NEW");
        String ticketNumber4 = entranceGate2.registerVehicle(car4);

        // Demonstrate gate availability
        entranceGate1.setAvailable(false);
        try {
            Vehicle car5 = VehicleFactory.createVehicle("CAR", "BLOCKED");
            entranceGate1.registerVehicle(car5);
        } catch (Exception e) {
            System.out.println("Gate 1 is not available: " + e.getMessage());
        }
    }
}
