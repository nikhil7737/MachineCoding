package org.ad.engine.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@AllArgsConstructor
public class AdFilter {

    private User user;
    private Map<String, ArrayDeque<String>> latestAdsSeenByUser;
    private Map<String, ArrayDeque<Long>> latestAdImpressionsByAdId;

}
