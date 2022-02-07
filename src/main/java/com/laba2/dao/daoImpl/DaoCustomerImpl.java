package com.laba2.dao.daoImpl;

import com.laba2.dao.DaoCustomer;
import com.laba2.dao.DbInterface;
import com.laba2.dao.dbParse.CustomerParse;
import com.laba2.models.Customer;
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
public class DaoCustomerImpl implements DaoCustomer {

    Logger logger = Logger.getLogger(DaoCustomerImpl.class);

    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private List<Customer> customerList = new ArrayList<>();

    private DbInterface dbInterface;

    @Autowired
    public void setDbInterface(DbInterface dbInterface) {
        this.dbInterface = dbInterface;
    }

    private CustomerParse customerParse;

    @Autowired
    public void setCustomerParse(CustomerParse customerParse) {
        this.customerParse = customerParse;
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
    public List<Customer> selectAllCustomers() {
        try (Connection connection = dbInterface.getConnection()) {
            logger.debug("call selectAllCustomers()");
            preparedStatement = connection.prepareStatement(SqlQuery.SELECT_ALL_CUSTOMERS);
            resultSet = preparedStatement.executeQuery();
            customerList = customerParse.getAllCustomer(resultSet);
            return customerList;
        } catch (SQLException e) {
            logger.error("SQLException in selectAll"+e.getMessage());
        } finally {
            close();
        }
        return customerList;
    }

    @Override
    public void deleteCustomer(int id) {
        try (Connection connection = dbInterface.getConnection()) {
            logger.debug("call deleteCustomer()");
            preparedStatement = connection.prepareStatement(SqlQuery.DELETE_CUSTOMER);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            logger.error("SQLException in delete"+e.getMessage());
        } finally {
            close();
        }
    }

    @Override
    public void createCustomer(Customer customer) {
        try (Connection connection = dbInterface.getConnection()) {
            logger.debug("call  createCustomer()");
            preparedStatement = connection.prepareStatement(SqlQuery.CREATE_CUSTOMER);
            preparedStatement.setString(1, customer.getCompanyName());
            preparedStatement.setInt(2, customer.getBudget());
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            logger.error("SQLException in create"+e.getMessage());
        } finally {
            close();
        }
    }
}
