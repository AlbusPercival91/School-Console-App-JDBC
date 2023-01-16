package ua.foxminded.schoolconsoleapp.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ua.foxminded.schoolconsoleapp.Student;
import ua.foxminded.schoolconsoleapp.dbconnection.DBConnection;
import ua.foxminded.schoolconsoleapp.dbconnection.ScriptReader;
import ua.foxminded.schoolconsoleapp.dbconnection.TestConstants;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class SchoolDAOTest {

    @BeforeEach
    void createTables() {
        ScriptReader.readSqlScript(TestConstants.STARTUP_SCRIPT,
                DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""));
    }

    @AfterEach
    void dropAllTables() throws Exception {
        ScriptReader.readSqlScript(TestConstants.END_SCRIPT,
                DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""));
    }

    @Test
    void findGgoupsWithLessOrEqualsStudents_CheckAllValues_ShouldMatchPattern() throws SQLException {
        TestDataDAO testData = new TestDataDAO();
        testData.createGroup(DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""));
        testData.createStudent(DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""));
        Pattern pattern = Pattern.compile("[a-z]{2}-[0-9]{2}");
        List<String> actual = SchoolDAO.findGgoupsWithLessOrEqualsStudents(30,
                DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""));
        int matchedPattern = (int) actual.stream().map(pattern::matcher).filter(Matcher::find).count();

        assertEquals(10, matchedPattern);
    }

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

    @ParameterizedTest
    @MethodSource("ua.foxminded.schoolconsoleapp.dao.SchoolDAOTest#findStudentsRelatedToCourse_CheckValues()")
    void findStudentsRelatedToCourse__ShouldBeMoreZero(boolean expected, boolean actual) {
        assertEquals(expected, actual);
    }

    @Test
    void addNewStudent_StringExpectedAndActual_ShouldBeEquals() throws SQLException {
        TestDataDAO testData = new TestDataDAO();
        testData.createGroup(DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""));
        Student student = new Student(4, "Harry", "Potter");
        SchoolDAO.addNewStudent(student, DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""));
        String actual = "";
        String expected = 1 + " " + student.getGroupId() + " " + student.getFirstName() + " " + student.getLastName();

        try (Connection connection = DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", "");
                Statement statement = connection.createStatement()) {
            ResultSet set = statement.executeQuery("select*from school.students;");

            while (set.next()) {
                actual = set.getString(1) + " " + set.getString(2) + " " + set.getString(3) + " " + set.getString(4);
            }
        }
        assertEquals(expected, actual);
    }

    @Test
    void deleteStudentByID_CheckQuantity_ShouldBeEqualOne() {
        TestDataDAO testData = new TestDataDAO();
        testData.createGroup(DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""));
        ScriptReader.readSqlScript("add_student.sql",
                DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""));
        int deleted = 0;

        if (SchoolDAO.getStudentID(DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", "")).contains(1)) {
            deleted = SchoolDAO.deleteStudentByID(1,
                    DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""));
        }
        assertEquals(1, deleted);
    }

    static Stream<Arguments> addStudentToTheCourse_StringExpectedAndActual() {
        ScriptReader.readSqlScript(TestConstants.STARTUP_SCRIPT,
                DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""));
        TestDataDAO testData = new TestDataDAO();
        testData.createGroup(DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""));
        testData.createCourse(DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""));
        testData.createStudent(DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""));
        testData.createCourseStudentRelation(DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""));
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

    @ParameterizedTest
    @MethodSource("ua.foxminded.schoolconsoleapp.dao.SchoolDAOTest#addStudentToTheCourse_StringExpectedAndActual()")
    void addStudentToTheCourse_StringExpectedAndActual_ShouldBeEquals(String expected, String actual) {
        assertEquals(expected, actual);
    }

}
