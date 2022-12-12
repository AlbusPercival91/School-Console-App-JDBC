package ua.foxminded.schoolconsoleapp.datageneration;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

public class Group {

    public List<String> generateGroups() {
        FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-GB"), new RandomService());
        return IntStream.range(0, 10).<String>mapToObj(i -> fakeValuesService.regexify("[a-z]{2}-[0-9]{2}"))
                .collect(Collectors.toCollection(() -> new ArrayList<>(10)));
    }
}
