package com.laba2.models;

import java.util.Objects;

public class Employee {

    private int id;
    private String firstname;
    private String lastname;
    private int project;
    private int job;
    private int salary;

    public Employee() {
        super();
    }

    public Employee(int id, String firstname, String lastname, int project, int job, int salary) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.project = project;
        this.job = job;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getProject() {
        return project;
    }

    public void setProject(int project) {
        this.project = project;
    }

    public int getJob() {
        return job;
    }

    public void setJob(int job) {
        this.job = job;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && project == employee.project && job == employee.job && salary == employee.salary && Objects.equals(firstname, employee.firstname) && Objects.equals(lastname, employee.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, project, job, salary);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", project=" + project +
                ", job=" + job +
                ", salary=" + salary +
                '}';
    }
}

