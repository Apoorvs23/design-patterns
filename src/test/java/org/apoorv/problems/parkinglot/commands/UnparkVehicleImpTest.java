package org.apoorv.problems.parkinglot.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.apoorv.problems.parkinglot.models.ParkingLot;
import org.apoorv.problems.parkinglot.models.ParkingLotFloor;
import org.apoorv.problems.parkinglot.models.ParkingSlot;
import org.apoorv.problems.parkinglot.payment.PaymentStrategy;
import java.time.Instant;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UnparkVehicleImpTest {

    @Mock
    private ParkingLot parkingLot;

    @Mock
    private ParkingLotFloor parkingLotFloor;

    @Mock
    private ParkingSlot parkingSlot;

    @Mock
    private PaymentStrategy paymentStrategy;

    private UnparkVehicleImp unparkVehicleImp;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        unparkVehicleImp = new UnparkVehicleImp(parkingLot);
    }

    @Test
    void testHourlyParkingFee() throws Exception {
        // Arrange
        String ticketNumber = "1_1_CAR_" + (Instant.now().getEpochSecond() - 3600); // 1 hour ago
        when(parkingLot.getParkingLotFloor(1)).thenReturn(parkingLotFloor);
        when(parkingLotFloor.getParkingSlot(1)).thenReturn(parkingSlot);

        // Act
        unparkVehicleImp.unparkVehicle(ticketNumber, paymentStrategy);

        // Assert
        verify(paymentStrategy).pay(100.0); // 1 hour * HOURLY_RATE
        verify(parkingSlot).unparkVehicle();
        verify(parkingLot).removeParkingTicket(ticketNumber);
    }

    @Test
    void testDailyParkingFee() throws Exception {
        // Arrange
        String ticketNumber = "1_1_CAR_" + (Instant.now().getEpochSecond() - 86400); // 1 day ago
        when(parkingLot.getParkingLotFloor(1)).thenReturn(parkingLotFloor);
        when(parkingLotFloor.getParkingSlot(1)).thenReturn(parkingSlot);

        // Act
        unparkVehicleImp.unparkVehicle(ticketNumber, paymentStrategy);

        // Assert
        verify(paymentStrategy).pay(1000.0); // 1 day * DAILY_RATE
        verify(parkingSlot).unparkVehicle();
        verify(parkingLot).removeParkingTicket(ticketNumber);
    }

    @Test
    void testWeeklyParkingFee() throws Exception {
        // Arrange
        String ticketNumber = "1_1_CAR_" + (Instant.now().getEpochSecond() - 604800); // 1 week ago
        when(parkingLot.getParkingLotFloor(1)).thenReturn(parkingLotFloor);
        when(parkingLotFloor.getParkingSlot(1)).thenReturn(parkingSlot);

        // Act
        unparkVehicleImp.unparkVehicle(ticketNumber, paymentStrategy);

        // Assert
        verify(paymentStrategy).pay(5000.0); // 1 week * WEEKLY_RATE
        verify(parkingSlot).unparkVehicle();
        verify(parkingLot).removeParkingTicket(ticketNumber);
    }

    @Test
    void testPartialWeekParkingFee() throws Exception {
        // Arrange
        String ticketNumber = "1_1_CAR_" + (Instant.now().getEpochSecond() - 432000); // 5 days ago
        when(parkingLot.getParkingLotFloor(1)).thenReturn(parkingLotFloor);
        when(parkingLotFloor.getParkingSlot(1)).thenReturn(parkingSlot);

        // Act
        unparkVehicleImp.unparkVehicle(ticketNumber, paymentStrategy);

        // Assert
        verify(paymentStrategy).pay(5000.0); // 5 days * DAILY_RATE
        verify(parkingSlot).unparkVehicle();
        verify(parkingLot).removeParkingTicket(ticketNumber);
    }

    @Test
    void testMinimumParkingFee() throws Exception {
        // Arrange
        String ticketNumber = "1_1_CAR_" + (Instant.now().getEpochSecond() - 1800); // 30 minutes ago
        when(parkingLot.getParkingLotFloor(1)).thenReturn(parkingLotFloor);
        when(parkingLotFloor.getParkingSlot(1)).thenReturn(parkingSlot);

        // Act
        unparkVehicleImp.unparkVehicle(ticketNumber, paymentStrategy);

        // Assert
        verify(paymentStrategy).pay(100.0); // Minimum 1 hour charge
        verify(parkingSlot).unparkVehicle();
        verify(parkingLot).removeParkingTicket(ticketNumber);
    }

    @Test
    void testInvalidTicketNumber() {
        // Arrange
        String invalidTicketNumber = "invalid_ticket";

        // Act & Assert
        assertThrows(Exception.class, () -> {
            unparkVehicleImp.unparkVehicle(invalidTicketNumber, paymentStrategy);
        });
    }
} 