package org.example.services.strategies;

import org.example.models.*;
import org.example.services.Utils;

import java.util.List;
import java.util.Map;

public class NearestParkingSpotSelectionStrategy implements IParkingSpotSelectionStrategy {


    @Override
    public ParkingSpot tryBookingParkingSpot(ParkingLot parkingLot, Vehicle vehicle) {
        for (int i = 0; i < parkingLot.getParkingFloors().size(); i++) {
            ParkingFloor parkingFloor = parkingLot.getParkingFloors().get(i);
            for (int j = 0; j < parkingFloor.getParkingSpots().size(); j++) {
                ParkingSpot parkingSpot = parkingFloor.getParkingSpots().get(j);
                parkingSpot.getLock().lock();
                try {
                    if (parkingSpot.getVehicle() == null && Utils.canAccommodate(parkingSpot, vehicle.getVehicleType())) {
                        parkingSpot.setVehicle(vehicle);
                        return parkingSpot;
                    }
                }
                finally {
                    parkingSpot.getLock().unlock();
                }
            }
        }
        return null;
    }

}
