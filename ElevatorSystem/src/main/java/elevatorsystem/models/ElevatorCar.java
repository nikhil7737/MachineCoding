package elevatorsystem.models;


import lombok.Getter;

import static elevatorsystem.models.Direction.*;


@Getter
public class ElevatorCar {

    private int currentFloor;
    private Direction direction;
    private Door door;


    public ElevatorCar() {
        currentFloor = 0;
        direction = IDLE;
    }


    public void move(int destinationFloor) {
        try {
            if (currentFloor == destinationFloor) {
                door.openDoor();
                return;
            }
            if (destinationFloor > currentFloor) {
                direction = UP;
            } else {
                direction = DOWN;
            }
            Thread.sleep(5000);
            currentFloor = destinationFloor;
            door.openDoor();
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
