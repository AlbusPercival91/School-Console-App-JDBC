package ua.foxminded.schoolconsoleapp.schooldata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Student {

    public List<String> generateNames() {
        return new ArrayList<>(Arrays.asList("Dusty", "Drew", "Margie", "Marcus", "Quentin", "Glayds", "Naoma", "Eloy",
                "Starla", "Holley", "Alida", "Dillon", "Alphonse", "Augusta", "Rudy", "Carole", "Dorsey", "Forrest",
                "Oscar", "Harry"));
    }

    public List<String> generateSurnames() {
        return new ArrayList<>(Arrays.asList("MacGyver", "Gottlieb", "Bradtke", "Koelpin", "Krajcik", "Sawayn",
                "Harvey", "Ankunding", "Dickens", "Lynch", "Stanton", "Wiegand", "Toy", " O'Reilly", "Skiles", "Mohran",
                "Rutherford", "Lehner", "Hoeger", "Towne"));
    }

    public Set<String> generateStudents(List<String> names, List<String> surnames) {
        Set<String> list = new HashSet<>();

        while (list.size() != 200) {
            Collections.shuffle(names);
            Collections.shuffle(surnames);
            Collections.addAll(list, names.get(0) + " " + surnames.get(0));
        }
        return list;
    }

//    public Map<String, String> generateStudents(List<String> names, List<String> surnames) {
//        Map<String, String> students = new LinkedHashMap<>();
//
//        return students;
//    }

//    Faker faker = new Faker();
//
//    public List<String> generateNames() {
//        return IntStream.range(0, 20).<String>mapToObj(i -> faker.name().firstName())
//                .collect(Collectors.toCollection(() -> new ArrayList<>(10)));
//    }
//
//    public List<String> generateSurnames() {
//        return IntStream.range(0, 20).<String>mapToObj(i -> faker.name().lastName())
//                .collect(Collectors.toCollection(() -> new ArrayList<>(10)));
//    }
    
    
//    System.out.println("-----------------------------------------------------");
//    Set<String> set = new HashSet<>();
//    for (String name : list) {
//        if (set.add(name) == false) {
//            System.out.println(name);
//        }
//    }
//
//    System.out.println("-----------------------------------------------------");

}
