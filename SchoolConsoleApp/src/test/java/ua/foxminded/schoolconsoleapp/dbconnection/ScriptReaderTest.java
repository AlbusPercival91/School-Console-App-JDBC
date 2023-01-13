package ua.foxminded.schoolconsoleapp.dbconnection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ScriptReaderTest {
    private static final String FILENAME_STARTUP_SCRIPT = "create_tables_test.sql";
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
    void test() {
        boolean d = true;
        assertEquals(true, d);
    }

}
