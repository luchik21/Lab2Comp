package com.laba2.dao.daoImpl;

import com.laba2.dao.DaoRequirements;
import com.laba2.dao.DbInterface;
import com.laba2.dao.dbParse.RequirementsParse;
import com.laba2.models.Requirements;
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
public class DaoRequirementsImpl implements DaoRequirements {

    Logger logger = Logger.getLogger(DaoRequirementsImpl.class);

    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private List<Requirements> requirementsList = new ArrayList<>();

    private DbInterface dbInterface;

    @Autowired
    public void setDbInterface(DbInterface dbInterface) {
        this.dbInterface = dbInterface;
    }

    private RequirementsParse requirementsParse;

    @Autowired
    public void setRequirementsParse(RequirementsParse requirementsParse) {
        this.requirementsParse = requirementsParse;
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
    public List<Requirements> selectAllRequirements() {
        try (Connection connection = dbInterface.getConnection()) {
            logger.debug("call selectAllRequirements()");
            preparedStatement = connection.prepareStatement(SqlQuery.SELECT_ALL_REQUIREMENTS);
            resultSet = preparedStatement.executeQuery();
            requirementsList = requirementsParse.getAllRequirements(resultSet);
            return requirementsList;
        } catch (SQLException e) {
            logger.error("SQLException in selectAll" + e.getMessage());
        } finally {
            close();
        }
        return requirementsList;
    }

    @Override
    public void deleteRequirements(int id) {
        try (Connection connection = dbInterface.getConnection()) {
            logger.debug("call deleteRequirements()");
            preparedStatement = connection.prepareStatement(SqlQuery.DELETE_REQUIREMENTS);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            logger.error("SQLException in delete" + e.getMessage());
        } finally {
            close();
        }
    }

    @Override
    public void createRequirements(Requirements requirements) {
        try (Connection connection = dbInterface.getConnection()) {
            logger.debug("call createRequirements()");
            preparedStatement = connection.prepareStatement(SqlQuery.CREATE_REQUIREMENTS);
            preparedStatement.setString(1, requirements.getMaterial());
            preparedStatement.setString(2, requirements.getColor());
            preparedStatement.setString(3, requirements.getType());
            preparedStatement.setInt(4, requirements.getFloorsCount());
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            logger.error("SQLException in create" + e.getMessage());
        } finally {
            close();
        }
    }
}
