package ua.foxminded.schoolconsoleapp.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    static Connection connection;
    private static final String DRIVER_WITH_HOST = "jdbc:postgresql://localhost:5432/school";
    private static final String DB_USER = "school_admin";
    private static final String DB_PASSWORD = "1234";

    private DBConnection() {

    }

    public static Connection getConnection(String driverWithHost, String user, String password) {
        try {
            connection = DriverManager.getConnection(driverWithHost, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static String getDriverWithHost() {
        return DRIVER_WITH_HOST;
    }

    public static String getDbUser() {
        return DB_USER;
    }

    public static String getDbPassword() {
        return DB_PASSWORD;
    }
}
