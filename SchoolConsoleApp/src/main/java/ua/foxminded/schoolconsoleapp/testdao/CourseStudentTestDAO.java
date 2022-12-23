package ua.foxminded.schoolconsoleapp.testdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;
import ua.foxminded.schoolconsoleapp.datageneration.CourseMaker;
import ua.foxminded.schoolconsoleapp.datageneration.StudentMaker;
import ua.foxminded.schoolconsoleapp.dbconnection.DataBaseConnection;

public class CourseStudentTestDAO {
    StudentMaker student = new StudentMaker();
    CourseMaker course = new CourseMaker();

    public void autoCreate() {
        String sql = "INSERT INTO school.students_courses_checkouts(student_id,course_id) " + "VALUES(?,?)";

        try (Connection connection = DataBaseConnection.connect();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            for (Map.Entry<Integer, Set<Integer>> entry : course.assignCourseId().entrySet()) {
                Integer key = entry.getKey();
                Set<Integer> value = entry.getValue();

                for (Integer i : value) {
                    statement.setInt(1, key);
                    statement.setInt(2, i);
                    statement.addBatch();
                }
                statement.executeBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
