package elevatorsystem.strategy;

import elevatorsystem.services.ElevatorController;

public interface IFloorSelectionStrategy {

    int getNextFloor(ElevatorController elevatorController);

}
