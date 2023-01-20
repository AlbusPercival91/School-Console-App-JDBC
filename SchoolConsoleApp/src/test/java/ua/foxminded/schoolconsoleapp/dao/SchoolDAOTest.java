package ua.foxminded.schoolconsoleapp.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ua.foxminded.schoolconsoleapp.Student;
import ua.foxminded.schoolconsoleapp.dbconnection.DBConnection;
import ua.foxminded.schoolconsoleapp.dbconnection.DataReader;
import ua.foxminded.schoolconsoleapp.dbconnection.TestConstants;

class SchoolDAOTest {

    @BeforeEach
    void createTables() {
        DataReader.readSqlScript(TestConstants.STARTUP_SCRIPT, DBConnection.getConnection("h2.properties"));
    }

    @AfterEach
    void dropAllTables() throws Exception {
        DataReader.readSqlScript(TestConstants.END_SCRIPT, DBConnection.getConnection("h2.properties"));
    }

    @ParameterizedTest
    @MethodSource("ua.foxminded.schoolconsoleapp.dao.SchoolDAOTestArguments#findStudentsRelatedToCourse_CheckValues()")
    void findStudentsRelatedToCourse__ShouldBeMoreZero(boolean expected, boolean actual) {
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("ua.foxminded.schoolconsoleapp.dao.SchoolDAOTestArguments#addStudentToTheCourse_StringExpectedAndActual()")
    void addStudentToTheCourse_StringExpectedAndActual_ShouldBeEquals(String expected, String actual) {
        assertEquals(expected, actual);
    }

    @Test
    void findGroupsWithLessOrEqualsStudents_CheckAllValues_ShouldMatchPattern() throws SQLException {
        SchoolDAO school = new SchoolDAO();
        DummyDataDAO testData = new DummyDataDAO();
        testData.createGroup(DBConnection.getConnection("h2.properties"));
        testData.createStudent(DBConnection.getConnection("h2.properties"));
        Pattern pattern = Pattern.compile("[a-z]{2}-[0-9]{2}");
        List<String> actual = school.findGroupsWithLessOrEqualsStudents(30,
                DBConnection.getConnection("h2.properties"));
        int matchedPattern = (int) actual.stream().map(pattern::matcher).filter(Matcher::find).count();

        assertEquals(10, matchedPattern);
    }

    @Test
    void addNewStudent_StringExpectedAndActual_ShouldBeEquals() throws SQLException {
        SchoolDAO school = new SchoolDAO();
        DummyDataDAO testData = new DummyDataDAO();
        testData.createGroup(DBConnection.getConnection("h2.properties"));
        Student student = new Student(4, "Harry", "Potter");
        school.addNewStudent(student, DBConnection.getConnection("h2.properties"));
        String actual = "";
        String expected = 1 + " " + student.getGroupId() + " " + student.getFirstName() + " " + student.getLastName();

        try (Connection connection = DBConnection.getConnection("h2.properties");
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
        SchoolDAO school = new SchoolDAO();
        DummyDataDAO testData = new DummyDataDAO();
        testData.createGroup(DBConnection.getConnection("h2.properties"));
        DataReader.readSqlScript("add_student_test.sql", DBConnection.getConnection("h2.properties"));
        int deleted = 0;

        if (school.getStudentID(DBConnection.getConnection("h2.properties")).contains(1)) {
            deleted = school.deleteStudentByID(1, DBConnection.getConnection("h2.properties"));
        }
        assertEquals(1, deleted);
    }

    @Test
    void removeStudentFromCourse_CheckQuantity_ShouldBeEquals() {
        SchoolDAO school = new SchoolDAO();
        DummyDataDAO testData = new DummyDataDAO();
        testData.createGroup(DBConnection.getConnection("h2.properties"));
        testData.createCourse(DBConnection.getConnection("h2.properties"));
        testData.createStudent(DBConnection.getConnection("h2.properties"));
        DataReader.readSqlScript("assign_course_test.sql", DBConnection.getConnection("h2.properties"));
        int deleted = 0;
        deleted = school.removeStudentFromCourse(17, "Sports", DBConnection.getConnection("h2.properties"));
        assertEquals(1, deleted);
    }

}
