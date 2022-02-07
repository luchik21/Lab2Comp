package com.laba2.dao.daoImpl;

import com.laba2.dao.DaoProject;
import com.laba2.dao.DbInterface;

import com.laba2.dao.dbParse.ProjectParse;
import com.laba2.models.Project;
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
public class DaoProjectImpl implements DaoProject {

    Logger logger = Logger.getLogger(DaoProjectImpl.class);

    private Project project;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private List<Project> projectList = new ArrayList<>();

    private DbInterface dbInterface;

    @Autowired
    public void setDbInterface(DbInterface dbInterface) {
        this.dbInterface = dbInterface;
    }

    private ProjectParse projectParse;

    @Autowired
    public void setProjectParse(ProjectParse projectParse) {
        this.projectParse = projectParse;
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
    public List<Project> selectAllProject() {
        try (Connection connection = dbInterface.getConnection()) {
            preparedStatement = connection.prepareStatement(SqlQuery.SELECT_ALL_PROJECT);
            resultSet = preparedStatement.executeQuery();
            projectList = projectParse.getAllProject(resultSet);
            return projectList;
        } catch (SQLException e) {
            logger.error("SQLException in selectAll" + e.getMessage());
        } finally {
            close();
        }
        return projectList;
    }

    @Override
    public Project findById(int id) {
        try (Connection connection = dbInterface.getConnection()) {
            preparedStatement = connection.prepareStatement(SqlQuery.FIND_PROJECT_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            project = projectParse.getOneProject(resultSet);
            return project;
        } catch (SQLException e) {
            logger.error("SQLException in findById" + e.getMessage());
        } finally {
            close();
        }
        return project;
    }

    @Override
    public void editProject(Project project) {
        try (Connection connection = dbInterface.getConnection()) {
            logger.debug("call editProject()");
            preparedStatement = connection.prepareStatement(SqlQuery.EDIT_PROJECT);
            preparedStatement.setString(1, project.getProjectName());
            preparedStatement.setInt(2, project.getBudget());
            preparedStatement.setInt(3, project.getLocation());
            preparedStatement.setInt(4, project.getCustomer());
            preparedStatement.setInt(5, project.getRequirements());
            preparedStatement.setInt(6, project.getTimeToBuild());
            preparedStatement.setInt(7, project.getId());
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            logger.error("SQLException in editEmployee() " + e.getMessage());
        } finally {
            close();
        }
    }

    @Override
    public void createProject(Project project) {
        try (Connection connection = dbInterface.getConnection()) {
            logger.debug("call createProject()");
            preparedStatement = connection.prepareStatement(SqlQuery.CREATE_PROJECT);
            preparedStatement.setString(1, project.getProjectName());
            preparedStatement.setInt(2, project.getBudget());
            preparedStatement.setInt(3, project.getLocation());
            preparedStatement.setInt(4, project.getCustomer());
            preparedStatement.setInt(5, project.getRequirements());
            preparedStatement.setInt(6, project.getTimeToBuild());
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            logger.error("SQLException in create" + e.getMessage());
        } finally {
            close();
        }
    }

    @Override
    public void deleteProject(int id) {
        try (Connection connection = dbInterface.getConnection()) {
            logger.debug("call deleteProject()");
            preparedStatement = connection.prepareStatement(SqlQuery.DELETE_PROJECT);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            logger.error("SQLException in delete" + e.getMessage());
        } finally {
            close();
        }
    }

    @Override
    public List<Project> findByCustomerId(int id) {
        try (Connection connection = dbInterface.getConnection()) {
            logger.debug("call findByCustomerId()");
            preparedStatement = connection.prepareStatement(SqlQuery.FIND_BY_CUSTOMER_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            projectList = projectParse.getAllProject(resultSet);
            return projectList;
        } catch (SQLException e) {
            logger.error("SQLException in findById" + e.getMessage());
        } finally {
            close();
        }
        return projectList;
    }

    @Override
    public List<Project> findByLocationId(int id) {
        try (Connection connection = dbInterface.getConnection()) {
            logger.debug("call findByLocationId()");
            preparedStatement = connection.prepareStatement(SqlQuery.FIND_BY_LOCATION_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            projectList = projectParse.getAllProject(resultSet);
            return projectList;
        } catch (SQLException e) {
            logger.error("SQLException in findById" + e.getMessage());
        } finally {
            close();
        }
        return projectList;
    }

    @Override
    public List<Project> findByRequirementsId(int id) {
        try (Connection connection = dbInterface.getConnection()) {
            logger.debug("call findByRequirementsId()");
            preparedStatement = connection.prepareStatement(SqlQuery.FIND_BY_REQUIREMENTS_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            projectList = projectParse.getAllProject(resultSet);
            return projectList;
        } catch (SQLException e) {
            logger.error("SQLException in findById" + e.getMessage());
        } finally {
            close();
        }
        return projectList;
    }
}
