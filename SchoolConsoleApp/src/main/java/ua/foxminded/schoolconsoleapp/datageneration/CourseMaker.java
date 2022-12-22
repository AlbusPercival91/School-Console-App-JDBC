package ua.foxminded.schoolconsoleapp.datageneration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CourseMaker {
    StudentMaker student = new StudentMaker();

    public List<String> generateCourses() {
        return new ArrayList<>(Arrays.asList("Mathematics", "English", "Life Science", "Physical Science", "Geography",
                "Computer Science", "History", "Sports", "Art", "Literature"));
    }
    
}
