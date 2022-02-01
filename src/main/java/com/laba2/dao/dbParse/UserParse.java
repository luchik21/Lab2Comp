package com.laba2.dao.dbParse;

import com.laba2.models.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserParse {

    Logger logger = Logger.getLogger(UserParse.class);

    private User user;

    public List<User> getAllUser(ResultSet resultSet) {
        List<User> userList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                userList.add(getUser(resultSet));
            }
        } catch (SQLException e) {
            logger.error("SQLException in getAll"+e.getMessage());
        }
        return userList;
    }

    public User getUser(ResultSet resultSet) {
        try {
            int id = resultSet.getInt("EMPLOYEE_ID");
            String username = resultSet.getString("USERNAME");
            String password = resultSet.getString("PASSWORD");
            String role = resultSet.getString("ROLE");
            int enable = resultSet.getInt("ENABLE");
            user = new User(id, username, password, role, enable);

        } catch (SQLException e) {
            logger.error("SQLException in get "+e.getMessage());
        }
        return user;
    }
}
