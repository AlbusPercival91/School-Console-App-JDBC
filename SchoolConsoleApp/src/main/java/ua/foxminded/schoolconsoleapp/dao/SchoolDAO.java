package ua.foxminded.schoolconsoleapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ua.foxminded.schoolconsoleapp.Student;
import ua.foxminded.schoolconsoleapp.dbconnection.DBConnection;

public class SchoolDAO {

    private SchoolDAO() {

    }

    public static void findGgoupsWithLessOrEqualsStudents(int number) {
        List<String> groupID = new ArrayList<>();
        String studentCountQuery = "SELECT group_id, COUNT (*) FROM school.students GROUP BY group_id HAVING COUNT(*)<="
                + number + ";";

        try (Connection connection = DBConnection.getConnection(DBConnection.getDriverWithHost(),
                DBConnection.getDbUser(), DBConnection.getDbPassword());
                Statement statement = connection.createStatement()) {
            ResultSet studentCountSet = statement.executeQuery(studentCountQuery);

            while (studentCountSet.next()) {
                if (studentCountSet.getString(1) != null) {
                    groupID.add(studentCountSet.getString(1));
                }
            }

            for (String s : groupID) {
                String groupNameQuery = "SELECT * FROM school.group WHERE group_id = " + s + ";";
                ResultSet groupNameSet = statement.executeQuery(groupNameQuery);

                while (groupNameSet.next()) {
                    System.out.println(groupNameSet.getString(2));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void findStudentsRelatedToCourse(String courseName) {
        String query = "SELECT first_name, last_name\n" + " FROM school.students\n"
                + "  JOIN school.students_courses_checkouts \n"
                + "    ON school.students_courses_checkouts.student_id = school.students.student_id\n"
                + "  JOIN school.course\n"
                + "    ON school.students_courses_checkouts.course_id = school.course.course_id\n"
                + " WHERE school.course.course_name = '" + courseName + "';";

        try (Connection connection = DBConnection.getConnection(DBConnection.getDriverWithHost(),
                DBConnection.getDbUser(), DBConnection.getDbPassword());
                Statement statement = connection.createStatement()) {
            ResultSet set = statement.executeQuery(query);

            while (set.next()) {
                System.out.println(set.getString(1) + set.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addNewStudent(Student student) {
        String query = "insert into school.students(group_id, first_name, last_name) values(?,?,?)";
        try (Connection connection = DBConnection.getConnection(DBConnection.getDriverWithHost(),
                DBConnection.getDbUser(), DBConnection.getDbPassword());
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, student.getGroupId());
            statement.setString(2, student.getFirstName());
            statement.setString(3, student.getLastName());
            statement.executeUpdate();
            System.out.println(student.toString() + " added to DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteStudentByID(int id) {
        String query = "delete from school.students where student_id = " + id + ";";
        try (Connection connection = DBConnection.getConnection(DBConnection.getDriverWithHost(),
                DBConnection.getDbUser(), DBConnection.getDbPassword());
                PreparedStatement statement = connection.prepareStatement(query)) {
            int rowsDeleted = statement.executeUpdate();
            System.out.println(rowsDeleted + " rows deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Set<Integer> getStudentID() {
        Set<Integer> studentId = new HashSet<>();
        String query = "select student_id from school.students;";

        try (Connection connection = DBConnection.getConnection(DBConnection.getDriverWithHost(),
                DBConnection.getDbUser(), DBConnection.getDbPassword());
                Statement statement = connection.createStatement()) {
            ResultSet set = statement.executeQuery(query);

            while (set.next()) {
                studentId.add(set.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentId;
    }

    public static void addStudentToTheCourse(Integer studentId, String courseName) {
        String query = "INSERT INTO school.students_courses_checkouts(student_id, course_id)\n" + " SELECT\n"
                + "     (SELECT student_id FROM school.students WHERE student_id=" + studentId + "),\n"
                + "     (SELECT course_id FROM school.course WHERE course_name = '" + courseName + "');";
        try (Connection connection = DBConnection.getConnection(DBConnection.getDriverWithHost(),
                DBConnection.getDbUser(), DBConnection.getDbPassword());
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeStudentFromCourse(Integer studentId, String courseName) {
        String query = "DELETE \n" + "FROM school.students_courses_checkouts\n" + "WHERE student_id='" + studentId
                + "' AND \n" + "      course_id IN (SELECT course_id \n" + "FROM school.course \n"
                + "WHERE course_name = '" + courseName + "');";
        try (Connection connection = DBConnection.getConnection(DBConnection.getDriverWithHost(),
                DBConnection.getDbUser(), DBConnection.getDbPassword());
                PreparedStatement statement = connection.prepareStatement(query)) {
            int rowsDeleted = statement.executeUpdate();
            System.out.println(rowsDeleted + " rows deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
