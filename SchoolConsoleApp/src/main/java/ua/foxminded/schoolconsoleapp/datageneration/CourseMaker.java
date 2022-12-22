package ua.foxminded.schoolconsoleapp.datageneration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class CourseMaker {
    StudentMaker student = new StudentMaker();

    public List<String> generateCourses() {
        return new ArrayList<>(Arrays.asList("Mathematics", "English", "Life Science", "Physical Science", "Geography",
                "Computer Science", "History", "Sports", "Art", "Literature"));
    }

    public Map<Integer, Set<Integer>> assignCourseId() {
        Map<Integer, Set<Integer>> studentCourseID = new HashMap<>();

        IntStream.range(1, 201).forEach(i -> {
            Set<Integer> courseID = new HashSet<>();
            Integer listSize = ThreadLocalRandom.current().nextInt(1, 4);

            IntStream.range(0, listSize).mapToObj(j -> ThreadLocalRandom.current().nextInt(1, 11))
                    .filter(rand -> Collections.frequency(courseID, rand) < 3).forEach(courseID::add);
            studentCourseID.put(i, courseID);
        });
        return studentCourseID;
    }

}
