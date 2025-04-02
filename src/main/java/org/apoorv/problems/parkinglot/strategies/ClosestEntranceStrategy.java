package org.apoorv.problems.parkinglot.strategies;

import org.apoorv.problems.parkinglot.models.*;
import java.util.*;

public class ClosestEntranceStrategy implements ParkingStrategy {
    private final Map<String, PriorityQueue<ParkingSlot>> entranceMinHeaps;
    private final Set<ParkingSlot> availableSpots;
    private final Set<ParkingSlot> reservedSpots;
    private final Map<ParkingSlot, Map<String, Integer>> spotDistances;

    public ClosestEntranceStrategy() {
        this.entranceMinHeaps = new HashMap<>();
        this.availableSpots = new HashSet<>();
        this.reservedSpots = new HashSet<>();
        this.spotDistances = new HashMap<>();
    }

    @Override
    public Optional<ParkingSlot> findAvailableSpot(Vehicle vehicle, ParkingLotFloor parkingLotFloor) {
        String entranceId = getCurrentEntranceId(); // This should be passed from the entrance gate
        PriorityQueue<ParkingSlot> minHeap = entranceMinHeaps.get(entranceId);
        
        if (minHeap == null || minHeap.isEmpty()) {
            return Optional.empty();
        }

        ParkingSlot closestSpot = minHeap.poll();
        if (closestSpot != null && closestSpot.canPark(vehicle)) {
            markSpotAsReserved(closestSpot);
            return Optional.of(closestSpot);
        }

        return Optional.empty();
    }

    public void initializeParkingLot(ParkingLot parkingLot) {
        // Initialize min-heaps for each entrance
        for (EntranceGate entranceGate : parkingLot.getEntranceGates()) {
            String entranceId = entranceGate.getGateId();
            PriorityQueue<ParkingSlot> minHeap = new PriorityQueue<>(
                (a, b) -> Integer.compare(
                    spotDistances.get(a).get(entranceId),
                    spotDistances.get(b).get(entranceId)
                )
            );
            entranceMinHeaps.put(entranceId, minHeap);
        }

        // Add all parking slots to available spots and calculate distances
        for (ParkingLotFloor floor : parkingLot.getParkingLotFloors()) {
            for (ParkingSlot slot : floor.getAllParkingSlots()) {
                availableSpots.add(slot);
                calculateDistances(slot, parkingLot);
            }
        }
    }

    private void calculateDistances(ParkingSlot slot, ParkingLot parkingLot) {
        Map<String, Integer> distances = new HashMap<>();
        for (EntranceGate entranceGate : parkingLot.getEntranceGates()) {
            String entranceId = entranceGate.getGateId();
            // Calculate Manhattan distance from entrance to slot
            int distance = calculateManhattanDistance(entranceGate, slot);
            distances.put(entranceId, distance);
        }
        spotDistances.put(slot, distances);
    }

    private int calculateManhattanDistance(EntranceGate entrance, ParkingSlot slot) {
        // This is a simplified distance calculation
        // In a real implementation, you would use actual coordinates
        return Math.abs(entrance.getGateId().hashCode() - slot.getId());
    }

    private void markSpotAsReserved(ParkingSlot spot) {
        availableSpots.remove(spot);
        reservedSpots.add(spot);
        
        // Remove the spot from all min-heaps
        for (PriorityQueue<ParkingSlot> minHeap : entranceMinHeaps.values()) {
            minHeap.remove(spot);
        }
    }

    public void releaseSpot(ParkingSlot spot) {
        reservedSpots.remove(spot);
        availableSpots.add(spot);
        
        // Add the spot back to all min-heaps
        for (Map.Entry<String, PriorityQueue<ParkingSlot>> entry : entranceMinHeaps.entrySet()) {
            String entranceId = entry.getKey();
            PriorityQueue<ParkingSlot> minHeap = entry.getValue();
            minHeap.offer(spot);
        }
    }

    private String getCurrentEntranceId() {
        // This should be implemented to get the current entrance ID
        // For now, returning a default value
        return "ENTRANCE_1";
    }
} 