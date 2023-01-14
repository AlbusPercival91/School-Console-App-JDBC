package ua.foxminded.schoolconsoleapp.dbconnection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void testGetConnection_ThrowsIllegalArgumentException_IfConnectionIsWrong() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> DBConnection.getConnection("dbc:h2:~/test", "", ""));
        assertEquals("Wrong connection parameters", exception.getMessage());
    }
}
