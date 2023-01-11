package ua.foxminded.schoolconsoleapp.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    static Connection connection;

    private DataBaseConnection() {

    }

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/school", "school_admin", "1234");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
