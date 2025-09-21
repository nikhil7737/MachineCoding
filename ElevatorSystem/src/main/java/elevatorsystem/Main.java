package elevatorsystem;

import elevatorsystem.models.Direction;
import elevatorsystem.models.ElevatorCar;
import elevatorsystem.models.ExternalRequest;
import elevatorsystem.models.InternalRequest;
import elevatorsystem.services.ElevatorController;
import elevatorsystem.services.ElevatorSystem;
import elevatorsystem.strategy.impls.NearestFloorStrategy;
import elevatorsystem.strategy.impls.QuickestElevatorSelectionStrategy;

import java.util.List;
import java.util.TreeSet;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        ElevatorCar car = new ElevatorCar(1);
        ElevatorController controller = new ElevatorController(1, car, new NearestFloorStrategy());
        ElevatorSystem system = new ElevatorSystem(
                List.of(controller),
                new QuickestElevatorSelectionStrategy()
        );

        int elevatorId = system.addExternalRequest(new ExternalRequest(5, Direction.UP));
        system.addExternalRequest(new ExternalRequest(3, Direction.UP));
        system.addExternalRequest(new ExternalRequest(7, Direction.UP));
        system.addInternalRequest(new InternalRequest(4, elevatorId));

        try {
            Thread.currentThread().sleep(60000);
            system.addInternalRequest(new InternalRequest(2, elevatorId));
        }
        catch (Exception e) {

        }

    }
}