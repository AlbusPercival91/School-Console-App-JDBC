package ua.foxminded.schoolconsoleapp.testdatadao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

import ua.foxminded.schoolconsoleapp.dbconnection.DataBaseConnection;
import ua.foxminded.schoolconsoleapp.testdata.CourseMaker;
import ua.foxminded.schoolconsoleapp.testdata.StudentMaker;

public class CourseStudentTestDAO {
    StudentMaker student = new StudentMaker();
    CourseMaker course = new CourseMaker();

    public void autoCreate() {
        String query = "INSERT INTO school.students_courses_checkouts(student_id,course_id) " + "VALUES(?,?)";

        try (Connection connection = DataBaseConnection.connect();
                PreparedStatement statement = connection.prepareStatement(query)) {

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
