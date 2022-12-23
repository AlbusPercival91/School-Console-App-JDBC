package ua.foxminded.schoolconsoleapp.testdatadao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ua.foxminded.schoolconsoleapp.dbconnection.DataBaseConnection;
import ua.foxminded.schoolconsoleapp.testdata.GroupMaker;

public class GroupTestDAO {
    GroupMaker groupMaker = new GroupMaker();

    public void autoCreate() {
        String sql = "INSERT INTO school.group(group_name) " + "VALUES(?)";
        try (Connection connection = DataBaseConnection.connect();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            for (String s : groupMaker.generateGroups()) {
                statement.setString(1, s);
                statement.addBatch();
                statement.executeBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
