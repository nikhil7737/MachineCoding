package org.example.services;

import org.example.models.Capacity;
import org.example.models.Cluster;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ClusterService {

    private ConcurrentHashMap<Integer, Cluster> clusterMap = new ConcurrentHashMap<>();

    public void addCluster(int cpu, long ram) {
        int clusterId = clusterMap.size() + 1;
        Cluster cluster = new Cluster(
                clusterId,
                new Capacity(cpu, ram)
        );
        clusterMap.put(clusterId, cluster);
    }
    


}
