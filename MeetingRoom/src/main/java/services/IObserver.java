package services;

import models.Meeting;

public interface IObserver {

    void notify(Meeting meeting);

}
