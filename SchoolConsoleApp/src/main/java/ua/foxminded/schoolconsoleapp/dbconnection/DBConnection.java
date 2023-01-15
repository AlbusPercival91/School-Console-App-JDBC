package ua.foxminded.schoolconsoleapp.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    static Connection connection;

    private DBConnection() {

    }

    public static Connection getPsqlConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/school", "school_admin", "1234");
        } catch (SQLException e) {
            throw new IllegalArgumentException("Wrong connection parameters");
        }
        return connection;
    }

    public static Connection getConnection(String dbUrl, String dbUser, String dbPwd) {
        try {
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
        } catch (SQLException e) {
            throw new IllegalArgumentException("Wrong connection parameters");
        }
        return connection;
    }
}
