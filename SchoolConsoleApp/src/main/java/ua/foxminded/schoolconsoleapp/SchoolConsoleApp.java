package ua.foxminded.schoolconsoleapp;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import ua.foxminded.schoolconsoleapp.datageneration.CourseMaker;
import ua.foxminded.schoolconsoleapp.datageneration.GroupMaker;
import ua.foxminded.schoolconsoleapp.datageneration.StudentMaker;
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

        GroupMaker group = new GroupMaker();
        logger.info(group.generateGroups());

        CourseMaker course = new CourseMaker();
        logger.info(course.generateCourses());

        StudentMaker student = new StudentMaker();

        for (String s : student.generateStudents(student.generateNames(20), student.generateSurnames(20))) {
            logger.info(s);
        }

    }
}
