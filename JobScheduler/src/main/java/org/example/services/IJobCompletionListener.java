package org.example.services;

import org.example.models.Cluster;
import org.example.models.Job;

public interface IJobCompletionListener {


    void onJobCompleted(Job job);
}
