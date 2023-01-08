package ua.foxminded.schoolconsoleapp.testdata;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class CourseMakerTest {

    static Set<String> generateCourses_ExpectedSet = new HashSet<>() {
        private static final long serialVersionUID = 1L;
        {
            add("Mathematics");
            add("English");
            add("Life Science");
            add("Physical Science");
            add("Geography");
            add("Computer Science");
            add("History");
            add("Sports");
            add("Art");
            add("Literature");
        }
    };

    static boolean assignCourseId_fromOneToThreePerStudentExpected() {
        CourseMaker course = new CourseMaker();
        List<Integer> list = new ArrayList<>();
        boolean assigned = false;

        for (Integer key : course.assignCourseId().keySet()) {
            list.add(course.assignCourseId().get(key).size());
        }

        if (Collections.max(list) <= 3 && Collections.min(list) >= 1 && course.assignCourseId().size() == 200) {
            assigned = true;
        }
        return assigned;
    }

    static Stream<Arguments> expectedAndActualStreamProvider() {
        CourseMaker course = new CourseMaker();
        return Stream.of(arguments(generateCourses_ExpectedSet, course.generateCourses()),
                arguments(assignCourseId_fromOneToThreePerStudentExpected(), true));
    }

    @ParameterizedTest
    @MethodSource("ua.foxminded.schoolconsoleapp.testdata.CourseMakerTest#expectedAndActualStreamProvider()")
    void coursesGenerationAndCoursesAssignation_ShouldBeEquals(Object expected, Object actual) {
        assertEquals(expected, actual);
    }

}
