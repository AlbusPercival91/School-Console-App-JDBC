package ua.foxminded.schoolconsoleapp.datageneration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import com.github.javafaker.Faker;

public class Student {
    Faker faker = new Faker();

    public List<String> generateNames() {
        return IntStream.range(0, 20).<String>mapToObj(i -> faker.name().firstName())
                .collect(Collectors.toCollection(() -> new ArrayList<>(20)));
    }

    public List<String> generateSurnames() {
        return IntStream.range(0, 20).<String>mapToObj(i -> faker.name().lastName())
                .collect(Collectors.toCollection(() -> new ArrayList<>(20)));
    }

    public Set<String> generateStudents(List<String> names, List<String> surnames) {
        Set<String> list = new HashSet<>();
        while (list.size() != 200) {
            Arrays.asList(names, surnames).forEach(Collections::shuffle);
            Collections.addAll(list, names.get(0) + " " + surnames.get(0));
        }
        return list;
    }

}
