package ua.foxminded.schoolconsoleapp;

import java.util.Scanner;
import ua.foxminded.schoolconsoleapp.dao.SchoolDAO;
import ua.foxminded.schoolconsoleapp.dao.Student;

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
        System.out.println(menu);

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
            } else if (command.equals("c")) {
                System.out.println("Enter student name: ");
                String firstName = scan.nextLine();

                System.out.println("Enter student last name: ");
                String lastName = scan.nextLine();

                System.out.println("Enter group id: ");
                Integer groupId = scan.nextInt();

                addStudent(firstName, lastName, groupId);
                System.out.println("Student inserted to DB");
            } else if (command.equals("d")) {
                System.out.println("Enter Student's ID: ");
                int studentId = scan.nextInt();
                SchoolDAO.deleteStudentByID(studentId);
                System.out.println("Student deleted from DB");
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

    public void addStudent(String firstName, String lastName, Integer groupId) {
        if (!firstName.isEmpty() && !lastName.isEmpty() && groupId >= 0 && groupId <= 10) {

            if (groupId == 0) {
                groupId = null;
            }
            Student student = new Student(groupId, firstName, lastName);
            SchoolDAO.addNewStudent(student);
        } else {
            System.out.println("wrong");
        }
    }

}
