package ua.foxminded.schoolconsoleapp;

import java.util.Scanner;
import ua.foxminded.schoolconsoleapp.dao.SchoolDAO;

public class ConsoleMenu {

    public void launchMenu() {
        Scanner scan = new Scanner(System.in);

        School school = new School();
        school.startSchoolApp();

        String command = "";
        String menu = "a. Find all groups with less or equal students’ number\n"
                + "b. Find all students related to the course with the given name\n" + "c. Add a new student\n"
                + "d. Delete a student by the STUDENT_ID\n" + "e. Add a student to the course (from a list)\n"
                + "f. Remove the student from one of their courses\n" + "q. Quit\n";
        System.out.println("Welcome to School console application. Please choose any options below:\n\n" + menu);

        while (!command.equalsIgnoreCase("q")) {
            command = scan.nextLine();

            if (command.equals("a")) {
                System.out.println("Enter number of students: ");
                int quant = scan.nextInt();
                
                SchoolDAO.findGgoupsWithLessOrEqualsStudents(quant);
            } else if (command.equals("b")) {
                System.out.println("Enter course name: ");
                String courseName = scan.nextLine();
                
                SchoolDAO.findStudentsRelatedToCourse(courseName);
                System.out.println("\n" + menu);
            } else if (command.equals("c")) {
                System.out.println("Enter student name: ");
                String firstName = scan.nextLine();
                System.out.println("Enter student last name: ");
                String lastName = scan.nextLine();
                System.out.println("Enter group id: ");

                if (scan.hasNextInt()) {
                    Integer groupId = scan.nextInt();
                    Student.addStudent(firstName, lastName, groupId);
                } else {
                    System.out.println("Wrong format! Pleae enter number from 0 to 10.");
                }
            } else if (command.equals("d")) {
                System.out.println("Enter Student's ID: ");
                int studentId = scan.nextInt();

                SchoolDAO.deleteStudentByID(studentId);
            } else if (command.equals("e")) {
                System.out.println("e");
            } else if (command.equals("f")) {
                System.out.println("f");
            } else if (command.equals("q")) {
                System.out.println("exit - OK!");
            } else {
                System.out.println("\n" + menu);
            }
        }
        scan.close();
    }

}
