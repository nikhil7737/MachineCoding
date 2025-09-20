package models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
@AllArgsConstructor
public class Meeting {

    private String id;
    private long startTime;
    private long endTime;
    private User scheduledBy;
    private List<User> guests = new ArrayList<>();
    private MeetingRoom assignedRoom;

}
