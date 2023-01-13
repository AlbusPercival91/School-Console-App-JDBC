package ua.foxminded.schoolconsoleapp.dbconnection;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;

class DataBaseConnectionTest {

    @Test
    void testGetConnection() throws SQLException {
        Connection con = DBConnection.getConnection(DBConnection.getDriverWithHost(), DBConnection.getDbUser(),
                DBConnection.getDbPassword());
        assertNotNull(con);
        con.close();
    }
}
