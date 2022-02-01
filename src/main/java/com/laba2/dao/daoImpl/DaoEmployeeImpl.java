package com.laba2.dao.daoImpl;

import com.laba2.dao.DaoEmployee;
import com.laba2.dao.DbInterface;
import com.laba2.dao.dbParse.EmployeeParse;
import com.laba2.models.Employee;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class DaoEmployeeImpl implements DaoEmployee {

    Logger logger = Logger.getLogger(DaoEmployeeImpl.class);

    private Employee employee;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private List<Employee> employeeList = new ArrayList<>();

    private DbInterface dbInterface;

    @Autowired
    public void setDbInterface(DbInterface dbInterface) {
        this.dbInterface = dbInterface;
    }

    private EmployeeParse employeeParse;

    @Autowired
    public void setEmployeeParse(EmployeeParse employeeParse) {
        this.employeeParse = employeeParse;
    }

    private void close() {
        try {
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            logger.error("Error in close"+e.getMessage());
        }
    }

    @Override
    public List<Employee> selectAllEmployee() {
        try (Connection connection = dbInterface.getConnection()) {
            logger.debug("call selectAllEmployee()");
            preparedStatement = connection.prepareStatement("SELECT * FROM LAB2_ZV_EMPLOYEE ORDER BY EMPLOYEE_ID");
            resultSet = preparedStatement.executeQuery();
            employeeList = employeeParse.getAllEmployee(resultSet);
            return employeeList;
        } catch (SQLException e) {
            logger.error("SQLException in selectAll"+e.getMessage());
        } finally {
            close();
        }
        return employeeList;
    }

    @Override
    public Employee findById(int id) {
        try (Connection connection = dbInterface.getConnection()) {
            logger.debug("call findById()");
            preparedStatement = connection.prepareStatement("SELECT * FROM LAB2_ZV_EMPLOYEE WHERE EMPLOYEE_ID = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            employee = employeeParse.getOneEmployee(resultSet);
            return employee;
        } catch (SQLException e) {
            logger.error("SQLException in findById"+e.getMessage());
        } finally {
            close();
        }
        return employee;
    }

    @Override
    public List<Employee> findByProjectId(int id) {
        try (Connection connection = dbInterface.getConnection()) {
            logger.debug("call findByProjectId()");
            preparedStatement = connection.prepareStatement("SELECT * FROM LAB2_ZV_EMPLOYEE WHERE PROJECT_ID = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            employeeList = employeeParse.getAllEmployee(resultSet);
            System.out.println(employeeList);
            return employeeList;
        } catch (SQLException e) {
            logger.error("SQLException in findByProjectId"+e.getMessage());
        } finally {
            close();
        }
        return employeeList;
    }

    @Override
    public List<Employee> findByJobId(int id) {
        try (Connection connection = dbInterface.getConnection()) {
            logger.debug("call findByJobId()");
            preparedStatement = connection.prepareStatement("SELECT * FROM LAB2_ZV_EMPLOYEE WHERE JOB_ID = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            employeeList = employeeParse.getAllEmployee(resultSet);
            return employeeList;
        } catch (SQLException e) {
            logger.error("SQLException in findByJobId"+e.getMessage());
        } finally {
            close();
        }
        return employeeList;
    }

    @Override
    public void editEmployee(Employee employee) {
        try (Connection connection = dbInterface.getConnection()) {
            logger.debug("call editEmployee()");
            preparedStatement = connection.prepareStatement("UPDATE LAB2_ZV_EMPLOYEE" +
                    " SET FIRSTNAME = ?,LASTNAME = ?, PROJECT_ID = ?, JOB_ID = ?, SALARY = ? WHERE EMPLOYEE_ID = ?");
            preparedStatement.setString(1, employee.getFirstname());
            preparedStatement.setString(2, employee.getLastname());
            preparedStatement.setInt(3, employee.getProject());
            preparedStatement.setInt(4, employee.getJob());
            preparedStatement.setInt(5, employee.getSalary());
            preparedStatement.setInt(6, employee.getId());

            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            logger.error("SQLException in editEmployee() "+e.getMessage());
        } finally {
            close();
        }
    }

    @Override
    public void deleteEmployee(int id) {
        try (Connection connection = dbInterface.getConnection()) {
            logger.debug("call deleteEmployee()");
            preparedStatement = connection.prepareStatement("DELETE LAB2_ZV_EMPLOYEE WHERE EMPLOYEE_ID = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            logger.error("SQLException in delete");
        } finally {
            close();
        }
    }

    @Override
    public void createEmployee(Employee employee) {
        try (Connection connection = dbInterface.getConnection()) {
            logger.debug("call createEmployee()");
            preparedStatement = connection.prepareStatement("INSERT INTO LAB2_ZV_EMPLOYEE " +
                    "(EMPLOYEE_ID, FIRSTNAME, LASTNAME, PROJECT_ID, JOB_ID, SALARY)  " +
                    "VALUES (LAB2_ZV_EMPLOYEE_SEQ.nextval, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, employee.getFirstname());
            preparedStatement.setString(2, employee.getLastname());
            preparedStatement.setInt(3, employee.getProject());
            preparedStatement.setInt(4, employee.getJob());
            preparedStatement.setInt(5, employee.getSalary());
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            logger.error("SQLException in create"+e.getMessage());
        } finally {
            close();
        }
    }
}
