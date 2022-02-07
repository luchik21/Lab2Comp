package com.laba2.dao.dbParse;

import com.laba2.models.Job;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JobParse {

    Logger logger = Logger.getLogger(JobParse.class);

    private Job job;

    public List<Job> getAllJob(ResultSet resultSet) {
        List<Job> locationList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                locationList.add(getJob(resultSet));
            }
        } catch (SQLException e) {
            logger.error("SQLException in getAll" + e.getMessage());
        }
        return locationList;
    }

    private Job getJob(ResultSet resultSet) {
        try {
            int id = resultSet.getInt(ColumnLabel.JOB_ID);
            String name = resultSet.getString(ColumnLabel.JOB_NAME);
            int salary = resultSet.getInt(ColumnLabel.JOB_BASE_SALARY);
            int premium = resultSet.getInt(ColumnLabel.JOB_PREMIUM);

            job = new Job(id, name, salary, premium);

        } catch (SQLException e) {
            logger.error("SQLException in get" + e.getMessage());
        }
        return job;
    }

    public Job getOneJob(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                job = getJob(resultSet);
            }
        } catch (SQLException e) {
            logger.error("SQLException in getOne" + e.getMessage());
        }
        return job;
    }
}
