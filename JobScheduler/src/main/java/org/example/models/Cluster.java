package org.example.models;

import org.example.services.JobSchedulerService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cluster {

    public int id;
    public Capacity totalCapacity;
    public Capacity availableCapacity;
    public List<Job> liveJobs = new ArrayList<>();
    public Lock lock = new ReentrantLock();


    public Cluster(int id, Capacity totalCapacity) {
        this.id = id;
        this.totalCapacity = totalCapacity;
        this.availableCapacity = new Capacity(totalCapacity.cpu, totalCapacity.ram);
    }

    public boolean tryAddingJob(Job job, JobSchedulerService jobSchedulerService) {
        lock.lock();
        try {
            if (availableCapacity.cpu >= job.capacityRequirement.cpu &&
                    availableCapacity.ram >= job.capacityRequirement.ram) {
                liveJobs.add(job);
                subtractAvailableCapacity(job.capacityRequirement);
                startJobExecution(job, jobSchedulerService);
                return true;
            }
        }
        finally {
            lock.unlock();
        }
        return false;
    }

    private void startJobExecution(Job job, JobSchedulerService jobSchedulerService) {
        System.out.println("job started executing: " + job.id);
        //wait for execution
        addAvailableCapacity(job.capacityRequirement);
        jobSchedulerService.markJobAsCompleted(job);

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
