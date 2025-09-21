package org.example.services.strategies;

import org.example.models.Cluster;
import org.example.models.Job;
import org.example.services.ClusterService;
import org.example.services.IJobCompletionListener;
import org.example.services.JobSchedulerService;

import java.util.Optional;
import java.util.TreeSet;
import java.util.concurrent.PriorityBlockingQueue;

public class LeastLoadedClusterAllocationStrategy implements IClusterAllocationStrategy {

    @Override
    public Optional<Cluster> tryReservingCluster(PriorityBlockingQueue<Cluster> clusters, Job job, IJobCompletionListener jobCompletionListener) {
        for (Cluster cluster : clusters) {
            if (cluster.tryAddingJob(job, jobCompletionListener)) {
                return Optional.of(cluster);
            }
        }
        return Optional.empty();
    }
}
