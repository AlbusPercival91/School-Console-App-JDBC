package ua.foxminded.schoolconsoleapp.tables;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.log4j.PropertyConfigurator;

public class Tables {

    private Tables() {

    }

    public static void createTables(String fileName) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/school",
                "school_admin", "1234");
                InputStream inputStream = PropertyConfigurator.class.getResourceAsStream("/" + fileName)) {
            ScriptRunner runner = new ScriptRunner(connection);
            Reader reader = new BufferedReader(new InputStreamReader(inputStream));
            runner.setLogWriter(null);
            runner.runScript(reader);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

    }

}
