--liquibase formatted sql

--changeset yusuf:1

--preconditions onFail:HALT onError:HALT
--precondition-sql-check expectedResult:0 SELECT count(*) FROM faculty;

INSERT INTO faculty(title, code)
    VALUES('Agriculture and Agricultural Technology', 'AAT'),
          ('Electrical and Engineering Technology', 'EET'),
          ('Infrastructure, Process Engineering and Technology', 'IPT'),
          ('Environmental Technology', 'ET'),
          ('Entrepreneurship and Management Technology', 'EMT'),
          ('Educational Technology', 'EDT'),
          ('Innovative Technology', 'IT'),
          ('Life Sciences', 'LS'),
          ('Physical Sciences', 'PS'),
          ('Information and Communication Technology', 'ICT'),
          ('Science and Technology Education', 'STE'),
          ('Postgraduate', 'PGS');

--rollback DELETE FROM "faculty";
