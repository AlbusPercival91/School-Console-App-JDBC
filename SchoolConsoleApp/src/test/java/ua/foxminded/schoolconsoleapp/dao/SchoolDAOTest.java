package ua.foxminded.schoolconsoleapp.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.sql.SQLException;
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
                        (int) SchoolDAO
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
    void addNewStudent() {

    }

}
