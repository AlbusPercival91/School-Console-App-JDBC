DROP TABLE IF EXISTS school.students_courses_checkouts;
DROP TABLE IF EXISTS school.students;
DROP TABLE IF EXISTS school.course;
DROP TABLE IF EXISTS school.group;

DROP SEQUENCE IF EXISTS school.student_seq;
DROP SEQUENCE IF EXISTS school.course_seq;
DROP SEQUENCE IF EXISTS school.group_seq;

DROP SCHEMA IF EXISTS school;

CREATE SCHEMA IF NOT EXISTS school
    AUTHORIZATION school_admin;
    
CREATE SEQUENCE IF NOT EXISTS school.student_seq
    INCREMENT 1
    START 1;
    
CREATE SEQUENCE IF NOT EXISTS school.course_seq
    INCREMENT 1
    START 1;
    
CREATE SEQUENCE IF NOT EXISTS school.group_seq
    INCREMENT 1
    START 1;
    
CREATE TABLE IF NOT EXISTS school.group (
    group_id integer DEFAULT nextval('school.group_seq'::regclass),
    group_name character(60) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT group_pkey PRIMARY KEY (group_id)
);
    
CREATE TABLE IF NOT EXISTS school.students(
    student_id integer DEFAULT nextval('school.student_seq'::regclass),
    group_id integer,
    first_name character(30) COLLATE pg_catalog."default" NOT NULL,
    last_name character(40) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT students_pkey PRIMARY KEY (student_id) 
);
    
CREATE TABLE IF NOT EXISTS school.course(
    course_id integer DEFAULT nextval('school.course_seq'::regclass),
    course_name character(90) COLLATE pg_catalog."default" NOT NULL,
    course_description text COLLATE pg_catalog."default",
    CONSTRAINT course_pkey PRIMARY KEY (course_id)
);

CREATE TABLE IF NOT EXISTS school.students_courses_checkouts(
    checkouts_id SERIAL,
    student_id integer DEFAULT nextval('school.student_seq'::regclass) NOT NULL,
    course_id integer DEFAULT nextval('school.course_seq'::regclass) NOT NULL,
    CONSTRAINT students_courses_checkouts_pkey PRIMARY KEY (checkouts_id) 
);


    





