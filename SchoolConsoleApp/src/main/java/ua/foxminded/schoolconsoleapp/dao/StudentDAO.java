package ua.foxminded.schoolconsoleapp.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import ua.foxminded.schoolconsoleapp.dbconnection.DataBaseConnection;

public class StudentDAO {

    public static void findGgoupsWithLessOrEqualsStudents(int number) {
        String query = "SELECT group_id, COUNT (*) FROM school.students GROUP BY group_id HAVING COUNT(*)<=" + number
                + ";";

        try (Connection connection = DataBaseConnection.connect(); Statement statement = connection.createStatement()) {
            ResultSet set = statement.executeQuery(query);

            while (set.next()) {
                int groupID = set.getInt(1);
                int count = set.getInt(2);

                System.out.println("Group ID: " + groupID + "\ncount: " + count);
                System.out.println("----------------------------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
