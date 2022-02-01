package com.laba2.dao;

import com.laba2.models.Customer;

import java.util.List;

public interface DaoCustomer {

    List<Customer> selectAllCustomers();

    void deleteCustomer(int id);

    void createCustomer(Customer customer);
}
