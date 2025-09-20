package org.ad.engine.services.AdFilter;

import org.ad.engine.models.AdCampaign;
import org.ad.engine.models.AdFilter;

import java.util.ArrayDeque;

public class AdSeenByUserFilter implements IAdFilter {
    private static final int recentImpressionCount = 10;

    @Override
    public boolean isMatch(AdCampaign adCampaign, AdFilter adFilter) {
        String userId = adFilter.getUser().getId();
        if (!adFilter.getLatestAdsSeenByUser().containsKey(userId)) {
            return true;
        }

        ArrayDeque<String> latestAds = adFilter.getLatestAdsSeenByUser().get(userId);
        while (latestAds.size() > recentImpressionCount) {
            latestAds.removeFirst();
        }

        return !latestAds.contains(adCampaign.getId());


    }
}
