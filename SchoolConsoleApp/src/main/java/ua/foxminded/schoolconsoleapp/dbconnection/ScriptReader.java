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

public class ScriptReader {

    private ScriptReader() {

    }

    public static void readSqlScript(String scriptFile, String dbUrl, String dbUser, String dbPwd) {
        if (scriptFile != null) {
            try (Connection connection = DBConnection.getConnection(dbUrl, dbUser, dbPwd);
                    InputStream iStream = PropertyAccessor.class.getResourceAsStream("/" + scriptFile)) {
                ScriptRunner runner = new ScriptRunner(connection);
                Reader reader = new BufferedReader(new InputStreamReader(iStream));
                runner.setLogWriter(null);
                runner.runScript(reader);
            } catch (IOException | SQLException e) {
                throw new IllegalArgumentException("Unexpected error occured during file reading");
            }
        } else {
            throw new IllegalArgumentException("File not found");
        }
    }
}
