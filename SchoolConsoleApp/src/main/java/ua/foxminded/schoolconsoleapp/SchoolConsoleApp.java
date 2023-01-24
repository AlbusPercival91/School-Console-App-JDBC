package ua.foxminded.schoolconsoleapp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Scanner;

public class SchoolConsoleApp {

    public static void main(String[] args) throws IOException, InterruptedException {
        File initialScriptTempFile;

        try {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("initialScript.sql");

            initialScriptTempFile = File.createTempFile(String.valueOf(is.hashCode()), ".tmp");
            initialScriptTempFile.deleteOnExit();

            try (FileOutputStream out = new FileOutputStream(initialScriptTempFile)) {
                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = is.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }
            String cmdQuery = "psql -U postgres -h localhost -p 5432 " + "-f "
                    + Paths.get(initialScriptTempFile.toURI());
            String[] envVars = { "PGPASSWORD=1234" };
            Process runInitScript = Runtime.getRuntime().exec(cmdQuery, envVars);
            runInitScript.waitFor();
        } catch (IOException e) {
            throw new IllegalArgumentException("File not found");
        }

        SchoolData school = new SchoolData();
        school.createSchoolData();
        ConsoleMenuFacade cmf = new ConsoleMenuFacade();
        Scanner scan = new Scanner(System.in);

        String command = "";
        String menu = "a. Find all groups with less or equal students’ number\n"
                + "b. Find all students related to the course with the given name\n" + "c. Add a new student\n"
                + "d. Delete a student by the STUDENT_ID\n" + "e. Add a student to the course (from a list)\n"
                + "f. Remove the student from one of their courses\n" + "q. Quit\n";
        System.out.println("Welcome to School console application. Please choose options below:\n\n" + menu);

        while (!command.equalsIgnoreCase("q")) {
            command = scan.nextLine();

            if (command.equalsIgnoreCase("a")) {
                cmf.findGroupsWithLessOrEqualsStudentsFacade(scan);
            } else if (command.equalsIgnoreCase("b")) {
                cmf.findStudentsRelatedToCourseFacade(scan, menu);
            } else if (command.equalsIgnoreCase("c")) {
                cmf.addNewStudentFacade(scan);
            } else if (command.equalsIgnoreCase("d")) {
                cmf.deleteStudentByIdFacade(scan);
            } else if (command.equalsIgnoreCase("e")) {
                cmf.addStudentToTheCourseFacade(scan);
            } else if (command.equalsIgnoreCase("f")) {
                cmf.removeStudentFromCourseFacade(scan);
            } else if (command.equalsIgnoreCase("q")) {
                System.out.println("exit - OK!");
            } else {
                System.out.println("\n" + menu);
            }
        }
        scan.close();
    }
}
