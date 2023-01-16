package ua.foxminded.schoolconsoleapp.dao;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;
import ua.foxminded.schoolconsoleapp.dbconnection.DBConnection;
import ua.foxminded.schoolconsoleapp.dbconnection.ScriptReader;
import ua.foxminded.schoolconsoleapp.dbconnection.TestConstants;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class SchoolDAOTestArguments {

    static Stream<Arguments> findStudentsRelatedToCourse_CheckValues() {
        ScriptReader.readSqlScript(TestConstants.STARTUP_SCRIPT,
                DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""));
        TestDataDAO testData = new TestDataDAO();
        testData.createGroup(DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""));
        testData.createCourse(DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""));
        testData.createStudent(DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""));
        testData.createCourseStudentRelation(DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""));
        return Stream.of(
                arguments(true,
                        SchoolDAO
                                .findStudentsRelatedToCourse("History",
                                        DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""))
                                .stream().filter(s -> s.trim().contains(" ")).count() > 0),
                arguments(true,
                        SchoolDAO
                                .findStudentsRelatedToCourse("Literature",
                                        DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""))
                                .stream().filter(s -> s.trim().contains(" ")).count() > 0),
                arguments(true,
                        SchoolDAO
                                .findStudentsRelatedToCourse("Computer Science",
                                        DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""))
                                .stream().filter(s -> s.trim().contains(" ")).count() > 0),
                arguments(true,
                        SchoolDAO
                                .findStudentsRelatedToCourse("Geography",
                                        DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""))
                                .stream().filter(s -> s.trim().contains(" ")).count() > 0),
                arguments(true,
                        SchoolDAO
                                .findStudentsRelatedToCourse("Physical Science",
                                        DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""))
                                .stream().filter(s -> s.trim().contains(" ")).count() > 0),
                arguments(true,
                        SchoolDAO
                                .findStudentsRelatedToCourse("Life Science",
                                        DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""))
                                .stream().filter(s -> s.trim().contains(" ")).count() > 0),
                arguments(true,
                        SchoolDAO
                                .findStudentsRelatedToCourse("English",
                                        DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""))
                                .stream().filter(s -> s.trim().contains(" ")).count() > 0),
                arguments(true,
                        SchoolDAO
                                .findStudentsRelatedToCourse("Mathematics",
                                        DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""))
                                .stream().filter(s -> s.trim().contains(" ")).count() > 0),
                arguments(true,
                        SchoolDAO
                                .findStudentsRelatedToCourse("Sports",
                                        DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""))
                                .stream().filter(s -> s.trim().contains(" ")).count() > 0),
                arguments(true,
                        SchoolDAO
                                .findStudentsRelatedToCourse("Art",
                                        DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""))
                                .stream().filter(s -> s.trim().contains(" ")).count() > 0));
    }

    static Stream<Arguments> addStudentToTheCourse_StringExpectedAndActual() {
        ScriptReader.readSqlScript(TestConstants.STARTUP_SCRIPT,
                DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""));
        TestDataDAO testData = new TestDataDAO();
        testData.createGroup(DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""));
        testData.createCourse(DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""));
        testData.createStudent(DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""));
        return Stream.of(
                arguments("Student with ID: 12 assigned to course: Art",
                        SchoolDAO.addStudentToTheCourse(12, "Art",
                                DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""))),
                arguments("Student with ID: 190 assigned to course: Literature",
                        SchoolDAO.addStudentToTheCourse(190, "Literature",
                                DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""))),
                arguments("Student with ID: 19 assigned to course: Computer Science",
                        SchoolDAO.addStudentToTheCourse(19, "Computer Science",
                                DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""))),
                arguments("Student with ID: 21 assigned to course: Geography",
                        SchoolDAO.addStudentToTheCourse(21, "Geography",
                                DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""))),
                arguments("Student with ID: 193 assigned to course: Physical Science",
                        SchoolDAO.addStudentToTheCourse(193, "Physical Science",
                                DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""))),
                arguments("Student with ID: 1 assigned to course: Life Science",
                        SchoolDAO.addStudentToTheCourse(1, "Life Science",
                                DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""))),
                arguments("Student with ID: 9 assigned to course: English",
                        SchoolDAO.addStudentToTheCourse(9, "English",
                                DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""))),
                arguments("Student with ID: 2 assigned to course: Mathematics",
                        SchoolDAO.addStudentToTheCourse(2, "Mathematics",
                                DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""))),
                arguments("Student with ID: 150 assigned to course: Sports",
                        SchoolDAO.addStudentToTheCourse(150, "Sports",
                                DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""))),
                arguments("Student with ID: 7 assigned to course: History", SchoolDAO.addStudentToTheCourse(7,
                        "History", DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""))));
    }

}
