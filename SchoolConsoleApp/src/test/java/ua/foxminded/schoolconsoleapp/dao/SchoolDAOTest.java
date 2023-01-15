package ua.foxminded.schoolconsoleapp.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

}
