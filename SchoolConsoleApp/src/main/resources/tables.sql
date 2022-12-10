DROP TABLE IF EXISTS school.students;
DROP TABLE IF EXISTS school.course;
DROP TABLE IF EXISTS school.group;

DROP SEQUENCE IF EXISTS school.global_seq;

DROP SCHEMA IF EXISTS school;

CREATE SCHEMA IF NOT EXISTS school
    AUTHORIZATION school_admin;
    
CREATE TABLE IF NOT EXISTS school.group (
    group_id integer NOT NULL,
    group_name character(60) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT group_pkey PRIMARY KEY (group_id)
);

CREATE UNIQUE INDEX IF NOT EXISTS group_id_index
    ON school.group USING btree
    (group_id ASC NULLS LAST)
    TABLESPACE pg_default;
    
CREATE SEQUENCE IF NOT EXISTS school.global_seq
    INCREMENT 1
    START 1;
    
CREATE TABLE IF NOT EXISTS school.students(
    student_id integer NOT NULL DEFAULT nextval('school.global_seq'::regclass),
    group_id integer,
    first_name character(30) COLLATE pg_catalog."default" NOT NULL,
    last_name character(40) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT students_pkey PRIMARY KEY (student_id),
    CONSTRAINT students_group_id_fkey FOREIGN KEY (group_id)
        REFERENCES school.group (group_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE UNIQUE INDEX IF NOT EXISTS student_id_index
    ON school.students USING btree
    (student_id ASC NULLS LAST)
    TABLESPACE pg_default;
    
CREATE TABLE IF NOT EXISTS school.course(
    course_id integer NOT NULL,
    course_name character(90) COLLATE pg_catalog."default" NOT NULL,
    course_description text COLLATE pg_catalog."default",
    CONSTRAINT course_pkey PRIMARY KEY (course_id)
);

CREATE UNIQUE INDEX IF NOT EXISTS course_id_index
    ON school.course USING btree
    (course_id ASC NULLS LAST)
    TABLESPACE pg_default;

    
    





