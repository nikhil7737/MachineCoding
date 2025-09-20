package org.example.models;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.util.Date;


@Builder
@Data
public class Ticket {

    private int id;
    private ParkingSpot parkingSpot;
    private Vehicle vehicle;
    private Date parkedAt;
    private Date unparkedAt;
    private Double cost;


}
