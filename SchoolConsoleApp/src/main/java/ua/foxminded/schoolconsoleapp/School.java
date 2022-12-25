package ua.foxminded.schoolconsoleapp;

import ua.foxminded.schoolconsoleapp.dbconnection.InitialTables;
import ua.foxminded.schoolconsoleapp.testdatadao.CourseStudentTestDAO;
import ua.foxminded.schoolconsoleapp.testdatadao.CourseTestDAO;
import ua.foxminded.schoolconsoleapp.testdatadao.GroupTestDAO;
import ua.foxminded.schoolconsoleapp.testdatadao.StudentTestDAO;

public class School {
    
    public void startSchoolApp() {       
        InitialTables.createTables("tables.sql");

        GroupTestDAO groupDao = new GroupTestDAO();
        groupDao.autoCreate();

        StudentTestDAO student = new StudentTestDAO();
        student.autoCreate();

        CourseTestDAO course = new CourseTestDAO();
        course.autoCreate();

        CourseStudentTestDAO courseStudent = new CourseStudentTestDAO();
        courseStudent.autoCreate();     
    }

}
