package ua.foxminded.schoolconsoleapp;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class SchoolConsoleApp {

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

//        String command = "psql -U postgres -h localhost -p 5432 " + "-f "
//                + Paths.get(classLoader.getResource("initialScript.sql").toURI());
//        String[] envVars = { "PGPASSWORD=1234" };
//        Process runInitScript = Runtime.getRuntime().exec(command, envVars);
//        runInitScript.waitFor();

        ConsoleMenu consoleMenu = new ConsoleMenu();
        consoleMenu.launchMenu();
     
    }
}
