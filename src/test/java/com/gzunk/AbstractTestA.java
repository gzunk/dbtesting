package com.gzunk;

import org.h2.jdbcx.JdbcDataSource;
import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.fail;

public abstract class AbstractTestA {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractTestA.class);

    DataSource dataSource;

    private void setupDatabase() {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");

        LOG.info("Setting up database");

        try {
            Statement stmt = ds.getConnection().createStatement();
            stmt.execute("CREATE SCHEMA CONTROLS");
            stmt.execute("CREATE TABLE CONTROLS.MAINTABLE (ID NUMERIC, TITLE VARCHAR(20))");
        } catch (SQLException e) {
            LOG.error("Unable to create database");
            fail();
        }

        ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:mem:test;SCHEMA=CONTROLS;DB_CLOSE_DELAY=-1");
        dataSource = ds;
    }

    @Before
    public void setup() {
        LOG.info("SETUP");
        setupDatabase();
        populateSchema();
    }

    @After
    public void teardown() {
        LOG.info("TEARDOWN");
        try {
            dataSource.getConnection().createStatement().execute("SHUTDOWN");
        } catch (SQLException e) {
            LOG.error("Unable to shutdown database");
            fail();
        }

    }

    public abstract void populateSchema();

}
