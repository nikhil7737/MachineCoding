package org.ad.engine.models;


import lombok.Builder;
import lombok.Getter;
import org.ad.engine.models.enums.AdCategory;
import org.ad.engine.models.enums.AdType;

import java.util.List;

@Getter
@Builder
public class AdCampaign {

    private String id;
    private double bidAmount;
    private String advertiserId;
    private String url;
    private AdType adType;
    private Integer minAge;
    private Integer maxAge;
    private AdCategory adCategory;
    private List<String> applicableGenders;
    private List<String> applicableCities;

}
