-- Role: school_admin
DROP ROLE IF EXISTS school_admin;

CREATE ROLE school_admin WITH
    LOGIN
    SUPERUSER
    CREATEDB
    CREATEROLE
    INHERIT
    REPLICATION
    CONNECTION LIMIT -1;



    