package ua.foxminded.schoolconsoleapp;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import ua.foxminded.schoolconsoleapp.datageneration.Course;
import ua.foxminded.schoolconsoleapp.datageneration.Group;
import ua.foxminded.schoolconsoleapp.datageneration.Student;
import ua.foxminded.schoolconsoleapp.dbconnection.InitialTables;

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

        Group group = new Group();
        logger.info(group.generateGroups());

        Course course = new Course();
        logger.info(course.generateCourses());

        Student student = new Student();

        for (String s : student.generateStudents(student.generateNames(), student.generateSurnames())) {
            logger.info(s);
        }

    }
}
