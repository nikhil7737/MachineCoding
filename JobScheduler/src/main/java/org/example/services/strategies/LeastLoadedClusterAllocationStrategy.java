package org.example.services.strategies;

import org.example.models.Cluster;
import org.example.models.Job;
import org.example.services.ClusterService;
import org.example.services.JobSchedulerService;

import java.util.Optional;
import java.util.TreeSet;

public class LeastLoadedClusterAllocationStrategy implements IClusterAllocationStrategy {

    private final ClusterService clusterService;

    public LeastLoadedClusterAllocationStrategy(ClusterService clusterService) {
        this.clusterService = clusterService;
    }

    @Override
    public Optional<Cluster> tryReservingCluster(TreeSet<Cluster> clusters, Job job, JobSchedulerService jobSchedulerService) {
        for (Cluster cluster : clusters) {
            if (cluster.tryAddingJob(job, jobSchedulerService)) {
                return Optional.of(cluster);
            }
        }
        return Optional.empty();
    }
}
