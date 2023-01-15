package ua.foxminded.schoolconsoleapp;

import ua.foxminded.schoolconsoleapp.dao.TestDataDAO;
import ua.foxminded.schoolconsoleapp.dbconnection.DBConnection;
import ua.foxminded.schoolconsoleapp.dbconnection.ScriptReader;

public class School {

    public void createSchoolData() {
        ScriptReader.readSqlScript("create_tables.sql", DBConnection.getDbUrl(), DBConnection.getDbUser(),
                DBConnection.getDbPassword());

        TestDataDAO testData = new TestDataDAO();
        testData.createGroup();
        testData.createStudent();
        testData.createCourse();
        testData.createCourseStudentRelation();
    }
}
