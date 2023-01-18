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
import ua.foxminded.schoolconsoleapp.dbconnection.ScriptReader;
import ua.foxminded.schoolconsoleapp.dbconnection.TestConstants;

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
    void findGgoupsWithLessOrEqualsStudents_CheckAllValues_ShouldMatchPattern() throws SQLException {
        DummyDataDAO testData = new DummyDataDAO();
        testData.createGroup(DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""));
        testData.createStudent(DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""));
        Pattern pattern = Pattern.compile("[a-z]{2}-[0-9]{2}");
        List<String> actual = SchoolDAO.findGroupsWithLessOrEqualsStudents(30,
                DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""));
        int matchedPattern = (int) actual.stream().map(pattern::matcher).filter(Matcher::find).count();

        assertEquals(10, matchedPattern);
    }

    @Test
    void addNewStudent_StringExpectedAndActual_ShouldBeEquals() throws SQLException {
        DummyDataDAO testData = new DummyDataDAO();
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
        DummyDataDAO testData = new DummyDataDAO();
        testData.createGroup(DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""));
        ScriptReader.readSqlScript("add_student_test.sql",
                DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""));
        int deleted = 0;

        if (SchoolDAO.getStudentID(DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", "")).contains(1)) {
            deleted = SchoolDAO.deleteStudentByID(1,
                    DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""));
        }
        assertEquals(1, deleted);
    }

    @Test
    void removeStudentFromCourse_CheckQuantity_ShouldBeEquals() {
        DummyDataDAO testData = new DummyDataDAO();
        testData.createGroup(DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""));
        testData.createCourse(DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""));
        testData.createStudent(DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""));
        ScriptReader.readSqlScript("assign_course_test.sql",
                DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""));
        int deleted = 0;
        deleted = SchoolDAO.removeStudentFromCourse(17, "Sports",
                DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""));
        assertEquals(1, deleted);
    }

}
