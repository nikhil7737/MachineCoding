package org.example.models;

public class Job {

    public int id;
    public Capacity capacityRequirement;
    public long executionTimeInSeconds;
    public int submittedBy;
    public int submittedAt;
    public Cluster cluster;
    public JobStatus jobStatus;

}
