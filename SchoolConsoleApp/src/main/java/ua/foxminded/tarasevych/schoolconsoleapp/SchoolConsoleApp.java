package ua.foxminded.tarasevych.schoolconsoleapp;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.hsqldb.cmdline.SqlToolError;

import ua.foxminded.tarasevych.schoolconsoleapp.tables.DBTables;

public class SchoolConsoleApp {
    static Logger logger = Logger.getLogger(SchoolConsoleApp.class);

    public static void main(String[] args) throws InterruptedException, IOException, URISyntaxException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        PropertyConfigurator.configure(classLoader.getResource("log4j.properties "));

        String command = "psql -U postgres -h localhost -p 5432 " + "-f "
                + Paths.get(classLoader.getResource("initialScript.sql").toURI());
        String[] envVars = { "PGPASSWORD=1234" };
        Process runInitScript = Runtime.getRuntime().exec(command, envVars);
        runInitScript.waitFor();

        try {
            DBTables.createTables();
        } catch (SqlToolError | IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
