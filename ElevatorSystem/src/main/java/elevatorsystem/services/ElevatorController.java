package elevatorsystem.services;

import elevatorsystem.models.ElevatorCar;
import elevatorsystem.strategy.IFloorSelectionStrategy;
import lombok.Getter;

import java.util.TreeSet;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ElevatorController {

    @Getter
    private final int id;
    private final Lock lock;
    private final Condition notEmpty;

    @Getter
    private final TreeSet<Integer> floorRequests;
    @Getter
    private final ElevatorCar elevatorCar;

    private final IFloorSelectionStrategy floorSelectionStrategy;

    public ElevatorController(int id, ElevatorCar elevatorCar, IFloorSelectionStrategy floorSelectionStrategy) {
        this.id = id;
        Thread worker = new Thread(this::processRequests);
        lock = new ReentrantLock();
        notEmpty = lock.newCondition();
        this.elevatorCar = elevatorCar;
        this.floorSelectionStrategy = floorSelectionStrategy;
        floorRequests = new TreeSet<>();

        worker.start();
    }

    public void addFloorRequest(int floor) {
        lock.lock();
        try {
            floorRequests.add(floor);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    private void processRequests() {
        int nextFloor = -1;
        lock.lock();
        try {
            while (floorRequests.isEmpty()) {
                notEmpty.await();
            }
            nextFloor = floorSelectionStrategy.getNextFloor(this);
            floorRequests.remove(nextFloor);
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        finally {
            lock.unlock();
        }

        if (nextFloor != -1) {
            elevatorCar.move(nextFloor);
        }
    }

}
