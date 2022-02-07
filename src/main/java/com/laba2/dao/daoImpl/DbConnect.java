package com.laba2.dao.daoImpl;

import com.laba2.dao.DbInterface;
import com.laba2.dao.dbParse.ColumnLabel;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

@PropertySource("classpath:resources.properties")
@Component

public class DbConnect implements DbInterface {

    Logger logger = Logger.getLogger(DbConnect.class);

    private String jndi;
    private String url;
    private String data;

    private static final String DB = "classpath:db.sql";
    private static final String INSERT = "classpath:dbInsert.sql";
    private static final String SELECT_COUNT = "SELECT count(1) as cnt FROM ALL_TABLES WHERE TABLE_NAME like 'LAB2_ZV%'";

    private Connection connection;
    private Context context;
    private DataSource dataSource;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private int count;

    @Autowired
    public void setJndi(@Value("${company.jndi}") String jndi) {
        this.jndi = jndi;
    }

    @Autowired
    public void setUrl(@Value("${company.url}") String url) {
        this.url = url;
    }

    @Autowired
    public void setData(@Value("${company.contextData}") String data) {
        this.data = data;
    }

    @Override
    public void connection() {
        try {
            Hashtable ht = new Hashtable();
            ht.put(Context.INITIAL_CONTEXT_FACTORY, jndi);
            ht.put(Context.PROVIDER_URL, url);
            context = new InitialContext(ht);
            dataSource = (DataSource) context.lookup(data);
            connection = dataSource.getConnection();
        } catch (SQLException | NamingException e) {
            logger.error("Connection error" + e.getMessage());
        }
    }

    @Override
    public DataSource getDataSource() {
        try {
            logger.debug("call getDataSource()");
            connection();
            return dataSource;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("Error in create database" + e.getMessage());
            }
            try {
                context.close();
            } catch (NamingException e) {
                logger.error("Error in create database" + e.getMessage());
            }
        }
    }

    @Override
    public Connection getConnection() {
        logger.debug("call getConnection()");
        connection();
        return connection;
    }

    @PostConstruct
    public void dataCreate() throws SQLException {
        try {
            logger.debug("call dataCreate()");
            connection();
            preparedStatement = connection.prepareStatement(SELECT_COUNT);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(ColumnLabel.CNT);
            }
            if (count == 0) {
                File create = ResourceUtils.getFile(DB);
                File insert = ResourceUtils.getFile(INSERT);
                ScriptRunner scriptRunner = new ScriptRunner(connection);
                scriptRunner.setStopOnError(false);
                scriptRunner.runScript(new BufferedReader(new FileReader(create)));
                scriptRunner.runScript(new BufferedReader(new FileReader(insert)));
            }
        } catch (NullPointerException | IOException e) {
            logger.error("Error in create database" + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("Error in create database" + e.getMessage());
            }
            try {
                context.close();
            } catch (NamingException e) {
                logger.error("Error in create database" + e.getMessage());
            }
        }
    }
}

