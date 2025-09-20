package elevatorsystem.strategy.impls;

import elevatorsystem.models.ExternalRequest;
import elevatorsystem.services.ElevatorController;
import elevatorsystem.strategy.IElevatorSelectionStrategy;

import java.util.List;

public class QuickestElevatorSelectionStrategy implements IElevatorSelectionStrategy {
    @Override
    public ElevatorController selectElevator(List<ElevatorController> elevatorControllers, ExternalRequest request) {
        return null;
    }
}
