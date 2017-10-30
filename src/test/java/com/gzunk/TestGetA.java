package com.gzunk;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestGetA extends AbstractTestA {

    private static final Logger LOG = LoggerFactory.getLogger(TestGetA.class);

    @Test
    public void testGetA() {
        A a = new A(dataSource);
        assertEquals("A", a.getA());
        assertEquals("TESTA", a.getFromDatabase(1));
    }

    @Override
    public void populateSchema() {
        try {
            dataSource.getConnection().createStatement().execute("INSERT INTO MAINTABLE values (1, 'TESTA')");
        } catch (SQLException e) {
            LOG.error("Unable to insert data");
            fail();
        }
    }
}
