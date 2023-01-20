package ua.foxminded.schoolconsoleapp.dao;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;
import ua.foxminded.schoolconsoleapp.dbconnection.DBConnection;
import ua.foxminded.schoolconsoleapp.dbconnection.DataReader;
import ua.foxminded.schoolconsoleapp.dbconnection.TestConstants;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class SchoolDAOTestArguments {

    static Stream<Arguments> findStudentsRelatedToCourse_CheckValues() {
        SchoolDAO school = new SchoolDAO();
        DataReader.readSqlScript(TestConstants.STARTUP_SCRIPT, DBConnection.getConnection("h2.properties"));
        DummyDataDAO testData = new DummyDataDAO();
        testData.createGroup(DBConnection.getConnection("h2.properties"));
        testData.createCourse(DBConnection.getConnection("h2.properties"));
        testData.createStudent(DBConnection.getConnection("h2.properties"));
        testData.createCourseStudentRelation(DBConnection.getConnection("h2.properties"));
        return Stream.of(
                arguments(true,
                        school.findStudentsRelatedToCourse("History", DBConnection.getConnection("h2.properties"))
                                .stream().filter(s -> s.trim().contains(" ")).count() > 0),
                arguments(true,
                        school.findStudentsRelatedToCourse("Literature", DBConnection.getConnection("h2.properties"))
                                .stream().filter(s -> s.trim().contains(" ")).count() > 0),
                arguments(true, school
                        .findStudentsRelatedToCourse("Computer Science", DBConnection.getConnection("h2.properties"))
                        .stream().filter(s -> s.trim().contains(" ")).count() > 0),
                arguments(true,
                        school.findStudentsRelatedToCourse("Geography", DBConnection.getConnection("h2.properties"))
                                .stream().filter(s -> s.trim().contains(" ")).count() > 0),
                arguments(true, school
                        .findStudentsRelatedToCourse("Physical Science", DBConnection.getConnection("h2.properties"))
                        .stream().filter(s -> s.trim().contains(" ")).count() > 0),
                arguments(true,
                        school.findStudentsRelatedToCourse("Life Science", DBConnection.getConnection("h2.properties"))
                                .stream().filter(s -> s.trim().contains(" ")).count() > 0),
                arguments(true,
                        school.findStudentsRelatedToCourse("English", DBConnection.getConnection("h2.properties"))
                                .stream().filter(s -> s.trim().contains(" ")).count() > 0),
                arguments(true,
                        school.findStudentsRelatedToCourse("Mathematics", DBConnection.getConnection("h2.properties"))
                                .stream().filter(s -> s.trim().contains(" ")).count() > 0),
                arguments(true,
                        school.findStudentsRelatedToCourse("Sports", DBConnection.getConnection("h2.properties"))
                                .stream().filter(s -> s.trim().contains(" ")).count() > 0),
                arguments(true, school.findStudentsRelatedToCourse("Art", DBConnection.getConnection("h2.properties"))
                        .stream().filter(s -> s.trim().contains(" ")).count() > 0));
    }

    static Stream<Arguments> addStudentToTheCourse_StringExpectedAndActual() {
        SchoolDAO school = new SchoolDAO();
        DataReader.readSqlScript(TestConstants.STARTUP_SCRIPT, DBConnection.getConnection("h2.properties"));
        DummyDataDAO testData = new DummyDataDAO();
        testData.createGroup(DBConnection.getConnection("h2.properties"));
        testData.createCourse(DBConnection.getConnection("h2.properties"));
        testData.createStudent(DBConnection.getConnection("h2.properties"));
        return Stream.of(
                arguments("Student with ID: 12 assigned to course: Art",
                        school.addStudentToTheCourse(12, "Art", DBConnection.getConnection("h2.properties"))),
                arguments("Student with ID: 190 assigned to course: Literature",
                        school.addStudentToTheCourse(190, "Literature", DBConnection.getConnection("h2.properties"))),
                arguments("Student with ID: 19 assigned to course: Computer Science",
                        school.addStudentToTheCourse(19, "Computer Science",
                                DBConnection.getConnection("h2.properties"))),
                arguments("Student with ID: 21 assigned to course: Geography",
                        school.addStudentToTheCourse(21, "Geography", DBConnection.getConnection("h2.properties"))),
                arguments("Student with ID: 193 assigned to course: Physical Science",
                        school.addStudentToTheCourse(193, "Physical Science",
                                DBConnection.getConnection("h2.properties"))),
                arguments("Student with ID: 1 assigned to course: Life Science",
                        school.addStudentToTheCourse(1, "Life Science", DBConnection.getConnection("h2.properties"))),
                arguments("Student with ID: 9 assigned to course: English",
                        school.addStudentToTheCourse(9, "English", DBConnection.getConnection("h2.properties"))),
                arguments("Student with ID: 2 assigned to course: Mathematics",
                        school.addStudentToTheCourse(2, "Mathematics", DBConnection.getConnection("h2.properties"))),
                arguments("Student with ID: 150 assigned to course: Sports",
                        school.addStudentToTheCourse(150, "Sports", DBConnection.getConnection("h2.properties"))),
                arguments("Student with ID: 7 assigned to course: History",
                        school.addStudentToTheCourse(7, "History", DBConnection.getConnection("h2.properties"))));
    }
}
