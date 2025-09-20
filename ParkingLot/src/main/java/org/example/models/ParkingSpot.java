package org.example.models;

import lombok.Data;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Data
public class ParkingSpot {

    private int id;
    private ParkingSpotType spotType;
    private Vehicle vehicle;
    private Lock lock;

    public ParkingSpot(int id, ParkingSpotType spotType) {
        this.id = id;
        this.spotType = spotType;
        lock = new ReentrantLock();
    }

}
