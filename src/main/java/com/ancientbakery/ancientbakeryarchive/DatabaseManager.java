package com.ancientbakery.ancientbakeryarchive;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private static final String DB_RESOURCE_PATH = "com/ancientbakery/ancientbakeryarchive/sql/archive.db";
    private static String dbUrl;

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(getDatabaseUrl());
        } catch (ClassNotFoundException e) {
            throw new SQLException("SQLite JDBC Driver not found. Check your pom.xml dependencies!", e);
        }
    }

    private static synchronized String getDatabaseUrl() throws SQLException {
        if (dbUrl != null) {
            return dbUrl;
        }

        Path projectRootDb = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", DB_RESOURCE_PATH);
        if (Files.isRegularFile(projectRootDb)) {
            dbUrl = "jdbc:sqlite:" + projectRootDb.toAbsolutePath();
            return dbUrl;
        }

        Path nestedProjectDb = Paths.get(System.getProperty("user.dir"), "AncientBakeryArchive", "src", "main", "resources", DB_RESOURCE_PATH);
        if (Files.isRegularFile(nestedProjectDb)) {
            dbUrl = "jdbc:sqlite:" + nestedProjectDb.toAbsolutePath();
            return dbUrl;
        }

        Path targetClassesDb = Paths.get(System.getProperty("user.dir"), "target", "classes", DB_RESOURCE_PATH);
        if (Files.isRegularFile(targetClassesDb)) {
            dbUrl = "jdbc:sqlite:" + targetClassesDb.toAbsolutePath();
            return dbUrl;
        }

        URL resource = DatabaseManager.class.getClassLoader().getResource(DB_RESOURCE_PATH);
        if (resource != null) {
            if ("file".equalsIgnoreCase(resource.getProtocol())) {
                try {
                    Path resourceDb = Paths.get(resource.toURI());
                    dbUrl = "jdbc:sqlite:" + resourceDb.toAbsolutePath();
                    return dbUrl;
                } catch (URISyntaxException e) {
                    throw new SQLException("Could not load archive.db resource path.", e);
                }
            }

            try (InputStream input = resource.openStream()) {
                Path tempDb = Files.createTempFile("ancient-bakery-archive-", ".db");
                Files.copy(input, tempDb, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                tempDb.toFile().deleteOnExit();
                dbUrl = "jdbc:sqlite:" + tempDb.toAbsolutePath();
                return dbUrl;
            } catch (IOException e) {
                throw new SQLException("Could not copy archive.db from application resources.", e);
            }
        }

        throw new SQLException("Could not find archive.db. Checked project root, nested project folder, target/classes, and application resources.");
    }
}