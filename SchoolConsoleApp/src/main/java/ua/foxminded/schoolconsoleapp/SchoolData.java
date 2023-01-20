package ua.foxminded.schoolconsoleapp;

import ua.foxminded.schoolconsoleapp.dao.DummyDataDAO;
import ua.foxminded.schoolconsoleapp.dbconnection.DBConnection;
import ua.foxminded.schoolconsoleapp.dbconnection.DataReader;

public class SchoolData {
    private static final String PSQL = "psql.properties";

    public void createSchoolData() {
        DataReader.readSqlScript("create_tables.sql", DBConnection.getConnection(PSQL));

        DummyDataDAO testData = new DummyDataDAO();
        testData.createGroup(DBConnection.getConnection(PSQL));
        testData.createStudent(DBConnection.getConnection(PSQL));
        testData.createCourse(DBConnection.getConnection(PSQL));
        testData.createCourseStudentRelation(DBConnection.getConnection(PSQL));
    }
}
