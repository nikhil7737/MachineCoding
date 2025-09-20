package org.example.models;

import lombok.Data;

import java.util.List;

@Data
public class ParkingFloor {

    private List<ParkingSpot> parkingSpots;
    private int number;

    public void addParkingSpot(ParkingSpotType parkingSpotType) {
        ParkingSpot parkingSpot = new ParkingSpot(parkingSpots.size() + 1, parkingSpotType);
        parkingSpots.add(parkingSpot);
    }
}
