package com.laba2.models;

import java.util.Objects;

public class Customer {

    private int id;
    private String companyName;
    private int budget;

    public Customer() {
        super();
    }

    public Customer(int id, String companyName, int budget) {
        this.id = id;
        this.companyName = companyName;
        this.budget = budget;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id && budget == customer.budget && Objects.equals(companyName, customer.companyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyName, budget);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", budget=" + budget +
                '}';
    }
}

