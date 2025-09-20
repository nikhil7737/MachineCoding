package org.example.services;

import org.example.models.ParkingSpot;
import org.example.models.Ticket;
import org.example.models.Vehicle;
import org.example.models.VehicleType;
import org.example.services.strategies.IParkingSpotSelectionStrategy;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ParkingService {

    private static final Map<Integer, Ticket> tickets = new HashMap<>();
    private static final Map<VehicleType, Double> perHourPrice = Map.of(
            VehicleType.BIKE, 100D,
            VehicleType.CAR, 200D,
            VehicleType.TRUCK, 500D
    );

    private final ParkingLotService parkingLotService;
    private final IParkingSpotSelectionStrategy parkingSpotSelectionStrategy;

    public ParkingService(ParkingLotService parkingLotService, IParkingSpotSelectionStrategy parkingSpotSelectionStrategy) {
        this.parkingLotService = parkingLotService;
        this.parkingSpotSelectionStrategy = parkingSpotSelectionStrategy;
    }


    public Ticket parkVehicle(int parkingLotId, Vehicle vehicle) {
        ParkingSpot parkingSpot = parkingSpotSelectionStrategy.tryBookingParkingSpot(
                parkingLotService.getParkingLotById(parkingLotId),
                vehicle
        );
        if (parkingSpot == null) {
            return null;
        }
        return createTicket(parkingSpot, vehicle);

    }

    public Double unpark(int ticketId) {
        if (!tickets.containsKey(ticketId)) {
            throw new RuntimeException("invalid ticket id");
        }
        Ticket ticket = tickets.get(ticketId);
        ticket.setUnparkedAt(new Date());

        //can be strategy pattern
        Double cost = calculatePrice(ticket);
        ticket.setCost(cost);
        return cost;
    }

    private Double calculatePrice(Ticket ticket) {
        int hours = Math.toIntExact((ticket.getUnparkedAt().getTime() - ticket.getParkedAt().getTime()) / 1000 / 3600);
        return hours * perHourPrice.get(ticket.getVehicle().getVehicleType());
    }

    private Ticket createTicket(ParkingSpot parkingSpot, Vehicle vehicle) {
        int id = tickets.size() + 1;
        Ticket ticket = Ticket.builder()
                .parkedAt(new Date())
                .parkingSpot(parkingSpot)
                .vehicle(vehicle)
                .id(id)
                .build();
        tickets.put(id, ticket);
        return ticket;
    }

}
