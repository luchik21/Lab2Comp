package com.laba2.dao.daoImpl;

import com.laba2.dao.DaoJob;
import com.laba2.dao.DbInterface;
import com.laba2.dao.dbParse.JobParse;
import com.laba2.models.Job;
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
public class DaoJobImpl implements DaoJob {

    Logger logger = Logger.getLogger(DaoJobImpl.class);

    private Job job;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private List<Job> jobList = new ArrayList<>();

    private DbInterface dbInterface;

    @Autowired
    public void setDbInterface(DbInterface dbInterface) {
        this.dbInterface = dbInterface;
    }

    private JobParse jobParse;

    @Autowired
    public void setJobParse(JobParse jobParse) {
        this.jobParse = jobParse;
    }

    private void close() {
        try {
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            logger.error("Error in close");
        }
    }

    @Override
    public List<Job> selectAllJob() {
        try (Connection connection = dbInterface.getConnection()) {
            logger.debug("call selectAllJob()");
            preparedStatement = connection.prepareStatement("SELECT * FROM LAB2_ZV_JOB");
            resultSet = preparedStatement.executeQuery();
            jobList = jobParse.getAllJob(resultSet);
            return jobList;
        } catch (SQLException e) {
            logger.error("SQLException in selectAll");
        } finally {
            close();
        }
        return jobList;
    }

    @Override
    public Job findById(int id) {
        try (Connection connection = dbInterface.getConnection()) {
            logger.debug("call findById()");
            preparedStatement = connection.prepareStatement("SELECT * FROM LAB2_ZV_JOB WHERE JOB_ID = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            job = jobParse.getOneJob(resultSet);
            return job;
        } catch (SQLException e) {
            logger.error("SQLException in findById"+e.getMessage());
        } finally {
            close();
        }
        return job;
    }

    @Override
    public void editJob(Job job) {
        try (Connection connection = dbInterface.getConnection()) {
            logger.debug("call editJob()");
            preparedStatement = connection.prepareStatement("UPDATE LAB2_ZV_JOB" +
                    " SET JOB_NAME = ?,JOB_BASE_SALARY = ?, JOB_PREMIUM = ? WHERE JOB_ID = ?");
            preparedStatement.setString(1, job.getJobName());
            preparedStatement.setInt(2, job.getSalary());
            preparedStatement.setInt(3, job.getPremium());
            preparedStatement.setInt(4, job.getId());
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            logger.error("SQLException in editJob() "+e.getMessage());
        } finally {
            close();
        }
    }

    @Override
    public void deleteJob(int id) {
        try (Connection connection = dbInterface.getConnection()) {
            logger.debug("call deleteJob()");
            preparedStatement = connection.prepareStatement("DELETE LAB2_ZV_JOB WHERE JOB_ID = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            logger.error("SQLException in delete"+e.getMessage());
        } finally {
            close();
        }
    }

    @Override
    public void createJob(Job job) {
        try (Connection connection = dbInterface.getConnection()) {
            logger.debug("call createJob()");
            preparedStatement = connection.prepareStatement("INSERT INTO LAB2_ZV_JOB " +
                    "(JOB_ID, JOB_NAME, JOB_BASE_SALARY, JOB_PREMIUM)  " +
                    "VALUES (LAB2_ZV_JOB_SEQ.nextval, ?, ?, ?)");
            preparedStatement.setString(1, job.getJobName());
            preparedStatement.setInt(2, job.getSalary());
            preparedStatement.setInt(3, job.getPremium());
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            logger.error("SQLException in create"+e.getMessage());
        } finally {
            close();
        }
    }
}
