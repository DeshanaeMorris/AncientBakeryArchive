package com.ancientbakery.ancientbakeryarchive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private static final String PROJECT_ROOT = System.getProperty("user.dir");
    private static final String DB_URL = "jdbc:sqlite:" + PROJECT_ROOT + "/src/main/resources/com/ancientbakery/ancientbakeryarchive/sql/archive.db";
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(DB_URL);
        } catch (ClassNotFoundException e) {
            throw new SQLException("SQLite JDBC Driver not found. Check your pom.xml dependencies!");
        }
    }
}

