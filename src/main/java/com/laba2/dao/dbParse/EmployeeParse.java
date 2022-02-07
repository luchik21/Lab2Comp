package com.laba2.dao.dbParse;

import com.laba2.models.Employee;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeParse {

    Logger logger = Logger.getLogger(EmployeeParse.class);

    private Employee employee;

    public List<Employee> getAllEmployee(ResultSet resultSet) {
        List<Employee> employeeList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                employeeList.add(getEmployee(resultSet));
            }
        } catch (SQLException e) {
            logger.error("SQLException in getAll"+e.getMessage());
        }
        return employeeList;
    }

    public Employee getEmployee(ResultSet resultSet) {
        try {
            int id = resultSet.getInt(ColumnLabel.EMPLOYEE_ID);
            String firstname = resultSet.getString(ColumnLabel.FIRSTNAME);
            String lastname = resultSet.getString(ColumnLabel.LASTNAME);
            int project = resultSet.getInt(ColumnLabel.PROJECT_ID);
            int job = resultSet.getInt(ColumnLabel.JOB_ID);
            int salary = resultSet.getInt(ColumnLabel.SALARY);
            employee = new Employee(id, firstname, lastname, project, job, salary);

        } catch (SQLException e) {
            logger.error("SQLException in get"+e.getMessage());
        }
        return employee;
    }

    public Employee getOneEmployee(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                employee = getEmployee(resultSet);
            }
        } catch (SQLException e) {
            logger.error("SQLException in getOne"+e.getMessage());
        }
        return employee;
    }
}
