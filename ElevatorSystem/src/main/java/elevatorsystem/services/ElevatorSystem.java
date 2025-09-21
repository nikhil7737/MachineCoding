package elevatorsystem.services;

import elevatorsystem.models.ExternalRequest;
import elevatorsystem.models.InternalRequest;
import elevatorsystem.strategy.IElevatorSelectionStrategy;

import java.util.List;

public class ElevatorSystem {

    private final List<ElevatorController> elevatorControllers;

    private final IElevatorSelectionStrategy selectionStrategy;

    public ElevatorSystem(List<ElevatorController> elevatorControllers, IElevatorSelectionStrategy selectionStrategy) {
        this.elevatorControllers = elevatorControllers;
        this.selectionStrategy = selectionStrategy;
    }


    public int addExternalRequest(ExternalRequest request) {
        ElevatorController elevatorController = selectionStrategy.selectElevator(elevatorControllers, request);
        elevatorController.addFloorRequest(request.getFloor());
        return elevatorController.getId();
    }

    public void addInternalRequest(InternalRequest request) {
        ElevatorController elevatorController = elevatorControllers.stream()
                .filter(elevator -> elevator.getId() == request.getElevatorId()).findFirst().orElseThrow();
        elevatorController.addFloorRequest(request.getFloor());
    }

}
