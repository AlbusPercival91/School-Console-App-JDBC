package ua.foxminded.schoolconsoleapp.dbconnection;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;

class DBConnectionTest {

    @Test
    void testGetConnection() throws SQLException {
        Connection con = DBConnection.getConnection("jdbc:h2:~/test", "", "");
        assertNotNull(con);
        con.close();
    }
}
