package org.ad.engine.services;

import lombok.Getter;
import org.ad.engine.models.AdCampaign;
import org.ad.engine.models.enums.AdCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class AdCampaignService {

    private final Map<String, AdCampaign> adCampaignMap = new ConcurrentHashMap<>();

    @Getter
    private final List<AdCampaign> adCampaigns = new ArrayList<>();

    public void addCampaign(String advertiserId, Double bidAmount, String url, Integer minAge, Integer maxAge,
                            AdCategory adCategory, List<String> cities) {
        String campaignId = IdGenerator.generate();
        AdCampaign adCampaign = AdCampaign.builder()
                .id(campaignId)
                .advertiserId(advertiserId)
                .bidAmount(bidAmount)
                .url(url)
                .minAge(minAge)
                .maxAge(maxAge)
                .adCategory(adCategory)
                .applicableCities(cities)
                .build();

        adCampaignMap.put(campaignId, adCampaign);
        adCampaigns.add(adCampaign);
    }


}
