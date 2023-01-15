package ua.foxminded.schoolconsoleapp.dao;

import java.sql.SQLException;
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
    void tearDown() throws Exception {
        ScriptReader.readSqlScript(TestConstants.END_SCRIPT,
                DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""));
    }

    @Test
    void findGgoupsWithLessOrEqualsStudents_ExpectedAndActualList_ShouldBeSameSize() throws SQLException {

    }

}
