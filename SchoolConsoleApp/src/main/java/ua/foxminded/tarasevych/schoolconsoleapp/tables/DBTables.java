package ua.foxminded.tarasevych.schoolconsoleapp.tables;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.PropertyConfigurator;
import org.hsqldb.cmdline.SqlFile;
import org.hsqldb.cmdline.SqlToolError;

public class DBTables {

    public static void createTables() throws IOException, SQLException, SqlToolError {

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/school",
                "school_admin", "1234");
                InputStream inputStream = PropertyConfigurator.class.getResourceAsStream("/create_tables.sql")) {
            SqlFile sqlFile = new SqlFile(new InputStreamReader(inputStream), "init", System.out, "UTF-8", false,
                    new File("."));

            sqlFile.setConnection(connection);
            sqlFile.execute();
        }

    }

}
