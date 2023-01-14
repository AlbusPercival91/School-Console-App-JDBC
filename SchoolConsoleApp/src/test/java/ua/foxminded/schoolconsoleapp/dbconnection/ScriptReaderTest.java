package ua.foxminded.schoolconsoleapp.dbconnection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ScriptReaderTest {
    private static final String FILENAME_STARTUP_SCRIPT = "create_tables_test.sql";
    private static final String SCRIPTREADER_TEST_SCRIPT = "scriptreader_test.sql";
    private static final String FILENAME_FINISH_SCRIPT = "drop_all_tables.sql";

    @BeforeEach
    void createTables() {
        ScriptReader.readSqlScript(FILENAME_STARTUP_SCRIPT, "jdbc:h2:~/test", "", "");
    }

    @AfterEach
    void tearDown() throws Exception {
        ScriptReader.readSqlScript(FILENAME_FINISH_SCRIPT, "jdbc:h2:~/test", "", "");
    }

    @Test
    void readSqlScript_ExpectedAndActualList_ShouldBeEquals() throws SQLException {
        ScriptReader.readSqlScript(SCRIPTREADER_TEST_SCRIPT, "jdbc:h2:~/test", "", "");
        String studentCountQuery = "select*from groups;";
        List<String> groupsActual = new ArrayList<>();
        List<String> groupsExpected = Arrays.asList("AB-28", "PM-83", "WD-40");

        try (Connection connection = DBConnection.getConnection("jdbc:h2:~/test", "", "");
                Statement statement = connection.createStatement()) {
            ResultSet groupSet = statement.executeQuery(studentCountQuery);

            while (groupSet.next()) {
                groupsActual.add(groupSet.getString(2));
            }
        }
        assertEquals(groupsExpected, groupsActual);
    }

    @Test
    void readSqlScript_ThrowsIllegalArgumentException_IfFileIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> ScriptReader.readSqlScript(null, "jdbc:h2:~/test", "", ""));
        assertEquals("File not found", exception.getMessage());
    }

}
