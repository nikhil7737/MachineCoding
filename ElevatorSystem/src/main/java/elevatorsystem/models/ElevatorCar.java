package elevatorsystem.models;


import lombok.Getter;

import static elevatorsystem.models.Direction.*;


@Getter
public class ElevatorCar {

    private final int id;
    private int currentFloor;
    private Direction direction;
    private final Door door = new Door();


    public ElevatorCar(int id) {
        this.id = id;
        currentFloor = 0;
        direction = IDLE;
    }


    public void move(int destinationFloor) {
        try {
            if (currentFloor == destinationFloor) {
                door.openDoor();
                System.out.println("Elevator " + id + " already at floor " + destinationFloor);
                return;
            }
            if (destinationFloor > currentFloor) {
                direction = UP;
            } else {
                direction = DOWN;
            }

            System.out.printf("Elevator %s moving towards floor %s\n", id, destinationFloor);
            Thread.sleep(5000);
            currentFloor = destinationFloor;
            door.openDoor();
            System.out.printf("Elevator %s reached floor %s\n", id, destinationFloor);
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
