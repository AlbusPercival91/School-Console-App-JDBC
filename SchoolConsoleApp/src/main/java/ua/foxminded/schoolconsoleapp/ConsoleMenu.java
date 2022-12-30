package ua.foxminded.schoolconsoleapp;

import java.util.Scanner;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import ua.foxminded.schoolconsoleapp.dao.SchoolDAO;

public class ConsoleMenu {
    static Logger logger = Logger.getLogger(ConsoleMenu.class);

    public void launchMenu() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        PropertyConfigurator.configure(classLoader.getResource("log4j.properties "));
        Scanner scan = new Scanner(System.in);

        School school = new School();
        school.startSchoolApp();
        SchoolDAO student = new SchoolDAO();
        
        String command = "";
        String menu = "a. Find all groups with less or equal students’ number\n"
                + "b. Find all students related to the course with the given name\n" + "c. Add a new student\n"
                + "d. Delete a student by the STUDENT_ID\n" + "e. Add a student to the course (from a list)\n"
                + "f. Remove the student from one of their courses\n";
        logger.info(menu);

        while (!command.equalsIgnoreCase("q")) {
            command = scan.nextLine();

            if (command.equals("a")) {
 
                logger.info("Enter number of students: ");
                int quant = scan.nextInt();

                if (!student.findGgoupsWithLessOrEqualsStudents(quant).isEmpty()) {
                    student.findGgoupsWithLessOrEqualsStudents(quant).forEach(logger::info);
                } else {
                    logger.info("no such groups found");
                }

            } else if (command.equals("b")) {
                logger.info("b");
            } else if (command.equals("c")) {
                logger.info("c");
            } else if (command.equals("d")) {
                logger.info("d");
            } else if (command.equals("e")) {
                logger.info("e");
            } else if (command.equals("f")) {
                logger.info("f");
            } else {
                logger.info("\n" + menu);
            }
        }
        scan.close();
    }

}
