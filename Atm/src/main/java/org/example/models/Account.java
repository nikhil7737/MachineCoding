package org.example.models;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {

    public Card card;
    public Double balance;
    public Lock lock;
    public String pin;

    public Account(Card card, String pin) {
        this.card = card;
        balance = 0D;
        this.pin = pin;
        lock = new ReentrantLock();
    }


}
