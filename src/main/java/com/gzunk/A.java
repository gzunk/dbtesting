package com.gzunk;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class A {

    private DataSource dataSource;

    public A(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getA() {
        return "A";
    }

    public String getB() {
        return "B";
    }

    public String getC() {
        return "C";
    }

    public String getFromDatabase(long id) {
        try {
            Connection c = dataSource.getConnection();
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("select TITLE from MAINTABLE where ID=" + id);
            String title = null;
            while (rs.next()) {
                title = rs.getString(1);
            }
            rs.close();
            stmt.close();
            rs.close();
            return title;
        } catch (SQLException e) {
            throw new RuntimeException("Error querying database");
        }
    }

}
