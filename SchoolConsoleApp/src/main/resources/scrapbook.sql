select * from school.course;
select * from school.group;
select * from school.students;
select * from school.students_courses_checkouts;

-- Find all students related to the course with the given name
SELECT first_name, last_name
  FROM school.students
  JOIN school.students_courses_checkouts 
    ON school.students_courses_checkouts.student_id = school.students.student_id
  JOIN school.course
    ON school.students_courses_checkouts.course_id = school.course.course_id
 WHERE school.course.course_name = 'History';



      
--SELECT * FROM school.group WHERE group_id = 5;
       
--insert into school.students_courses_checkouts(sometext) values('skdj');


