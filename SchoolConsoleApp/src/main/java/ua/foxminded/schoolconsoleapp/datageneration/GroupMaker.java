package ua.foxminded.schoolconsoleapp.datageneration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

public class GroupMaker {
    StudentMaker student = new StudentMaker();

    public List<String> generateGroups() {
        FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-GB"), new RandomService());
        return IntStream.range(0, 10).<String>mapToObj(i -> fakeValuesService.regexify("[a-z]{2}-[0-9]{2}"))
                .collect(Collectors.toCollection(() -> new ArrayList<>(10)));
    }

    public List<Integer> generateGroupId() {
        List<Integer> id = new ArrayList<>();

        IntStream.range(0, student.generateStudents(student.generateNames(20), student.generateSurnames(20)).size())
                .forEach(s -> {
                    Integer randomID = ThreadLocalRandom.current().nextInt(0, 11);

                    if (randomID == 0) {
                        randomID = null;
                    }

                    if (Collections.frequency(id, randomID) <= 30 || Collections.frequency(id, randomID) >= 10) {
                        Collections.addAll(id, randomID);
                    }
                });
        return id;
    }
}
