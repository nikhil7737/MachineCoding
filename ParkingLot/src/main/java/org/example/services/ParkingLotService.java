package org.example.services;

import org.example.models.ParkingLot;
import org.example.models.ParkingSpot;
import org.example.models.ParkingSpotType;
import org.example.models.VehicleType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLotService {

    private final Map<Integer, ParkingLot> parkingLotMap = new HashMap<>();


    public void createParkingLot() {
        int id = parkingLotMap.size() + 1;
        parkingLotMap.put(id, new ParkingLot());
    }

    public void addFloorToParkingLot(int parkingLotId) {
        validateParkingLotId(parkingLotId);
        parkingLotMap.get(parkingLotId).addFloor();
    }

    public void addParkingSpot(int parkingLotId, int floor, ParkingSpotType parkingSpotType) {
        validateParkingLotId(parkingLotId);
        parkingLotMap.get(parkingLotId).addParkingSpotToFloor(floor, parkingSpotType);
    }

    public ParkingLot getParkingLotById(Integer lotId) {
        validateParkingLotId(lotId);
        return parkingLotMap.get(lotId);
    }

    private void validateParkingLotId(int parkingLotId) {
        if (!parkingLotMap.containsKey(parkingLotId)) {
            throw new RuntimeException("invalid parking lot id for addFloorToParkingLot");
        }
    }

    public List<ParkingSpot> fetchFreeSlotsOnFloor(int parkingLotId, int floor, VehicleType vehicleType) {
        validateParkingLotId(parkingLotId);
        return parkingLotMap.get(parkingLotId).getParkingFloors().get(floor).getParkingSpots().stream().filter(
                spot -> Utils.canAccommodate(spot, vehicleType) && spot.getVehicle() == null
        ).toList();
    }

    public List<ParkingSpot> fetchOccupiedSlotsOnFloor(int parkingLotId, int floor) {
        validateParkingLotId(parkingLotId);
        return parkingLotMap.get(parkingLotId).getParkingFloors().get(floor).getParkingSpots().stream().filter(
                spot -> spot.getVehicle() != null
        ).toList();
    }

}
