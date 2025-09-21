package org.example.services;

import org.example.models.Capacity;
import org.example.models.Cluster;
import org.example.models.Job;
import org.example.models.JobStatus;
import org.example.services.strategies.IClusterAllocationStrategy;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

public class JobSchedulerService implements IJobCompletionListener {

    private final IClusterAllocationStrategy allocationStrategy;
    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);
    private final AtomicInteger jobIdGenerator = new AtomicInteger(0);

    private final Map<Integer, Job> jobMap = new ConcurrentHashMap<>();

    private final PriorityBlockingQueue<Cluster> clusters = new PriorityBlockingQueue<>(
            10,
            (a, b) -> {
                if (a.availableCapacity.cpu != b.availableCapacity.cpu) {
                    return a.availableCapacity.cpu < b.availableCapacity.cpu ? 1 : -1;
                }
                if (a.availableCapacity.ram != b.availableCapacity.ram) {
                    return a.availableCapacity.ram < b.availableCapacity.ram ? 1 : -1;
                }
                return 0;

            }
    );

    public JobSchedulerService(IClusterAllocationStrategy allocationStrategy) {
        this.allocationStrategy = allocationStrategy;
    }

    public void addJob(int submittedBy, int cpu, long ram, long executionTimeInSeconds) {
        Job job = new Job();
        job.submittedBy = submittedBy;
        job.id = jobIdGenerator.incrementAndGet();
        job.capacityRequirement = new Capacity(cpu, ram);
        job.executionTimeInSeconds = executionTimeInSeconds;

        Optional<Cluster> cluster = allocationStrategy.tryReservingCluster(clusters, job, this);
        if (cluster.isPresent()) {
            job.cluster = cluster.get();
            updateCluster(cluster.get());
            jobMap.put(job.id, job);
            System.out.println("Job " + job.id + " submitted on cluster: " + cluster.get().id);

        } else {
            System.out.println("No cluster found for job " + job.id);
        }
    }

    @Override
    public void onJobCompleted(Job job) {
        updateCluster(job.cluster);
        job.jobStatus = JobStatus.FINISHED;
        System.out.println("Job " + job.id + " finished on cluster: " + job.cluster.id);
    }

    private void updateCluster(Cluster cluster) {
        clusters.remove(cluster);
        clusters.add(cluster);
    }

}

