package ua.foxminded.schoolconsoleapp.schooldata;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.github.javafaker.Faker;

public class Course {

    public List<String> generateCourses() {
        Faker faker = new Faker();
        return IntStream.range(0, 10).<String>mapToObj(i -> faker.educator().course())
                .collect(Collectors.toCollection(() -> new ArrayList<>(10)));
    }
}
