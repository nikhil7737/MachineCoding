package elevatorsystem.strategy;

import elevatorsystem.services.ElevatorController;
import elevatorsystem.models.ExternalRequest;

import java.util.List;

public interface IElevatorSelectionStrategy {

    ElevatorController selectElevator(List<ElevatorController> elevatorControllers, ExternalRequest request);
}
