package ua.foxminded.schoolconsoleapp;

import java.util.Scanner;
import ua.foxminded.schoolconsoleapp.dao.SchoolDAO;
import ua.foxminded.schoolconsoleapp.testdata.CourseMaker;

public class ConsoleMenuFacade {
    CourseMaker course = new CourseMaker();
    private static final String DIGITS_REQUIRED = "Digits required!";
    private static final String STUDENT_ID = "Enter Student's ID: ";
    private static final String WRONG_COURSE = "Wrong course name!";
    private static final String NUMBER_OF_STUDENTS = "Enter number of students: ";
    private static final String COURSE_NAME = "Enter course name: ";
    private static final String STUDENT_NAME = "Enter student name: ";
    private static final String STUDENT_LAST_NAME = "Enter student last name: ";
    private static final String GROUP_ID = "Enter group id (if student don't have group enter 0): ";
    private static final String STUDENT_ID_NOT_EXIST = "Student ID not exist!";
    private static final String COURSE_LIST = "Please choose the course from List\n";
    private static final String GROUP_ID_NOTE = "Group ID should be from 0 to 10.";
    private static final String GROUP_ID_NOTE2 = "Wrong id format, digits required!";
    private static final String EMPTY_NOTE = "Empty entrance!";

    public void findGgoupsWithLessOrEqualsStudentsFacade(Scanner scan) {
        System.out.println(NUMBER_OF_STUDENTS);

        if (scan.hasNextInt()) {
            int quant = scan.nextInt();
            SchoolDAO.findGgoupsWithLessOrEqualsStudents(quant).forEach(System.out::println);
        } else {
            System.out.println(DIGITS_REQUIRED);
        }
    }

    public void findStudentsRelatedToCourseFacade(Scanner scan, String menu) {
        System.out.println(COURSE_NAME);
        String courseName = scan.nextLine();

        if (course.generateCourses().contains(courseName)) {
            SchoolDAO.findStudentsRelatedToCourse(courseName).forEach(System.out::println);
            System.out.println("\n" + menu);
        } else {
            System.out.println(WRONG_COURSE);
            System.out.println("\n" + menu);
        }
    }

    public void addNewStudentFacade(Scanner scan) {
        System.out.println(STUDENT_NAME);
        String firstName = scan.nextLine();
        System.out.println(STUDENT_LAST_NAME);
        String lastName = scan.nextLine();

        if (!firstName.isEmpty() || !lastName.isEmpty()) {
            System.out.println(GROUP_ID);

            if (scan.hasNextInt()) {
                Integer groupId = scan.nextInt();

                if (groupId >= 0 && groupId <= 10) {

                    if (groupId == 0) {
                        groupId = null;
                    }
                    Student student = new Student(groupId, firstName, lastName);
                    System.out.println(SchoolDAO.addNewStudent(student));
                } else {
                    System.out.println(GROUP_ID_NOTE);
                }
            } else {
                System.out.println(GROUP_ID_NOTE2);
            }
        } else {
            System.out.println(EMPTY_NOTE);
        }
    }

    public void deleteStudentByIdFacade(Scanner scan) {
        System.out.println(STUDENT_ID);
        int studentId = scan.nextInt();
        System.out.println(SchoolDAO.deleteStudentByID(studentId));
    }

    public void addStudentToTheCourseFacade(Scanner scan) {
        System.out.println(STUDENT_ID);

        if (scan.hasNextInt()) {
            Integer studentId = scan.nextInt();

            if (SchoolDAO.getStudentID().contains(studentId)) {
                System.out.println(COURSE_LIST);
                course.generateCourses().forEach(System.out::println);
                String courseName = scan.next();

                if (course.generateCourses().contains(courseName)) {
                    SchoolDAO.addStudentToTheCourse(studentId, courseName);
                    System.out.println("Student with ID: " + studentId + " assigned to the course: " + courseName);
                } else {
                    System.out.println(WRONG_COURSE);
                }
            } else {
                System.out.println(STUDENT_ID_NOT_EXIST);
            }
        } else {
            System.out.println(DIGITS_REQUIRED);
        }
    }

    public void removeStudentFromCourseFacade(Scanner scan) {
        System.out.println(STUDENT_ID);

        if (scan.hasNextInt()) {
            Integer studentId = scan.nextInt();

            if (SchoolDAO.getStudentID().contains(studentId)) {
                System.out.println(COURSE_LIST);
                course.generateCourses().forEach(System.out::println);
                String courseName = scan.next();

                if (course.generateCourses().contains(courseName)) {
                    System.out.println(SchoolDAO.removeStudentFromCourse(studentId, courseName));
                } else {
                    System.out.println(WRONG_COURSE);
                }
            } else {
                System.out.println(STUDENT_ID_NOT_EXIST);
            }
        } else {
            System.out.println(DIGITS_REQUIRED);
        }
    }

}
