package models;

import lombok.Getter;

import java.util.*;

@Getter
public class MeetingRoom {

    private final String id;
    private final TreeMap<Long, Meeting> meetings = new TreeMap<>();

    public MeetingRoom(String id) {
        this.id = id;
    }

    public boolean isAvailable(Long startTime, Long endTime) {
        Map.Entry<Long, Meeting> firstMeeting = meetings.lowerEntry(startTime);
        Map.Entry<Long, Meeting> secondMeeting = meetings.ceilingEntry(startTime);
        boolean isFirstMeetingNonOverlapping = firstMeeting == null || firstMeeting.getValue().getEndTime() <= startTime;
        boolean isSecondMeetingNonOverlapping = secondMeeting == null || endTime <=  secondMeeting.getValue().getStartTime();
        return isFirstMeetingNonOverlapping && isSecondMeetingNonOverlapping;

    }

    public void addMeeting(Meeting meeting) {
        meetings.put(meeting.getStartTime(), meeting);
    }

}
