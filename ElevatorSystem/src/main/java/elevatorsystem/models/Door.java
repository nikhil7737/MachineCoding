package elevatorsystem.models;


import lombok.Data;


public class Door {

    private DoorStatus doorStatus;

    public void openDoor() {
        doorStatus = DoorStatus.OPEN;
    }

    public void closeDoor() {
        doorStatus = DoorStatus.CLOSE;
    }
}
