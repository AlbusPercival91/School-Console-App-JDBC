package ua.foxminded.schoolconsoleapp;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import ua.foxminded.schoolconsoleapp.dbconnection.InitialTables;
import ua.foxminded.schoolconsoleapp.schooldao.CourseTestDAO;
import ua.foxminded.schoolconsoleapp.schooldao.GroupTestDAO;
import ua.foxminded.schoolconsoleapp.schooldao.StudentTestDAO;

public class SchoolConsoleApp {
    static Logger logger = Logger.getLogger(SchoolConsoleApp.class);

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        PropertyConfigurator.configure(classLoader.getResource("log4j.properties "));

        String command = "psql -U postgres -h localhost -p 5432 " + "-f "
                + Paths.get(classLoader.getResource("initialScript.sql").toURI());
        String[] envVars = { "PGPASSWORD=1234" };
        Process runInitScript = Runtime.getRuntime().exec(command, envVars);
        runInitScript.waitFor();

        InitialTables.createTables("tables.sql");

        GroupTestDAO groupDao = new GroupTestDAO();
        groupDao.autoCreate();

        StudentTestDAO student = new StudentTestDAO();
        student.autoCreate();

        CourseTestDAO course = new CourseTestDAO();
        course.autoCreate();

        logger.info("OK");

    }
}
