package com.laba2.dao.dbParse;

import com.laba2.models.Requirements;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class RequirementsParse {

    Logger logger = Logger.getLogger(RequirementsParse.class);

    private Requirements requirements;

    public List<Requirements> getAllRequirements(ResultSet resultSet) {
        List<Requirements> locationList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                locationList.add(getRequirements(resultSet));
            }
        } catch (SQLException e) {
            logger.error("SQLException in getAll" + e.getMessage());
        }
        return locationList;
    }

    private Requirements getRequirements(ResultSet resultSet) {
        try {
            int id = resultSet.getInt(ColumnLabel.REQUIREMENTS_ID);
            String material = resultSet.getString(ColumnLabel.MATERIAL);
            String color = resultSet.getString(ColumnLabel.COLOR);
            String type = resultSet.getString(ColumnLabel.TYPE);
            int floorsCount = resultSet.getInt(ColumnLabel.FLOORS_COUNT);

            requirements = new Requirements(id, material, color, type, floorsCount);

        } catch (SQLException e) {
            logger.error("SQLException in get" + e.getMessage());
        }
        return requirements;
    }
}
