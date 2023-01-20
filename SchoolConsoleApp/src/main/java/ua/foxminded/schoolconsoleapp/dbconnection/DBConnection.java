package ua.foxminded.schoolconsoleapp.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    static Connection connection;
    private static final String DB_PROPERTIES = "psql.properties";

    private DBConnection() {

    }

    public static Connection getPsqlConnection() {
        DataReader reader = new DataReader();
        Properties properties = reader.readProperties(DB_PROPERTIES);
        try {
            connection = DriverManager.getConnection(properties.getProperty("db.url"),
                    properties.getProperty("db.login"), properties.getProperty("db.password"));
        } catch (SQLException e) {
            throw new IllegalArgumentException("Wrong connection parameters");
        }
        return connection;
    }

    public static Connection getConnection(String dbProperties) {
        DataReader reader = new DataReader();
        Properties properties = reader.readProperties(dbProperties);
        try {
            connection = DriverManager.getConnection(properties.getProperty("db.url"),
                    properties.getProperty("db.login"), properties.getProperty("db.password"));
        } catch (SQLException e) {
            throw new IllegalArgumentException("Wrong connection parameters");
        }
        return connection;
    }
}
