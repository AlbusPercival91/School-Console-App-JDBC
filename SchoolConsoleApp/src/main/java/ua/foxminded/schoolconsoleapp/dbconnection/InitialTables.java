package ua.foxminded.schoolconsoleapp.dbconnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.ognl.PropertyAccessor;

public class InitialTables {

    private InitialTables() {

    }

    public static void createTables(String fileName) {
        try (Connection connection = DBConnection.getConnection(DBConnection.getDriverWithHost(),
                DBConnection.getDbUser(), DBConnection.getDbPassword());
                InputStream iStream = PropertyAccessor.class.getResourceAsStream("/" + fileName)) {
            ScriptRunner runner = new ScriptRunner(connection);
            Reader reader = new BufferedReader(new InputStreamReader(iStream));
            runner.setLogWriter(null);
            runner.runScript(reader);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
