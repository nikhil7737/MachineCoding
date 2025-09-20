package services;

import models.Meeting;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class MeetingSearchService implements IObserver {

    private final ConcurrentHashMap<String, List<Meeting>> meetingsByUserId = new ConcurrentHashMap<>();


    @Override
    public void notify(Meeting meeting) {
        meeting.getGuests().forEach(guest -> {
            meetingsByUserId.putIfAbsent(guest.getId(), new ArrayList<>());
            meetingsByUserId.get(guest.getId()).add(meeting);
        });
    }

    public List<Meeting> fetchMeetingsByUserId(String userId) {
        return meetingsByUserId.getOrDefault(userId, new ArrayList<>());
    }

}
