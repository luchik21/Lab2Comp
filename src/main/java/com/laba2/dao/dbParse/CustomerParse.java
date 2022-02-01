package com.laba2.dao.dbParse;

import com.laba2.models.Customer;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerParse {

    Logger logger = Logger.getLogger(CustomerParse.class);

    private Customer customer;

    public List<Customer> getAllCustomer(ResultSet resultSet) {
        List<Customer> customerList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                customerList.add(getCustomer(resultSet));
            }
        } catch (SQLException e) {
            logger.error("SQLException in getAll"+e.getMessage());
        }
        return customerList;
    }

    private Customer getCustomer(ResultSet resultSet) {
        try {
            int id = resultSet.getInt("CUSTOMER_ID");
            String name = resultSet.getString("COMPANY_NAME");
            int budget = resultSet.getInt("COMPANY_BUDGET");
            customer = new Customer(id, name, budget);
        } catch (SQLException e) {
            logger.error("SQLException in get"+e.getMessage());
        }
        return customer;
    }
}
