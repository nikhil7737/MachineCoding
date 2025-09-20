package services;

import models.Meeting;
import models.MeetingRoom;
import models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MeetingSchedulerService {

    private final List<MeetingRoom> rooms = new ArrayList<>();
    private final ConcurrentHashMap<String, Lock> roomLocks = new ConcurrentHashMap<>();

    private final List<IObserver> observers;

    public MeetingSchedulerService(List<IObserver> observers, int roomCount) {
        this.observers = observers;
        for (int i = 0; i < roomCount; i++) {
            rooms.add(new MeetingRoom(UUID.randomUUID().toString()));
        }
    }

    public List<MeetingRoom> fetchAvailableMeetingRooms(Long startTime, Long endTime) {
        return rooms.stream().filter(room -> room.isAvailable(startTime, endTime)).toList();
    }

    public boolean bookMeetingRoom(MeetingRoom room, Long startTime, Long endTime, User bookedBy, List<User> users) {
        roomLocks.putIfAbsent(room.getId(), new ReentrantLock());
        Lock lock = roomLocks.get(room.getId());
        lock.lock();
        boolean isBooked = false;
        Meeting meeting = null;
        try {
            if (room.isAvailable(startTime, endTime)) {
                meeting = new Meeting(UUID.randomUUID().toString(), startTime, endTime, bookedBy, users, room);
                room.addMeeting(meeting);
                isBooked = true;
            }
        }
        finally {
            lock.unlock();
        }

        if (isBooked) {
            notifyMeetingCreated(meeting);
        }
        return isBooked;
    }

    private void notifyMeetingCreated(Meeting meeting) {

        observers.forEach(observer -> {
            observer.notify(meeting);
        });
    }

}
