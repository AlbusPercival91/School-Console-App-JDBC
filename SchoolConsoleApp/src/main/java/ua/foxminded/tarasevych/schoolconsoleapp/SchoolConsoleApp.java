package ua.foxminded.tarasevych.schoolconsoleapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class SchoolConsoleApp {
    static Logger logger = Logger.getLogger(SchoolConsoleApp.class);

    public static void main(String[] args) throws FileNotFoundException, SQLException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        PropertyConfigurator.configure(classLoader.getResource("log4j.properties "));
        
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", "postgres",
                "1234")) {
            ScriptRunner scriptRunner = new ScriptRunner(connection);
            File sqlScript = new File(classLoader.getResource("create_user.sql").getFile());
            Reader reader = new BufferedReader(new FileReader(sqlScript));
            scriptRunner.runScript(reader);
            logger.info("User created successfully...");
        } catch (FileNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", "postgres", "1234");
                Statement stmt = conn.createStatement();) {
            String sql = "DROP DATABASE IF EXISTS students;" + "CREATE DATABASE students";
            stmt.executeUpdate(sql);
            logger.info("Database created successfully...");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
