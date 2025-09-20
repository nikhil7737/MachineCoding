package elevatorsystem.strategy.impls;

import elevatorsystem.models.Direction;
import elevatorsystem.models.ElevatorCar;
import elevatorsystem.services.ElevatorController;
import elevatorsystem.strategy.IFloorSelectionStrategy;

public class NearestFloorStrategy implements IFloorSelectionStrategy {
    @Override
    public int getNextFloor(ElevatorController elevatorController) {
        ElevatorCar elevatorCar = elevatorController.getElevatorCar();
        Integer nextUp = elevatorController.getFloorRequests().ceiling(elevatorCar.getCurrentFloor());
        Integer nextDown = elevatorController.getFloorRequests().floor(elevatorCar.getCurrentFloor());
        if (elevatorCar.getDirection() == Direction.UP) {
            return nextUp != null ? nextUp : nextDown;
        }
        else if (elevatorCar.getDirection() == Direction.DOWN) {
            return nextDown != null ? nextDown : nextUp;
        }
        else {
            return Math.min(nextUp != null ? nextUp : 10000, nextDown != null ? nextDown : 10000);
        }
    }
}
