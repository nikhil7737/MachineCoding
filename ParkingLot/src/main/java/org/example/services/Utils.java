package org.example.services;

import org.example.models.ParkingSpot;
import org.example.models.ParkingSpotType;
import org.example.models.VehicleType;

import java.util.List;
import java.util.Map;

public class Utils {

    private static final Map<ParkingSpotType, List<VehicleType>> spotTypeToVehicleTypeMapping = Map.of(
            ParkingSpotType.BIKE, List.of(VehicleType.BIKE),
            ParkingSpotType.CAR, List.of(VehicleType.BIKE, VehicleType.CAR),
            ParkingSpotType.TRUCK, List.of(VehicleType.BIKE, VehicleType.CAR, VehicleType.TRUCK),
            ParkingSpotType.ELECTRIC, List.of(VehicleType.ELECTRIC)
    );

    public static boolean canAccommodate(ParkingSpot parkingSpot, VehicleType vehicleType) {
        return spotTypeToVehicleTypeMapping.get(parkingSpot.getSpotType()).contains(vehicleType);
    }

}
