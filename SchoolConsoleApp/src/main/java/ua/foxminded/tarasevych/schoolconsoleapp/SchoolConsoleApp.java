package ua.foxminded.tarasevych.schoolconsoleapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class SchoolConsoleApp {
    static Logger logger = Logger.getLogger(SchoolConsoleApp.class);

    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        PropertyConfigurator.configure(classLoader.getResource("log4j.properties "));

        String scriptTablePath = "src/main/resources/testDB.txt";

        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", "postgres", "1234");
            ScriptRunner sr = new ScriptRunner(con);
            Reader reader = new BufferedReader(new FileReader(scriptTablePath));
            sr.runScript(reader);
        } catch (Exception e) {
            logger.error("Failed to Execute" + scriptTablePath + " The error is " + e.getMessage());
        }

    }

}
