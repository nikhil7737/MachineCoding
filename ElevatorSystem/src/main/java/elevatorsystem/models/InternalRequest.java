package elevatorsystem.models;


import lombok.Getter;

@Getter
public class InternalRequest {

    int floor;
    int elevatorId;

    public InternalRequest(int floor, int elevatorId) {
        this.floor = floor;
        this.elevatorId = elevatorId;
    }

}
