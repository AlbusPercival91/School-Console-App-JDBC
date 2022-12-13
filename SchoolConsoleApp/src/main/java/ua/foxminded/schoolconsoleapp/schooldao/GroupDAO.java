package ua.foxminded.schoolconsoleapp.schooldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import ua.foxminded.schoolconsoleapp.datageneration.GroupMaker;
import ua.foxminded.schoolconsoleapp.dbconnection.DataBaseConnection;

public class GroupDAO {
    GroupMaker groupMaker = new GroupMaker();

    public void createGroup() {
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
