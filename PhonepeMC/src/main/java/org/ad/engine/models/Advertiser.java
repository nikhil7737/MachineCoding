package org.ad.engine.models;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Advertiser {

    private final String id;
    private String name;
    private Double budget;
    private final Lock lock = new ReentrantLock();

    public Advertiser(String id, String name) {
        this.id = id;
        this.name = name;
        this.budget = 0D;
    }

    public void addBudget(Double value) {
        lock.lock();
        budget += value;
        lock.unlock();

    }

    public boolean tryDeductingBudget(Double bidAmount) {
        lock.lock();
        boolean isEnough = false;
        if (budget >= bidAmount) {
            budget -= bidAmount;
            isEnough = true;
        }
        lock.unlock();
        return isEnough;
    }

}


//Advertiser table: mysql
//User table: mysql, dynamodb(kv store)
// AdCampaign: dynamodb


//ad1: 455
//ad1 656

//select count(ad) where ts >= (last 5 min ts)