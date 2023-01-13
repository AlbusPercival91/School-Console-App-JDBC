package ua.foxminded.schoolconsoleapp.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    static Connection connection;
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/school";
    private static final String DB_USER = "school_admin";
    private static final String DB_PASSWORD = "1234";

    private DBConnection() {

    }

    public static Connection getConnection(String dbUrl, String dbUser, String dbPwd) {
        try {
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static String getDbUrl() {
        return DB_URL;
    }

    public static String getDbUser() {
        return DB_USER;
    }

    public static String getDbPassword() {
        return DB_PASSWORD;
    }
}
