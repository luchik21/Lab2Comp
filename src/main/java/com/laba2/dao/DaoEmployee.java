package com.laba2.dao;


import com.laba2.models.Employee;
import java.util.List;

public interface DaoEmployee {

    List<Employee> selectAllEmployee();

    Employee findById(int id);

    List<Employee> findByProjectId(int id);

    List<Employee> findByJobId(int id);

    void editEmployee(Employee employee);

    void deleteEmployee(int id);

    void createEmployee(Employee employee);
}

