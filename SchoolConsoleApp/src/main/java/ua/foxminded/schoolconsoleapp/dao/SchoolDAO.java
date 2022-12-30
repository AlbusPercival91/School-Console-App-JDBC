package ua.foxminded.schoolconsoleapp.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ua.foxminded.schoolconsoleapp.dbconnection.DataBaseConnection;

public class SchoolDAO {

    public List<String> findGgoupsWithLessOrEqualsStudents(int number) {
        List<String> groupID = new ArrayList<>();
        List<String> groupName = new ArrayList<>();
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
                    groupName.add(groupNameSet.getString(2));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return groupName;
    }

}
