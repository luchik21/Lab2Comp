package com.laba2.dao.daoImpl;

import com.laba2.dao.DaoLocation;
import com.laba2.dao.DbInterface;
import com.laba2.dao.dbParse.LocationParse;
import com.laba2.models.Location;
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
public class DaoLocationImpl implements DaoLocation {

    Logger logger = Logger.getLogger(DaoLocationImpl.class);

    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private List<Location> locationList = new ArrayList<>();

    private DbInterface dbInterface;

    @Autowired
    public void setDbInterface(DbInterface dbInterface) {
        this.dbInterface = dbInterface;
    }

    private LocationParse locationParse;

    @Autowired
    public void setLocationParse(LocationParse locationParse) {
        this.locationParse = locationParse;
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
    public List<Location> selectAllLocation() {
        try (Connection connection = dbInterface.getConnection()) {
            logger.debug("call selectAllLocation()");
            preparedStatement = connection.prepareStatement(SqlQuery.SELECT_ALL_LOCATION);
            resultSet = preparedStatement.executeQuery();
            locationList = locationParse.getAllLocation(resultSet);
            return locationList;
        } catch (SQLException e) {
            logger.error("SQLException in selectAll" + e.getMessage());
        } finally {
            close();
        }
        return locationList;
    }

    @Override
    public void deleteLocation(int id) {
        try (Connection connection = dbInterface.getConnection()) {
            logger.debug("call deleteLocation()");
            preparedStatement = connection.prepareStatement(SqlQuery.DELETE_LOCATION);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            logger.error("SQLException in delete" + e.getMessage());
        } finally {
            close();
        }
    }

    @Override
    public void createLocation(Location location) {
        try (Connection connection = dbInterface.getConnection()) {
            logger.debug("call createLocation()");
            preparedStatement = connection.prepareStatement(SqlQuery.CREATE_LOCATION);
            preparedStatement.setString(1, location.getCity());
            preparedStatement.setString(2, location.getAddress());
            preparedStatement.setInt(3, location.getArea());
            preparedStatement.setString(4, location.getPermission());
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            logger.error("SQLException in create" + e.getMessage());
        } finally {
            close();
        }
    }
}
