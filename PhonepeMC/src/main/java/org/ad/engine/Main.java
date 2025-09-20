package org.ad.engine;

import org.ad.engine.models.AdCampaign;
import org.ad.engine.models.Advertiser;
import org.ad.engine.models.User;
import org.ad.engine.models.enums.AdCategory;
import org.ad.engine.models.enums.Gender;
import org.ad.engine.services.AdCampaignService;
import org.ad.engine.services.AdMatchingService;
import org.ad.engine.services.AdvertiserService;
import org.ad.engine.services.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        AdvertiserService advertiserService = new AdvertiserService();
        UserService userService = new UserService();
        AdCampaignService adCampaignService = new AdCampaignService();
        AdMatchingService adMatchingService = new AdMatchingService(userService, advertiserService, adCampaignService);

        String advertiserId = advertiserService.addAdvertiser("Nikhil");
        advertiserService.addAdvertiserBudget(advertiserId, 500D);

        String userId = userService.addUser("user1", Gender.MALE, 25, "Bangalore");

        List<String> cities = Arrays.asList("Bangalore", "Mumbai");
        adCampaignService.addCampaign(advertiserId, 100D, "url", 15, 45, AdCategory.SPORTS, cities);
        adCampaignService.addCampaign(advertiserId, 200D, "url", 15, 45, AdCategory.SPORTS, cities);
        adCampaignService.addCampaign(advertiserId, 200D, "url", 15, 45, AdCategory.SPORTS, cities);
        adCampaignService.addCampaign(advertiserId, 200D, "url", 15, 45, AdCategory.SPORTS, cities);

        for (int i = 0; i < 4; i++) {
            AdCampaign adCampaign = adMatchingService.fetchCampaignForUser(userId);
            if (adCampaign == null) {
                System.out.println((Object) null);
            }
            else {
                System.out.println(adCampaign.getId());
            }
        }
    }
}