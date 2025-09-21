package org.example.models;

import org.example.services.IJobCompletionListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cluster {

    public int id;
    public Capacity totalCapacity;
    public Capacity availableCapacity;
    public List<Job> liveJobs = new ArrayList<>();
    private final Lock lock = new ReentrantLock();
    private final ScheduledExecutorService executor;

    public Cluster(int id, Capacity totalCapacity) {
        this.id = id;
        this.totalCapacity = totalCapacity;
        this.availableCapacity = new Capacity(totalCapacity.cpu, totalCapacity.ram);

        this.executor = Executors.newScheduledThreadPool(totalCapacity.cpu);
    }

    public boolean tryAddingJob(Job job, IJobCompletionListener listener) {
        lock.lock();
        try {
            if (availableCapacity.cpu >= job.capacityRequirement.cpu &&
                    availableCapacity.ram >= job.capacityRequirement.ram) {
                liveJobs.add(job);
                subtractAvailableCapacity(job.capacityRequirement);
                startJobExecution(job, listener);
                return true;
            }
        } finally {
            lock.unlock();
        }
        return false;
    }

    private void startJobExecution(Job job, IJobCompletionListener listener) {
        System.out.println("Job " + job.id + " started executing on cluster: " + id);

        executor.schedule(() -> {
            addAvailableCapacity(job.capacityRequirement);
            listener.onJobCompleted(job);
        }, job.executionTimeInSeconds, TimeUnit.SECONDS);
    }

    private void subtractAvailableCapacity(Capacity capacityRequirement) {
        availableCapacity.cpu -= capacityRequirement.cpu;
        availableCapacity.ram -= capacityRequirement.ram;
    }

    private void addAvailableCapacity(Capacity capacityRequirement) {
        availableCapacity.cpu += capacityRequirement.cpu;
        availableCapacity.ram += capacityRequirement.ram;
    }

}
