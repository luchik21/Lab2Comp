package com.laba2.models;

import java.util.Objects;

public class Project {

    private int id;
    private String projectName;
    private int budget;
    private int location;
    private int customer;
    private int requirements;
    private int timeToBuild;

    public Project() {
        super();
    }

    public Project(int id, String projectName, int budget, int location, int customer, int requirements, int timeToBuild) {
        this.id = id;
        this.projectName = projectName;
        this.budget = budget;
        this.location = location;
        this.customer = customer;
        this.requirements = requirements;
        this.timeToBuild = timeToBuild;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public int getRequirements() {
        return requirements;
    }

    public void setRequirements(int requirements) {
        this.requirements = requirements;
    }

    public int getTimeToBuild() {
        return timeToBuild;
    }

    public void setTimeToBuild(int timeToBuild) {
        this.timeToBuild = timeToBuild;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id == project.id && budget == project.budget && location == project.location && customer == project.customer && requirements == project.requirements && timeToBuild == project.timeToBuild && Objects.equals(projectName, project.projectName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, projectName, budget, location, customer, requirements, timeToBuild);
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", budget=" + budget +
                ", location=" + location +
                ", customer=" + customer +
                ", requirements=" + requirements +
                ", timeToBuild=" + timeToBuild +
                '}';
    }
}

