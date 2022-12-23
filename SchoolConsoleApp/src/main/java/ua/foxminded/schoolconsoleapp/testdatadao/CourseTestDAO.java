package ua.foxminded.schoolconsoleapp.testdatadao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ua.foxminded.schoolconsoleapp.dbconnection.DataBaseConnection;
import ua.foxminded.schoolconsoleapp.testdata.CourseMaker;

public class CourseTestDAO {
    CourseMaker courseMaker = new CourseMaker();

    public void autoCreate() {
        String sql = "INSERT INTO school.course(course_name, course_description) " + "VALUES(?,?)";
        try (Connection connection = DataBaseConnection.connect();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            for (String s : courseMaker.generateCourses()) {
                statement.setString(1, s);
                statement.setString(2, "TBD");
                statement.addBatch();
                statement.executeBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
