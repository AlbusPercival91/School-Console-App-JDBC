INSERT INTO school.students_courses_checkouts(student_id, course_id)
SELECT
    (SELECT student_id FROM school.students WHERE student_id=17),
    (SELECT course_id FROM school.course WHERE course_name = 'Sports');