package com.laba2.dao.dbParse;


import com.laba2.models.Location;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class LocationParse {

    Logger logger = Logger.getLogger(LocationParse.class);

    private Location location;

    public List<Location> getAllLocation(ResultSet resultSet) {
        List<Location> locationList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                locationList.add(getLocation(resultSet));
            }
        } catch (SQLException e) {
            logger.error("SQLException in getAll" + e.getMessage());
        }
        return locationList;
    }

    private Location getLocation(ResultSet resultSet) {
        try {
            int id = resultSet.getInt(ColumnLabel.LOCATION_ID);
            String city = resultSet.getString(ColumnLabel.CITY);
            String address = resultSet.getString(ColumnLabel.ADDRESS);
            int area = resultSet.getInt(ColumnLabel.AREA);
            String permission = resultSet.getString(ColumnLabel.PERMISSION);

            location = new Location(id, city, address, area, permission);

        } catch (SQLException e) {
            logger.error("SQLException in get" + e.getMessage());
        }
        return location;
    }
}
