package ua.foxminded.schoolconsoleapp;

import java.util.Scanner;

import ua.foxminded.schoolconsoleapp.dao.SchoolDAO;
import ua.foxminded.schoolconsoleapp.testdata.CourseMaker;

public class ConsoleMenuFacade {
    CourseMaker course = new CourseMaker();

    public void findGgoupsWithLessOrEqualsStudentsFacade(Scanner scan) {
        System.out.println("Enter number of students: ");

        if (scan.hasNextInt()) {
            int quant = scan.nextInt();
            SchoolDAO.findGgoupsWithLessOrEqualsStudents(quant);
        } else {
            System.out.println("Digits required!");
        }
    }

    public void findStudentsRelatedToCourseFacade(Scanner scan, String menu) {
        System.out.println("Enter course name: ");
        String courseName = scan.nextLine();

        if (course.generateCourses().contains(courseName)) {
            SchoolDAO.findStudentsRelatedToCourse(courseName);
            System.out.println("\n" + menu);
        } else {
            System.out.println("Wrong course name!");
            System.out.println("\n" + menu);
        }
    }

    public void addNewStudentFacade(Scanner scan) {
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
    }

    public void deleteStudentByIdFacade(Scanner scan) {
        System.out.println("Enter Student's ID: ");
        int studentId = scan.nextInt();
        SchoolDAO.deleteStudentByID(studentId);
    }

    public void addStudentToTheCourseFacade(Scanner scan) {
        System.out.println("Enter Student's ID: ");

        if (scan.hasNextInt()) {
            Integer studentId = scan.nextInt();

            if (SchoolDAO.getStudentID().contains(studentId)) {
                System.out.println("Please choose the course from List\n");
                course.generateCourses().forEach(System.out::println);
                String courseName = scan.next();

                if (course.generateCourses().contains(courseName)) {
                    SchoolDAO.addStudentToTheCourse(studentId, courseName);
                    System.out.println("Student with ID: " + studentId + " assigned to the course: " + courseName);
                } else {
                    System.out.println("Wrong course name!");
                }
            } else {
                System.out.println("Student ID not exist!");
            }
        } else {
            System.out.println("Digits required!");
        }
    }

    public void removeStudentFromCourseFacade(Scanner scan) {

    }

}
