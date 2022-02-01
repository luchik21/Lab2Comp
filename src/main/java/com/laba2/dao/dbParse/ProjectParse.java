package com.laba2.dao.dbParse;

import com.laba2.models.Project;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProjectParse {

    Logger logger = Logger.getLogger(ProjectParse.class);

    private Project project;

    public List<Project> getAllProject(ResultSet resultSet) {
        List<Project> projectList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                projectList.add(getProject(resultSet));
            }
        } catch (SQLException e) {
            logger.error("SQLException in getAll" + e.getMessage());
        }
        return projectList;
    }

    private Project getProject(ResultSet resultSet) {
        try {
            int id = resultSet.getInt("PROJECT_ID");
            String projectName = resultSet.getString("PROJECT_NAME");
            int budget = resultSet.getInt("BUDGET");
            int location = resultSet.getInt("LOCATION_ID");
            int customer = resultSet.getInt("CUSTOMER_ID");
            int requirements = resultSet.getInt("REQUIREMENTS_ID");
            int time = resultSet.getInt("TIME_TO_BUILD");

            project = new Project(id, projectName, budget, location, customer, requirements, time);

        } catch (SQLException e) {
            logger.error("SQLException in get" + e.getMessage());
        }
        return project;
    }

    public Project getOneProject(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                project = getProject(resultSet);
            }
        } catch (SQLException e) {
            logger.error("SQLException in getOne" + e.getMessage());
        }
        return project;
    }
}
