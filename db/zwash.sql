--
-- PostgreSQL port of the MySQL "fastwash" database.
--


BEGIN;
CREATE USER zeriab;
CREATE DATABASE zwash;
GRANT ALL PRIVILEGES ON DATABASE zwash TO zeriab;

SET client_encoding = 'LATIN1';

CREATE TABLE zwashuser (
    id integer NOT NULL,
    firstname character(50) NOT NULL,
    lastname  character(50) NOT NULL,
    username character(20) NOT NULL,
    password character(20) NOT NULL,
    dateOfBirth Date NOT NULL,
    secretQuestion character(100) NOT NULL,
    secretAnswer character(100) NOT NULL,
    active boolean NOT NULL
);

CREATE TABLE car (
    id integer NOT NULL,
    user_id integer NOT NULL,
    registerationPlate character(20) NOT NULL,
    mark character(20) NOT NULL,
    datetime Date NOT NULL
);


ALTER TABLE ONLY zwashuser
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);

ALTER TABLE ONLY car
    ADD CONSTRAINT car_pkey PRIMARY KEY (id);


ALTER TABLE ONLY car
    ADD CONSTRAINT user_fkey FOREIGN KEY (user_id) REFERENCES zwashuser(id);


COMMIT;

ANALYZE zwashuser;
ANALYZE car;


