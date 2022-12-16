package ua.foxminded.schoolconsoleapp.schooldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ua.foxminded.schoolconsoleapp.datageneration.GroupMaker;
import ua.foxminded.schoolconsoleapp.datageneration.StudentMaker;
import ua.foxminded.schoolconsoleapp.dbconnection.DataBaseConnection;

public class StudentDAO {
    StudentMaker student = new StudentMaker();
    GroupMaker group = new GroupMaker();
    int i = 0;

    public void autoCreate() {
        String sql = "INSERT INTO school.students(group_id, first_name, last_name) " + "VALUES(?,?,?)";
        try (Connection connection = DataBaseConnection.connect();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            for (String s : student.generateStudents(student.generateNames(20), student.generateSurnames(20))) {
                statement.setObject(1, group.generateGroupId().get(i++));
                statement.setString(2, s.substring(0, s.indexOf(" ")));
                statement.setString(3, s.substring(s.indexOf(" ")));
                statement.addBatch();
                statement.executeBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
