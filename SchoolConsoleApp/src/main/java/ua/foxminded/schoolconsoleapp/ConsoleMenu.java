package ua.foxminded.schoolconsoleapp;

import java.util.Scanner;
import ua.foxminded.schoolconsoleapp.dao.SchoolDAO;
import ua.foxminded.schoolconsoleapp.testdata.CourseMaker;

public class ConsoleMenu {

    public void launchMenu() {
        Scanner scan = new Scanner(System.in);
        CourseMaker course = new CourseMaker();
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
                System.out.println("Enter number of students: ");

                if (scan.hasNextInt()) {
                    int quant = scan.nextInt();
                    SchoolDAO.findGgoupsWithLessOrEqualsStudents(quant);
                } else {
                    System.out.println("Digits required!");
                }
            } else if (command.equals("b")) {
                System.out.println("Enter course name: ");
                String courseName = scan.nextLine();

                if (course.generateCourses().contains(courseName)) {
                    SchoolDAO.findStudentsRelatedToCourse(courseName);
                    System.out.println("\n" + menu);
                } else {
                    System.out.println("Wrong course name!");
                    System.out.println("\n" + menu);
                }

            } else if (command.equals("c")) {
                System.out.println("Enter student name: ");
                String firstName = scan.nextLine();
                System.out.println("Enter student last name: ");
                String lastName = scan.nextLine();

                if (!firstName.isEmpty() || !lastName.isEmpty()) {
                    System.out.println("Enter group id (if student don't have group enter 0): ");

                    if (scan.hasNextInt()) {
                        Integer groupId = scan.nextInt();

                        if (groupId >= 0 && groupId <= 10) {

                            if (groupId == 0) {
                                groupId = null;
                            }
                            Student student = new Student(groupId, firstName, lastName);
                            SchoolDAO.addNewStudent(student);
                        } else {
                            System.out.println("Group ID should be from 0 to 10.");
                        }
                    } else {
                        System.out.println("Wrong id format, digits required!");
                    }
                } else {
                    System.out.println("Empty entrance!");
                }

            } else if (command.equals("d")) {
                System.out.println("Enter Student's ID: ");
                int studentId = scan.nextInt();

                SchoolDAO.deleteStudentByID(studentId);
            } else if (command.equals("e")) {
                System.out.println("Enter Student's ID: ");

                if (scan.hasNextInt()) {
                    Integer studentId = scan.nextInt();

                    if (SchoolDAO.getStudentID().contains(studentId)) {
                        System.out.println("Please choose the course from List\n");

                        for (String s : SchoolDAO.getCourseList()) {
                            System.out.println(s);
                        }
                        String courseName = scan.next();

                        if (course.generateCourses().contains(courseName)) {
                            SchoolDAO.addStudentToTheCourse(studentId, courseName);
                            System.out.println(
                                    "Student with ID: " + studentId + " assigned to the course: " + courseName);
                        } else {
                            System.out.println("Wrong course name!");
                        }
                    } else {
                        System.out.println("Student ID not exist!");
                    }
                } else {
                    System.out.println("Digits required!");
                }
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
