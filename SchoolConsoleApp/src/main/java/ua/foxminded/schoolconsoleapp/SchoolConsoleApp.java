package ua.foxminded.schoolconsoleapp;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Scanner;

public class SchoolConsoleApp {

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
//        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//
//        String cmdQuery = "psql -U postgres -h localhost -p 5432 " + "-f "
//                + Paths.get(classLoader.getResource("initialScript.sql").toURI());
//        String[] envVars = { "PGPASSWORD=1234" };
//        Process runInitScript = Runtime.getRuntime().exec(cmdQuery, envVars);
//        runInitScript.waitFor();

        School school = new School();
        school.createSchoolData();
        ConsoleMenuFacade cmf = new ConsoleMenuFacade();
        Scanner scan = new Scanner(System.in);
       
        String command = "";
        String menu = "a. Find all groups with less or equal students’ number\n"
                + "b. Find all students related to the course with the given name\n" + "c. Add a new student\n"
                + "d. Delete a student by the STUDENT_ID\n" + "e. Add a student to the course (from a list)\n"
                + "f. Remove the student from one of their courses\n" + "q. Quit\n";
        System.out.println("Welcome to School console application. Please choose options below:\n\n" + menu);

        while (!command.equalsIgnoreCase("q")) {
            command = scan.nextLine();

            if (command.equalsIgnoreCase("a")) {
                cmf.findGgoupsWithLessOrEqualsStudentsFacade(scan);
            } else if (command.equalsIgnoreCase("b")) {
                cmf.findStudentsRelatedToCourseFacade(scan, menu);
            } else if (command.equalsIgnoreCase("c")) {
                cmf.addNewStudentFacade(scan);
            } else if (command.equalsIgnoreCase("d")) {
                cmf.deleteStudentByIdFacade(scan);
            } else if (command.equalsIgnoreCase("e")) {
                cmf.addStudentToTheCourseFacade(scan);
            } else if (command.equalsIgnoreCase("f")) {
                cmf.removeStudentFromCourseFacade(scan);
            } else if (command.equalsIgnoreCase("q")) {
                System.out.println("exit - OK!");
            } else {
                System.out.println("\n" + menu);
            }
        }
        scan.close();  
    }
}
