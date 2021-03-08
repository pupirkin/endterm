package com.company;

import com.company.IDB;

import java.sql.*;

public class DB implements IDB{
    @Override
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        String connectionUrl = "jdbc:postgresql://localhost:5432/endterm";
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(connectionUrl, "postgres", "");
            return con;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
