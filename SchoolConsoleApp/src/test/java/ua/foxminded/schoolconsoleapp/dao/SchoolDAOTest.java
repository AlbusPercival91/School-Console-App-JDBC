package ua.foxminded.schoolconsoleapp.dao;

import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.foxminded.schoolconsoleapp.dbconnection.DBConnection;
import ua.foxminded.schoolconsoleapp.dbconnection.ScriptReader;

class SchoolDAOTest {
    private static final String STARTUP_SCRIPT = "create_tables_test.sql";
    private static final String END_SCRIPT = "drop_all_tables.sql";

    @BeforeEach
    void createTables() {
        ScriptReader.readSqlScript(STARTUP_SCRIPT,
                DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""));
    }

    @AfterEach
    void tearDown() throws Exception {
        ScriptReader.readSqlScript(END_SCRIPT, DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""));
    }

    @Test
    void findGgoupsWithLessOrEqualsStudents_ExpectedAndActualList_ShouldBeSameSize() throws SQLException {

    }

}
