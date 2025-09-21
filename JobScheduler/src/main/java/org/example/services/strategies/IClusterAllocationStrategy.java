package org.example.services.strategies;

import org.example.models.Cluster;
import org.example.models.Job;
import org.example.services.JobSchedulerService;

import java.util.Optional;
import java.util.TreeSet;

public interface IClusterAllocationStrategy {

    Optional<Cluster> tryReservingCluster(TreeSet<Cluster> clusters, Job job, JobSchedulerService jobSchedulerService);

}
