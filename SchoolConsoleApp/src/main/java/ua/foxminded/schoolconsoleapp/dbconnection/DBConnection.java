package ua.foxminded.schoolconsoleapp.dbconnection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    static Connection connection;
    private static final File PROPS = new File(getHomeDir(), "config" + File.separator + "db.properties");

    private DBConnection() {

    }

    private static File getHomeDir() {
        String prop = System.getProperty("user.home");
        File homeDir = new File(prop == null ? "." : prop);

        if (!homeDir.isDirectory()) {
            throw new IllegalStateException(homeDir + " is not directory");
        }
        return homeDir;
    }

    private static Properties readProperties() {
        Properties props = new Properties();

        try (InputStream is = new FileInputStream(PROPS)) {
            props.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

    public static Connection getPsqlConnection() {
        try {
            connection = DriverManager.getConnection(readProperties().getProperty("db.url"),
                    readProperties().getProperty("db.login"), readProperties().getProperty("db.password"));
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
