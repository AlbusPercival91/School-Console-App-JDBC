package ua.foxminded.schoolconsoleapp.schooldata;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.IntStream;

import com.github.javafaker.Faker;

public class Student {

    public Map<String, String> generateStudents() {
        Faker faker = new Faker();
        return IntStream.range(0, 20).collect(LinkedHashMap::new,
                (m, i) -> m.put(faker.name().firstName(), faker.name().lastName()), Map::putAll);
    }
}
