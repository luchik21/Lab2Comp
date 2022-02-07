package com.laba2.dao.daoImpl;

import com.laba2.dao.DaoUser;
import com.laba2.dao.DbInterface;
import com.laba2.dao.dbParse.UserParse;
import com.laba2.models.User;
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
public class DaoUserImpl implements DaoUser {

    Logger logger = Logger.getLogger(DaoUserImpl.class);

    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private List<User> userList = new ArrayList<>();

    private DbInterface dbInterface;

    @Autowired
    public void setDbInterface(DbInterface dbInterface) {
        this.dbInterface = dbInterface;
    }

    private UserParse userParse;

    @Autowired
    public void setUserParse(UserParse userParse) {
        this.userParse = userParse;
    }

    private void close() {
        try {
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            logger.error("Error in close" + e.getMessage());
        }
    }

    @Override
    public List<User> selectAllUser() {
        try (Connection connection = dbInterface.getConnection()) {
            logger.debug("call selectAllUser()");
            preparedStatement = connection.prepareStatement(SqlQuery.SELECT_ALL_USER);
            resultSet = preparedStatement.executeQuery();
            userList = userParse.getAllUser(resultSet);
            return userList;
        } catch (SQLException e) {
            logger.error("SQLException in selectAll" + e.getMessage());
        } finally {
            close();
        }
        return userList;
    }

    @Override
    public void deleteUser(int id) {
        try (Connection connection = dbInterface.getConnection()) {
            logger.debug("call deleteUser()");
            preparedStatement = connection.prepareStatement(SqlQuery.DELETE_USER);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            logger.error("SQLException in delete" + e.getMessage());
        } finally {
            close();
        }
    }

    @Override
    public void createUser(User user) {
        try (Connection connection = dbInterface.getConnection()) {
            logger.debug("call createUser()");
            preparedStatement = connection.prepareStatement(SqlQuery.CREATE_USER);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.setInt(4, user.getEnable());
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            logger.error("SQLException in create" + e.getMessage());
        } finally {
            close();
        }
    }
}
