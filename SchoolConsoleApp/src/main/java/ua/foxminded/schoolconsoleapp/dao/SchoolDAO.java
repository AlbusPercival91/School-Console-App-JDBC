package ua.foxminded.schoolconsoleapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ua.foxminded.schoolconsoleapp.Student;
import ua.foxminded.schoolconsoleapp.dbconnection.DataBaseConnection;

public class SchoolDAO {

    private SchoolDAO() {

    }

    public static void findGgoupsWithLessOrEqualsStudents(int number) {
        List<String> groupID = new ArrayList<>();
        String studentCountQuery = "SELECT group_id, COUNT (*) FROM school.students GROUP BY group_id HAVING COUNT(*)<="
                + number + ";";

        try (Connection connection = DataBaseConnection.connect(); Statement statement = connection.createStatement()) {
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

        try (Connection connection = DataBaseConnection.connect(); Statement statement = connection.createStatement()) {
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
        try (Connection connection = DataBaseConnection.connect();
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
        try (Connection connection = DataBaseConnection.connect();
                PreparedStatement statement = connection.prepareStatement(query)) {
            int rowsDeleted = statement.executeUpdate();
            System.out.println(rowsDeleted + " rows deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
