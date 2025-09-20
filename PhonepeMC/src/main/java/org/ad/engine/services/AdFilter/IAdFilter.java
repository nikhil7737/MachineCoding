package org.ad.engine.services.AdFilter;

import org.ad.engine.models.AdCampaign;
import org.ad.engine.models.AdFilter;
import org.ad.engine.models.User;

public interface IAdFilter {


    public boolean isMatch(AdCampaign adCampaign, AdFilter adFilter);

}
