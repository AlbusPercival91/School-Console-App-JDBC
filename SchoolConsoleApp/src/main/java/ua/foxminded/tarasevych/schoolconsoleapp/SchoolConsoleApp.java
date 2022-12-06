package ua.foxminded.tarasevych.schoolconsoleapp;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.hsqldb.cmdline.SqlFile;
import org.hsqldb.cmdline.SqlToolError;

public class SchoolConsoleApp {
    static Logger logger = Logger.getLogger(SchoolConsoleApp.class);

    public static void main(String[] args) throws SQLException, IOException, SqlToolError {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        PropertyConfigurator.configure(classLoader.getResource("log4j.properties "));

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", "postgres",
                "1234"); InputStream inputStream = classLoader.getResourceAsStream("script.sql")) {
            SqlFile sqlFile = new SqlFile(new InputStreamReader(inputStream), "init", System.out, "UTF-8", false,
                    new File("."));

            sqlFile.setConnection(connection);
            sqlFile.execute();
        }

    }
}
