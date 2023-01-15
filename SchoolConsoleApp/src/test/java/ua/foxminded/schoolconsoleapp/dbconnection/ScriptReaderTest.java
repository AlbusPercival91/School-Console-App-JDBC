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

    @BeforeEach
    void createTables() {
        ScriptReader.readSqlScript(TestConstants.STARTUP_SCRIPT,
                DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""));
    }

    @AfterEach
    void dropAllTables() throws Exception {
        ScriptReader.readSqlScript(TestConstants.END_SCRIPT, DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""));
    }

    @Test
    void readSqlScript_ExpectedAndActualList_ShouldBeEquals() throws SQLException {
        ScriptReader.readSqlScript("scriptreader_test.sql",
                DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", ""));
        List<String> groupsActual = new ArrayList<>();
        List<String> groupsExpected = Arrays.asList("AB-28", "PM-83", "WD-40");

        try (Connection connection = DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", "");
                Statement statement = connection.createStatement()) {
            ResultSet groupSet = statement.executeQuery("select*from school.groups;");

            while (groupSet.next()) {
                groupsActual.add(groupSet.getString(2));
            }
        }
        assertEquals(groupsExpected, groupsActual);
    }

    @Test
    void readSqlScript_ThrowsIllegalArgumentException_IfFileIsNull() {
        Exception exception = assertThrows(Exception.class, () -> ScriptReader.readSqlScript(null,
                DBConnection.getConnection("jdbc:h2:~/test;MODE=PostgreSQL", "", "")));
        assertEquals("File not found", exception.getMessage());
    }
}
