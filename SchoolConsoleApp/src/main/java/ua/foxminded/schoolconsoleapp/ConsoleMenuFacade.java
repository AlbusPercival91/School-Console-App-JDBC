package ua.foxminded.schoolconsoleapp;

import java.util.Scanner;
import ua.foxminded.schoolconsoleapp.dao.SchoolDAO;
import ua.foxminded.schoolconsoleapp.dbconnection.DBConnection;
import ua.foxminded.schoolconsoleapp.testdata.CourseMaker;

public class ConsoleMenuFacade {
    CourseMaker course = new CourseMaker();

    public void findGgoupsWithLessOrEqualsStudentsFacade(Scanner scan) {
        System.out.println(ConsoleMenuConstants.NUMBER_OF_STUDENTS);

        if (scan.hasNextInt()) {
            int quant = scan.nextInt();
            SchoolDAO.findGroupsWithLessOrEqualsStudents(quant, DBConnection.getPsqlConnection())
                    .forEach(System.out::println);
        } else {
            System.out.println(ConsoleMenuConstants.DIGITS_REQUIRED);
        }
    }

    public void findStudentsRelatedToCourseFacade(Scanner scan, String menu) {
        System.out.println(ConsoleMenuConstants.COURSE_NAME);
        String courseName = scan.nextLine();

        if (course.generateCourses().contains(courseName)) {
            SchoolDAO.findStudentsRelatedToCourse(courseName, DBConnection.getPsqlConnection())
                    .forEach(System.out::println);
            System.out.println("\n" + menu);
        } else {
            System.out.println(ConsoleMenuConstants.WRONG_COURSE);
            System.out.println("\n" + menu);
        }
    }

    public void addNewStudentFacade(Scanner scan) {
        System.out.println(ConsoleMenuConstants.STUDENT_NAME);
        String firstName = scan.nextLine();
        System.out.println(ConsoleMenuConstants.STUDENT_LAST_NAME);
        String lastName = scan.nextLine();

        if (!firstName.isEmpty() || !lastName.isEmpty()) {
            System.out.println(ConsoleMenuConstants.GROUP_ID);

            if (scan.hasNextInt()) {
                Integer groupId = scan.nextInt();

                if (groupId >= 0 && groupId <= 10) {

                    if (groupId == 0) {
                        groupId = null;
                    }
                    Student student = new Student(groupId, firstName, lastName);
                    System.out.println(SchoolDAO.addNewStudent(student, DBConnection.getPsqlConnection()));
                } else {
                    System.out.println(ConsoleMenuConstants.GROUP_ID_NOTE);
                }
            } else {
                System.out.println(ConsoleMenuConstants.GROUP_ID_NOTE2);
            }
        } else {
            System.out.println(ConsoleMenuConstants.EMPTY_NOTE);
        }
    }

    public void deleteStudentByIdFacade(Scanner scan) {
        System.out.println(ConsoleMenuConstants.STUDENT_ID);
        int studentId = scan.nextInt();
        System.out.println(SchoolDAO.deleteStudentByID(studentId, DBConnection.getPsqlConnection())
                + " student(s) deleted from data base");
    }

    public void addStudentToTheCourseFacade(Scanner scan) {
        System.out.println(ConsoleMenuConstants.STUDENT_ID);

        if (scan.hasNextInt()) {
            Integer studentId = scan.nextInt();

            if (SchoolDAO.getStudentID(DBConnection.getPsqlConnection()).contains(studentId)) {
                System.out.println(ConsoleMenuConstants.COURSE_LIST);
                course.generateCourses().forEach(System.out::println);
                String courseName = scan.next();

                if (course.generateCourses().contains(courseName)) {
                    SchoolDAO.addStudentToTheCourse(studentId, courseName, DBConnection.getPsqlConnection());
                    System.out.println("Student with ID: " + studentId + " assigned to the course: " + courseName);
                } else {
                    System.out.println(ConsoleMenuConstants.WRONG_COURSE);
                }
            } else {
                System.out.println(ConsoleMenuConstants.STUDENT_ID_NOT_EXIST);
            }
        } else {
            System.out.println(ConsoleMenuConstants.DIGITS_REQUIRED);
        }
    }

    public void removeStudentFromCourseFacade(Scanner scan) {
        System.out.println(ConsoleMenuConstants.STUDENT_ID);

        if (scan.hasNextInt()) {
            Integer studentId = scan.nextInt();

            if (SchoolDAO.getStudentID(DBConnection.getPsqlConnection()).contains(studentId)) {
                System.out.println(ConsoleMenuConstants.COURSE_LIST);
                course.generateCourses().forEach(System.out::println);
                String courseName = scan.next();

                if (course.generateCourses().contains(courseName)) {
                    System.out.println(
                            SchoolDAO.removeStudentFromCourse(studentId, courseName, DBConnection.getPsqlConnection())
                                    + " student(s) with ID: " + studentId + " deleted from course " + courseName);
                } else {
                    System.out.println(ConsoleMenuConstants.WRONG_COURSE);
                }
            } else {
                System.out.println(ConsoleMenuConstants.STUDENT_ID_NOT_EXIST);
            }
        } else {
            System.out.println(ConsoleMenuConstants.DIGITS_REQUIRED);
        }
    }

}
