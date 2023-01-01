package ua.foxminded.schoolconsoleapp;

import java.util.Scanner;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import ua.foxminded.schoolconsoleapp.dao.SchoolDAO;

public class ConsoleMenu {

    public void launchMenu() {
        Scanner scan = new Scanner(System.in);

        School school = new School();
        school.startSchoolApp();
        SchoolDAO schoolDAO = new SchoolDAO();

        String command = "";
        String menu = "a. Find all groups with less or equal students’ number\n"
                + "b. Find all students related to the course with the given name\n" + "c. Add a new student\n"
                + "d. Delete a student by the STUDENT_ID\n" + "e. Add a student to the course (from a list)\n"
                + "f. Remove the student from one of their courses\n" + "q. Quit\n";
        System.out.println(menu);

        while (!command.equalsIgnoreCase("q")) {
            command = scan.nextLine();

            if (command.equals("a")) {
                System.out.println("Enter number of students: ");
                int quant = scan.nextInt();
                schoolDAO.findGgoupsWithLessOrEqualsStudents(quant);
            } else if (command.equals("b")) {
                System.out.println("Enter course name: ");
                String courseName = scan.nextLine();
                schoolDAO.findStudentsRelatedToCourse(courseName);
            } else if (command.equals("c")) {
                System.out.println("c");
            } else if (command.equals("d")) {
                System.out.println("d");
            } else if (command.equals("e")) {
                System.out.println("e");
            } else if (command.equals("f")) {
                System.out.println("f");
            } else {
                System.out.println("\n" + menu);
            }
        }
        scan.close();
    }

}
