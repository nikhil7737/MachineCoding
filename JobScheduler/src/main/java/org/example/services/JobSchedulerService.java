package org.example.services;

import org.example.models.Capacity;
import org.example.models.Cluster;
import org.example.models.Job;
import org.example.models.JobStatus;
import org.example.services.strategies.IClusterAllocationStrategy;

import java.util.Map;
import java.util.Optional;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

public class JobSchedulerService {

    private final IClusterAllocationStrategy allocationStrategy;

    Map<Integer, Job> jobMap = new ConcurrentHashMap<>();

    TreeSet<Cluster> clusters = new TreeSet<>((a, b) -> {
        if (a.availableCapacity.cpu != b.availableCapacity.cpu) {
            return a.availableCapacity.cpu < b.availableCapacity.cpu ? 1 : -1;
        }
        if (a.availableCapacity.ram != b.availableCapacity.ram) {
            return a.availableCapacity.ram < b.availableCapacity.ram ? 1 : -1;
        }
        return 0;
    });

    public JobSchedulerService(IClusterAllocationStrategy allocationStrategy) {
        this.allocationStrategy = allocationStrategy;
    }


    public void addJob(int submittedBy, int cpu, long ram, long executionTimeInSeconds) {
        Job job = new Job();
        job.submittedBy = submittedBy;
        job.id = jobMap.size() + 1;
        job.capacityRequirement = new Capacity(cpu, ram);
        job.executionTimeInSeconds = executionTimeInSeconds;

        Optional<Cluster> cluster = allocationStrategy.tryReservingCluster(clusters, job, this);
        if (cluster.isPresent()) {
            job.cluster = cluster.get();
            updateAvailableCapacity(job.cluster);
            jobMap.put(job.id, job);
            System.out.println("Job submitted on cluster: " + cluster);
        }
        else {
            System.out.println("No cluster found");
        }
    }

    public void markJobAsCompleted(Job job) {
        updateAvailableCapacity(job.cluster);
        job.jobStatus = JobStatus.FINISHED;
    }


    private void updateAvailableCapacity(Cluster cluster) {
        clusters.remove(cluster);
        clusters.add(cluster);
    }

}
