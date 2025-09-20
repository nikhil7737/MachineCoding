package services;

import models.Meeting;

public class EmailNotificationService implements INotificationService{
    @Override
    public void notify(Meeting meeting) {

        meeting.getGuests().forEach(guest -> {
            System.out.println("Notifying user for meeting");
        });

    }
}
