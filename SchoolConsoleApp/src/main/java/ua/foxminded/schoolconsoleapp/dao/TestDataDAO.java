package ua.foxminded.schoolconsoleapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;
import ua.foxminded.schoolconsoleapp.dbconnection.DBConnection;
import ua.foxminded.schoolconsoleapp.testdata.CourseMaker;
import ua.foxminded.schoolconsoleapp.testdata.GroupMaker;
import ua.foxminded.schoolconsoleapp.testdata.StudentMaker;

public class TestDataDAO {
    StudentMaker student = new StudentMaker();
    CourseMaker course = new CourseMaker();
    GroupMaker group = new GroupMaker();

    public void createStudent() {
        String query = "INSERT INTO school.students(group_id, first_name, last_name) " + "VALUES(?,?,?)";
        int i = 0;

        try (Connection connection = DBConnection.getConnection(DBConnection.getDbUrl(),
                DBConnection.getDbUser(), DBConnection.getDbPassword());
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

    public void createGroup() {
        String query = "INSERT INTO school.group(group_name) " + "VALUES(?)";
        try (Connection connection = DBConnection.getConnection(DBConnection.getDbUrl(),
                DBConnection.getDbUser(), DBConnection.getDbPassword());
                PreparedStatement statement = connection.prepareStatement(query)) {

            for (String s : group.generateGroups()) {
                statement.setString(1, s);
                statement.addBatch();
                statement.executeBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createCourse() {
        String query = "INSERT INTO school.course(course_name, course_description) " + "VALUES(?,?)";
        try (Connection connection = DBConnection.getConnection(DBConnection.getDbUrl(),
                DBConnection.getDbUser(), DBConnection.getDbPassword());
                PreparedStatement statement = connection.prepareStatement(query)) {

            for (String s : course.generateCourses()) {
                statement.setString(1, s);
                statement.setString(2, "TBD");
                statement.addBatch();
                statement.executeBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createCourseStudentRelation() {
        String query = "INSERT INTO school.students_courses_checkouts(student_id,course_id) " + "VALUES(?,?)";

        try (Connection connection = DBConnection.getConnection(DBConnection.getDbUrl(),
                DBConnection.getDbUser(), DBConnection.getDbPassword());
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
