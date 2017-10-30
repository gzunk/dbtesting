package com.gzunk;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestGetC extends AbstractTestA {

    private static final Logger LOG = LoggerFactory.getLogger(TestGetC.class);

    @Test
    public void testGetA() {
        A a = new A(dataSource);
        assertEquals("C", a.getC());
        assertEquals("TESTC", a.getFromDatabase(3));
    }

    @Override
    public void populateSchema() {
        try {
            dataSource.getConnection().createStatement().execute("INSERT INTO MAINTABLE values (3, 'TESTC')");
        } catch (SQLException e) {
            LOG.error("Unable to insert data");
            fail();
        }
    }
}
