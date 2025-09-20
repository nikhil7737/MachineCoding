package org.example.services.strategies;

import org.example.models.*;

public interface IParkingSpotSelectionStrategy {


    ParkingSpot tryBookingParkingSpot(ParkingLot parkingLot, Vehicle vehicle);
}
