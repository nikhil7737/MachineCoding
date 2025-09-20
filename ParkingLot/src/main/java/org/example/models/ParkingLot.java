package org.example.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ParkingLot {

    private int id;
    private final List<ParkingFloor> parkingFloors = new ArrayList<>();

    public void addFloor() {
        parkingFloors.add(new ParkingFloor());
    }


    public void addParkingSpotToFloor(int floor, ParkingSpotType parkingSpotType) {
        if (parkingFloors.size() - 1 < floor) {
            throw new RuntimeException("invalid floor");
        }

        parkingFloors.get(floor).addParkingSpot(parkingSpotType);
    }
}
