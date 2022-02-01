package com.laba2.models;


import java.util.Objects;

public class Job {

    private int id;
    private String jobName;
    private int salary;
    private int premium;

    public Job() {
        super();
    }

    public Job(int id, String jobName, int salary, int premium) {
        this.id = id;
        this.jobName = jobName;
        this.salary = salary;
        this.premium = premium;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getPremium() {
        return premium;
    }

    public void setPremium(int premium) {
        this.premium = premium;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return id == job.id && salary == job.salary && premium == job.premium && Objects.equals(jobName, job.jobName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, jobName, salary, premium);
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", name='" + jobName + '\'' +
                ", salary=" + salary +
                ", premium=" + premium +
                '}';
    }
}