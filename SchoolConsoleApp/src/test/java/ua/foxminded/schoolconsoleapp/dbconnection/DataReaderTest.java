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
import java.util.Properties;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataReaderTest {

    @BeforeEach
    void createTables() {
        DataReader.readSqlScript(TestConstants.STARTUP_SCRIPT, DBConnection.getConnection("h2.properties"));
    }

    @AfterEach
    void dropAllTables() throws Exception {
        DataReader.readSqlScript(TestConstants.END_SCRIPT, DBConnection.getConnection("h2.properties"));
    }

    @Test
    void readSqlScript_ExpectedAndActualList_ShouldBeEquals() throws SQLException {
        DataReader.readSqlScript("scriptreader_test.sql", DBConnection.getConnection("h2.properties"));
        List<String> groupsActual = new ArrayList<>();
        List<String> groupsExpected = Arrays.asList("AB-28", "PM-83", "WD-40");

        try (Connection connection = DBConnection.getConnection("h2.properties");
                Statement statement = connection.createStatement()) {
            ResultSet groupSet = statement.executeQuery("select*from school.groups;");

            while (groupSet.next()) {
                groupsActual.add(groupSet.getString(2));
            }
        }
        assertEquals(groupsExpected, groupsActual);
    }

    @Test
    void readProperties_ExpectedAndActualString_ShouldBeEquals() throws SQLException {
        DataReader reader = new DataReader();
        Properties propDb = reader.readProperties("h2.properties");
        String expectedDbUrl = "jdbc:h2:~/test;MODE=PostgreSQL";
        String actualDbUrl = propDb.getProperty("db.url");
        assertEquals(expectedDbUrl, actualDbUrl);
    }

    @Test
    void readSqlScript_ThrowsIllegalArgumentException_IfFileIsNull() {
        Exception exception = assertThrows(Exception.class,
                () -> DataReader.readSqlScript(null, DBConnection.getConnection("h2.properties")));
        assertEquals("File not found", exception.getMessage());
    }
}
