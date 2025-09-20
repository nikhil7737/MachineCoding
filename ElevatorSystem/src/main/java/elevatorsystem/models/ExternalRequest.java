package elevatorsystem.models;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExternalRequest {

    private int floor;
    private Direction direction;
}
