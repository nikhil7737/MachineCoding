package org.ad.engine.services.AdFilter;

import org.ad.engine.models.AdCampaign;
import org.ad.engine.models.AdFilter;
import org.ad.engine.models.User;

public class AgeAdFilter implements IAdFilter{
    @Override
    public boolean isMatch(AdCampaign adCampaign, AdFilter adFilter) {
        User user = adFilter.getUser();
        return (adCampaign.getMinAge() == null || adCampaign.getMinAge() <= user.getAge()) &&
                (adCampaign.getMaxAge() == null || adCampaign.getMaxAge() >= user.getAge());
    }
}
