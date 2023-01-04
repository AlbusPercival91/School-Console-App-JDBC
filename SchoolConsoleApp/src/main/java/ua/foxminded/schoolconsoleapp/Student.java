package ua.foxminded.schoolconsoleapp;

import ua.foxminded.schoolconsoleapp.dao.SchoolDAO;

public class Student {
    private Integer groupId;
    private String firstName;
    private String lastName;

    public Student() {

    }

    public Student(Integer groupId, String firstName, String lastName) {
        super();
        this.groupId = groupId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Student group ID = " + groupId + ", first name = " + firstName + ", last name = " + lastName;
    }

    public static void addStudent(String firstName, String lastName, Integer groupId) {
        if (!firstName.isEmpty() && !lastName.isEmpty() && groupId >= 0 && groupId <= 10) {

            if (groupId == 0) {
                groupId = null;
            }
            Student student = new Student(groupId, firstName, lastName);
            SchoolDAO.addNewStudent(student);
        } else {
            System.out.println("wrong");
        }
    }

}
