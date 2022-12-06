DROP DATABASE IF EXISTS school;
DROP ROLE IF EXISTS school_admin;
CREATE USER school_admin;

CREATE DATABASE school
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.UTF-8'
    LC_CTYPE = 'en_US.UTF-8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;
    
GRANT ALL PRIVILEGES ON DATABASE school TO school_admin;


    