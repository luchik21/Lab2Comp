package com.laba2.dao;

import com.laba2.models.Job;

import java.util.List;

public interface DaoJob {

    List<Job> selectAllJob();

    Job findById(int id);

    void editJob(Job job);

    void deleteJob(int id);

    void createJob(Job job);
}
