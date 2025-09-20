package org.ad.engine.services;

import org.ad.engine.models.AdCampaign;
import org.ad.engine.models.AdFilter;
import org.ad.engine.models.Advertiser;
import org.ad.engine.models.User;
import org.ad.engine.services.AdFilter.AdSeenByUserFilter;
import org.ad.engine.services.AdFilter.AgeAdFilter;
import org.ad.engine.services.AdFilter.GlobalAdImpressionFilter;
import org.ad.engine.services.AdFilter.IAdFilter;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class AdMatchingService {

    Map<String, ArrayDeque<String>> latestAdsSeenByUser = new ConcurrentHashMap<>();
    Map<String, ArrayDeque<Long>> latestAdImpressionsByAdId = new ConcurrentHashMap<>();

    List<IAdFilter> adFilters;

    private final UserService userService;
    private final AdvertiserService advertiserService;
    private final AdCampaignService adCampaignService;

    public AdMatchingService(UserService userService, AdvertiserService advertiserService, AdCampaignService adCampaignService) {
        this.userService = userService;
        this.advertiserService = advertiserService;
        this.adCampaignService = adCampaignService;
        adFilters = List.of(new AgeAdFilter(), new AdSeenByUserFilter(), new GlobalAdImpressionFilter());
    }

    public AdCampaign fetchCampaignForUser(String userId) {
        User user = userService.getUserById(userId);
        AdFilter adFilter = new AdFilter(user, latestAdsSeenByUser, latestAdImpressionsByAdId);
        AdCampaign selectedAd = null;

        for (AdCampaign ad : adCampaignService.getAdCampaigns()) {
            boolean isMatch = true;
            for (IAdFilter filter : adFilters) {
                isMatch = filter.isMatch(ad, adFilter);
                if (!isMatch) {
                    break;
                }
            }

            Advertiser advertiser = advertiserService.getAdvertiserById(ad.getAdvertiserId());
            if (isMatch && advertiser.tryDeductingBudget(ad.getBidAmount())) {
                selectedAd = ad;
                break;
            }
        }

        if (selectedAd == null) {
            return null;
        }

        latestAdsSeenByUser.putIfAbsent(userId, new ArrayDeque<>());
        latestAdImpressionsByAdId.putIfAbsent(selectedAd.getId(), new ArrayDeque<>());
        latestAdsSeenByUser.get(userId).addLast(selectedAd.getId());
        latestAdImpressionsByAdId.get(selectedAd.getId()).addLast(System.currentTimeMillis());
        return selectedAd;

    }

}
