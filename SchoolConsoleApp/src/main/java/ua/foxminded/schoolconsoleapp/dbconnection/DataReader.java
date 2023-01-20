package ua.foxminded.schoolconsoleapp.dbconnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.ognl.PropertyAccessor;

public class DataReader {

    public static void readSqlScript(String scriptFile, Connection dbConnection) {
        if (scriptFile != null) {
            try (Connection connection = dbConnection;
                    InputStream iStream = PropertyAccessor.class.getResourceAsStream("/" + scriptFile)) {
                ScriptRunner runner = new ScriptRunner(connection);
                Reader reader = new BufferedReader(new InputStreamReader(iStream));
                runner.setLogWriter(null);
                runner.runScript(reader);
            } catch (IOException | SQLException e) {
                throw new IllegalArgumentException("Wrong connection parameters");
            }
        } else {
            throw new IllegalArgumentException("File not found");
        }
    }

    public Properties readProperties(String file) {
        Properties props = new Properties();

        try (InputStream is = getClass().getClassLoader().getResourceAsStream(file)) {
            if (is != null) {
                props.load(is);
            } else {
                throw new IllegalArgumentException("File missing");
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Wrong connection parameters");
        }
        return props;
    }
}
