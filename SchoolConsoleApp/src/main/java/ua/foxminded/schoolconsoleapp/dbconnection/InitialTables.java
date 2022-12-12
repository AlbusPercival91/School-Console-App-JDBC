package ua.foxminded.schoolconsoleapp.dbconnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.log4j.PropertyConfigurator;

public class InitialTables {

    private InitialTables() {

    }

    public static void createTables(String fileName) {
        try (Connection connection = DataBaseConnection.connect();
                InputStream iStream = PropertyConfigurator.class.getResourceAsStream("/" + fileName)) {
            ScriptRunner runner = new ScriptRunner(connection);
            Reader reader = new BufferedReader(new InputStreamReader(iStream));
            runner.setLogWriter(null);
            runner.runScript(reader);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

    }

}
