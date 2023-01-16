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

public class SchoolDAO {

    private SchoolDAO() {

    }

    public static List<String> findGgoupsWithLessOrEqualsStudents(int number, Connection dbConnection) {
        List<String> groupID = new ArrayList<>();
        List<String> groupName = new ArrayList<>();
        String studentCountQuery = "SELECT group_id, COUNT (*) FROM school.students GROUP BY group_id HAVING COUNT(*)<="
                + number + ";";

        try (Connection connection = dbConnection; Statement statement = connection.createStatement()) {
            ResultSet studentCountSet = statement.executeQuery(studentCountQuery);

            while (studentCountSet.next()) {
                if (studentCountSet.getString(1) != null) {
                    groupID.add(studentCountSet.getString(1));
                }
            }

            for (String s : groupID) {
                String groupNameQuery = "SELECT * FROM school.groups WHERE group_id = " + s + ";";
                ResultSet groupNameSet = statement.executeQuery(groupNameQuery);

                while (groupNameSet.next()) {
                    groupName.add(groupNameSet.getString(2));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groupName;
    }

    public static List<String> findStudentsRelatedToCourse(String courseName, Connection dbConnection) {
        String query = "SELECT first_name, last_name\n" + " FROM school.students\n"
                + "  JOIN school.students_courses_checkouts \n"
                + "    ON school.students_courses_checkouts.student_id = school.students.student_id\n"
                + "  JOIN school.course\n"
                + "    ON school.students_courses_checkouts.course_id = school.course.course_id\n"
                + " WHERE school.course.course_name = '" + courseName + "';";
        List<String> studentName = new ArrayList<>();

        try (Connection connection = dbConnection; Statement statement = connection.createStatement()) {
            ResultSet set = statement.executeQuery(query);

            while (set.next()) {
                studentName.add(set.getString(1) + set.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentName;
    }

    public static String addNewStudent(Student student, Connection dbConnection) {
        String query = "insert into school.students(group_id, first_name, last_name) values(?,?,?)";

        try (Connection connection = dbConnection; PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, student.getGroupId());
            statement.setString(2, student.getFirstName());
            statement.setString(3, student.getLastName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student.toString() + " inserted to DB";
    }

    public static int deleteStudentByID(int id, Connection dbConnection) {
        String query = "delete from school.students where student_id = " + id + ";";
        int rowsDeleted = 0;

        try (Connection connection = dbConnection; PreparedStatement statement = connection.prepareStatement(query)) {
            rowsDeleted = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsDeleted;
    }

    public static Set<Integer> getStudentID(Connection dbConnection) {
        Set<Integer> studentId = new HashSet<>();
        String query = "select student_id from school.students;";

        try (Connection connection = dbConnection; Statement statement = connection.createStatement()) {
            ResultSet set = statement.executeQuery(query);

            while (set.next()) {
                studentId.add(set.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentId;
    }

    public static String addStudentToTheCourse(Integer studentId, String courseName, Connection dbConnection) {
        String query = "INSERT INTO school.students_courses_checkouts(student_id, course_id)\n" + " SELECT\n"
                + "     (SELECT student_id FROM school.students WHERE student_id=" + studentId + "),\n"
                + "     (SELECT course_id FROM school.course WHERE course_name = '" + courseName + "');";

        try (Connection connection = dbConnection; PreparedStatement statement = connection.prepareStatement(query)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Student with ID: " + studentId.toString() + " assigned to course: " + courseName;
    }

    public static String removeStudentFromCourse(Integer studentId, String courseName, Connection dbConnection) {
        String query = "DELETE \n" + "FROM school.students_courses_checkouts\n" + "WHERE student_id='" + studentId
                + "' AND \n" + "      course_id IN (SELECT course_id \n" + "FROM school.course \n"
                + "WHERE course_name = '" + courseName + "');";
        int rowsDeleted = 0;

        try (Connection connection = dbConnection; PreparedStatement statement = connection.prepareStatement(query)) {
            rowsDeleted = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsDeleted + " student(s) with ID: " + studentId + " deleted from course " + courseName;
    }

}
