package elevatorsystem.services;

import elevatorsystem.models.ExternalRequest;
import elevatorsystem.models.InternalRequest;
import elevatorsystem.strategy.IElevatorSelectionStrategy;

import java.util.List;

public class ElevatorSystem {

    private List<ElevatorController> elevatorControllers;

    private IElevatorSelectionStrategy selectionStrategy;



    public void addExternalRequest(ExternalRequest request) {
        ElevatorController elevatorController = selectionStrategy.selectElevator(elevatorControllers, request);
        elevatorController.addFloorRequest(request.getFloor());
    }

    public void addInternalRequest(InternalRequest request) {
        ElevatorController elevatorController = elevatorControllers.stream()
                .filter(elevator -> elevator.getId() == request.getElevatorId()).findFirst().orElseThrow();
        elevatorController.addFloorRequest(request.getFloor());
    }

}
