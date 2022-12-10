package ua.foxminded.schoolconsoleapp.schooldata;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.github.javafaker.Faker;

public class Student {
    Faker faker = new Faker();

    public List<String> generateNames() {
        return IntStream.range(0, 20).<String>mapToObj(i -> faker.name().firstName())
                .collect(Collectors.toCollection(() -> new ArrayList<>(10)));
    }

    public List<String> generateSurnames() {
        return IntStream.range(0, 20).<String>mapToObj(i -> faker.name().lastName())
                .collect(Collectors.toCollection(() -> new ArrayList<>(10)));
    }

}
