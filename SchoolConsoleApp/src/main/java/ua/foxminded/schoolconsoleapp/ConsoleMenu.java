package ua.foxminded.schoolconsoleapp;

import java.util.Scanner;

public class ConsoleMenu {

    public void launchMenu() {
        ConsoleMenuFacade cmf = new ConsoleMenuFacade();
        Scanner scan = new Scanner(System.in);
        School school = new School();
        school.startSchoolApp();

        String command = "";
        String menu = "a. Find all groups with less or equal students’ number\n"
                + "b. Find all students related to the course with the given name\n" + "c. Add a new student\n"
                + "d. Delete a student by the STUDENT_ID\n" + "e. Add a student to the course (from a list)\n"
                + "f. Remove the student from one of their courses\n" + "q. Quit\n";
        System.out.println("Welcome to School console application. Please choose options below:\n\n" + menu);

        while (!command.equalsIgnoreCase("q")) {
            command = scan.nextLine();

            if (command.equals("a")) {
                cmf.findGgoupsWithLessOrEqualsStudentsFacade(scan);
            } else if (command.equals("b")) {
                cmf.findStudentsRelatedToCourseFacade(scan, menu);
            } else if (command.equals("c")) {
                cmf.addNewStudentFacade(scan);
            } else if (command.equals("d")) {
                cmf.deleteStudentByIdFacade(scan);
            } else if (command.equals("e")) {
                cmf.addStudentToTheCourseFacade(scan);
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
