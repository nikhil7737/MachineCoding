package org.ad.engine.services.AdFilter;

import org.ad.engine.models.AdCampaign;
import org.ad.engine.models.AdFilter;

import java.util.ArrayDeque;

public class GlobalAdImpressionFilter implements IAdFilter {

    private final int windowSize = 60000;

    @Override
    public boolean isMatch(AdCampaign adCampaign, AdFilter adFilter) {
        String adCampaignId = adCampaign.getId();
        if (!adFilter.getLatestAdImpressionsByAdId().containsKey(adCampaignId)) {
            return true;
        }

        ArrayDeque<Long> recent = adFilter.getLatestAdImpressionsByAdId().get(adCampaignId);
        long current = System.currentTimeMillis();
        while (recent.getFirst() < current - windowSize) {
            recent.removeFirst();
        }

        return recent.size() < 5;

    }
}
