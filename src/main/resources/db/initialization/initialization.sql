--liquibase formatted sql

--changeset yusuf:1

--preconditions onFail:HALT onError:HALT
--precondition-sql-check expectedResult:0 SELECT count(*) FROM information_schema.columns where table_name = 'country';


CREATE TABLE country (
  id SERIAL,
  title VARCHAR(100) NOT NULL,
  code VARCHAR(5) NOT NULL,

  founding_year INT NULL,
  map_logo_url VARCHAR(500) NULL,

  created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

  PRIMARY KEY (id)
);

--rollback DROP TABLE IF EXISTS "country";





--changeset yusuf:2

--preconditions onFail:HALT onError:HALT
--precondition-sql-check expectedResult:0 SELECT count(*) FROM information_schema.columns where table_name = 'state';

CREATE TABLE state (
  id SERIAL,
  title VARCHAR(100) NOT NULL,
  capital VARCHAR(20) NOT NULL,

  about VARCHAR(1000) NULL,
  founding_year INT NULL DEFAULT 0000,
  map_logo_url VARCHAR(500) NULL,

  country_id INT NOT NULL,

  created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

  PRIMARY KEY (id),

  CONSTRAINT FK5p7a3x63k57nl1om7l7qkcvqw
    FOREIGN KEY (country_id)
      REFERENCES country (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

--rollback DROP TABLE IF EXISTS "state";





--changeset yusuf:3

--preconditions onFail:HALT onError:HALT
--precondition-sql-check expectedResult:0 SELECT count(*) FROM information_schema.columns where table_name = 'role';

CREATE TABLE role (
   id SERIAL,
   title VARCHAR(100) NOT NULL,
   code VARCHAR(100) NOT NULL,
   description VARCHAR(1000) NULL,

   created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

   PRIMARY KEY (id)
);

--rollback DROP TABLE IF EXISTS "role";





--changeset yusuf:4

--preconditions onFail:HALT onError:HALT
--precondition-sql-check expectedResult:0 SELECT count(*) FROM information_schema.columns where table_name = 'faculty';

CREATE TABLE faculty (
  id SERIAL,
  title VARCHAR(200) NOT NULL,
  code VARCHAR(5) NOT NULL,
  description VARCHAR(1000) NULL,

  created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

  PRIMARY KEY (id)
);

--rollback DROP TABLE IF EXISTS "faculty";





--changeset yusuf:5

--preconditions onFail:HALT onError:HALT
--precondition-sql-check expectedResult:0 SELECT count(*) FROM information_schema.columns where table_name = 'department';

CREATE TABLE department (
  id SERIAL,
  title VARCHAR(200) NOT NULL,
  code VARCHAR(5) NOT NULL,
  description VARCHAR(1000) NULL,

  faculty_id INT NOT NULL,

  created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

  PRIMARY KEY (id),

  CONSTRAINT FK5p7a3x63k57nl1om7l7qkcvqa
    FOREIGN KEY (faculty_id)
      REFERENCES faculty (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

--rollback DROP TABLE IF EXISTS "department";





--changeset yusuf:6

--preconditions onFail:HALT onError:HALT
--precondition-sql-check expectedResult:0 SELECT count(*) FROM information_schema.columns where table_name = 'institution';

CREATE TABLE institution (
  id SERIAL,
  title VARCHAR(200) NOT NULL,
  location VARCHAR(200) NULL,
  founding_year INT NULL,

  country_id INT NULL,
  state_id INT NULL,

  created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

  PRIMARY KEY (id),

  CONSTRAINT FK5p7a3x63k57nl1om7l7qkcvqb
    FOREIGN KEY (country_id)
        REFERENCES country (id)
        ON UPDATE SET NULL
        ON DELETE SET NULL,
  CONSTRAINT FK5p7a3x63k57nl1om7l7qkcvqc
      FOREIGN KEY (state_id)
          REFERENCES state (id)
          ON UPDATE SET NULL
          ON DELETE SET NULL
);

--rollback DROP TABLE IF EXISTS "institution";





--changeset yusuf:7

--preconditions onFail:HALT onError:HALT
--precondition-sql-check expectedResult:0 SELECT count(*) FROM information_schema.columns where table_name = 'grade';

CREATE TABLE grade (
  id SERIAL,
  title VARCHAR(100) NOT NULL,
  code VARCHAR(5) NOT NULL,
  description VARCHAR(500),

  created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

  PRIMARY KEY (id)
);

--rollback DROP TABLE IF EXISTS "grade";





--changeset yusuf:8

--preconditions onFail:HALT onError:HALT
--precondition-sql-check expectedResult:0 SELECT count(*) FROM information_schema.columns where table_name = 'material_award';

CREATE TABLE material_award (
  id SERIAL,
  title VARCHAR(100) NOT NULL,
  code VARCHAR(20) NOT NULL,
  description VARCHAR(500),

  created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

  PRIMARY KEY (id)
);

--rollback DROP TABLE IF EXISTS "material_award";






--changeset yusuf:9

--preconditions onFail:HALT onError:HALT
--precondition-sql-check expectedResult:0 SELECT count(*) FROM information_schema.columns where table_name = 'member';

CREATE TABLE member (
  id SERIAL,
  first_name VARCHAR(100) NOT NULL,
  last_name VARCHAR(100) NOT NULL,
  email_address VARCHAR(150) NOT NULL,
  password_hash VARCHAR(500) NOT NULL,
  avatar VARCHAR(500) NOT NULL,
  email_address_verified BOOLEAN NOT NULL DEFAULT 'false',

  created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

  PRIMARY KEY (id),
  CONSTRAINT FK5p7a3x63k57nl1om7l7qkcvqj
    UNIQUE (email_address)
);

--rollback DROP TABLE IF EXISTS "member";





--changeset yusuf:10

--preconditions onFail:HALT onError:HALT
--precondition-sql-check expectedResult:0 SELECT count(*) FROM information_schema.columns where table_name = 'author';

CREATE TABLE author (
  id SERIAL,
  institution_email_address VARCHAR(150) NOT NULL,
  institution_email_address_verified BOOLEAN NOT NULL DEFAULT 'false',
  graduation_year INT NOT NULL,

  created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

  member_id INT NOT NULL,

  PRIMARY KEY (id),

  CONSTRAINT FK5p7a3x63k57nl1om7l7qkcvqg
    FOREIGN KEY (member_id)
      REFERENCES member (id)
      ON UPDATE CASCADE
      ON DELETE CASCADE
);

--rollback DROP TABLE IF EXISTS "author";






--changeset yusuf:11

--preconditions onFail:HALT onError:HALT
--precondition-sql-check expectedResult:0 SELECT count(*) FROM information_schema.columns where table_name = 'academic_material';

CREATE TABLE material (
  id SERIAL,
  title VARCHAR(500) NOT NULL,
  material_abstract VARCHAR(5000) NOT NULL,
  material_type VARCHAR(200) NOT NULL,
  year INT NOT NULL,

  created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

  grade_id INT NULL,
  material_award_id INT NULL,
  author_id INT NOT NULL,

  PRIMARY KEY (id),

  CONSTRAINT FK5p7a3x63k57nl1om7l7qkcvqd
    FOREIGN KEY (grade_id)
      REFERENCES grade (id)
        ON UPDATE SET NULL
        ON DELETE SET NULL,
  CONSTRAINT FK5p7a3x63k57nl1om7l7qkcvqe
    FOREIGN KEY (material_award_id)
      REFERENCES material_award (id)
        ON UPDATE SET NULL
        ON DELETE SET NULL,
  CONSTRAINT FK5p7a3x63k57nl1om7l7qkcvqf
    FOREIGN KEY (author_id)
      REFERENCES author (id)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
);

--rollback DROP TABLE IF EXISTS "material";





--changeset yusuf:12

--preconditions onFail:HALT onError:HALT
--precondition-sql-check expectedResult:0 SELECT count(*) FROM information_schema.columns where table_name = 'member_role';

CREATE TABLE member_role (
  member_id INT NOT NULL,
  role_id INT NOT NULL,

  PRIMARY KEY (member_id, role_id),

  CONSTRAINT FK5p7a3x63k57nl1om7l7qkcvqh
    FOREIGN KEY (member_id)
      REFERENCES member (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,

  CONSTRAINT FK5p7a3x63k57nl1om7l7qkcvqi
    FOREIGN KEY (role_id)
      REFERENCES role (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

--rollback DROP TABLE IF EXISTS "member_role";