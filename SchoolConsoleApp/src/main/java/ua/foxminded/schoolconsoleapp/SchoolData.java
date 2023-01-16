package ua.foxminded.schoolconsoleapp;

import ua.foxminded.schoolconsoleapp.dao.DummyDataDAO;
import ua.foxminded.schoolconsoleapp.dbconnection.DBConnection;
import ua.foxminded.schoolconsoleapp.dbconnection.ScriptReader;

public class SchoolData {

    public void createSchoolData() {
        ScriptReader.readSqlScript("create_tables.sql", DBConnection.getPsqlConnection());

        DummyDataDAO testData = new DummyDataDAO();
        testData.createGroup(DBConnection.getPsqlConnection());
        testData.createStudent(DBConnection.getPsqlConnection());
        testData.createCourse(DBConnection.getPsqlConnection());
        testData.createCourseStudentRelation(DBConnection.getPsqlConnection());
    }
}
