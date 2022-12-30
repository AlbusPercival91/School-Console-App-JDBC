package ua.foxminded.schoolconsoleapp.testdatadao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ua.foxminded.schoolconsoleapp.dbconnection.DataBaseConnection;
import ua.foxminded.schoolconsoleapp.testdata.GroupMaker;
import ua.foxminded.schoolconsoleapp.testdata.StudentMaker;

public class StudentTestDAO {
    StudentMaker student = new StudentMaker();
    GroupMaker group = new GroupMaker();
    
    public void autoCreate() {
        String query = "INSERT INTO school.students(group_id, first_name, last_name) " + "VALUES(?,?,?)";
        int i = 0;
        
        try (Connection connection = DataBaseConnection.connect();
                PreparedStatement statement = connection.prepareStatement(query)) {

            for (String s : student.generateStudents(student.generateNames(20), student.generateSurnames(20))) {
                statement.setObject(1, group.assignGroupId().get(i++));
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
