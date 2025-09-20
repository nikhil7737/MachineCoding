package org.ad.engine.services;

import org.ad.engine.models.Advertiser;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AdvertiserService {

    private final Map<String, Advertiser> advertiserMap = new ConcurrentHashMap<>();

    public String addAdvertiser(String advertiserName) {
        String adId = IdGenerator.generate();
        advertiserMap.put(adId, new Advertiser(adId, advertiserName));
        return adId;
    }

    public void addAdvertiserBudget(String advertiserId, Double budget) {
        if (!advertiserMap.containsKey(advertiserId)) {
            throw new IllegalArgumentException("invalid advertiser id");
        }
        advertiserMap.get(advertiserId).addBudget(budget);
    }

    public Advertiser getAdvertiserById(String id) {
        return advertiserMap.get(id);
    }

}
