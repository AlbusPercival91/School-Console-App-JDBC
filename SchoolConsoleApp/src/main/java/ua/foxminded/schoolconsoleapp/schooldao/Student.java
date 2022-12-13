package ua.foxminded.schoolconsoleapp.schooldao;

public class Student {
    private String studentName;
    private String studentSurname;

    public Student(String studentName, String studentSurname) {
        super();
        this.studentName = studentName;
        this.studentSurname = studentSurname;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentSurname() {
        return studentSurname;
    }

    public void setStudentSurname(String studentSurname) {
        this.studentSurname = studentSurname;
    }

    @Override
    public String toString() {
        return "Student [studentName=" + studentName + ", studentSurname=" + studentSurname + "]";
    }

}
