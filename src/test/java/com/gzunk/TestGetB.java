package com.gzunk;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestGetB extends AbstractTestA {

    private static final Logger LOG = LoggerFactory.getLogger(TestGetB.class);

    @Test
    public void testGetA() {
        A a = new A(dataSource);
        assertEquals("B", a.getB());
        assertEquals("TESTB", a.getFromDatabase(2));
    }

    @Override
    public void populateSchema() {
        try {
            dataSource.getConnection().createStatement().execute("INSERT INTO MAINTABLE values (2, 'TESTB')");
        } catch (SQLException e) {
            LOG.error("Unable to insert data");
            fail();
        }
    }
}
