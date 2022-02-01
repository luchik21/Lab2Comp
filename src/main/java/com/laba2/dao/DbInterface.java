package com.laba2.dao;

import javax.sql.DataSource;
import java.sql.Connection;

public interface DbInterface {

    void connection();

    Connection getConnection();

    DataSource getDataSource();
}
