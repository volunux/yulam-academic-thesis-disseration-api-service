--liquibase formatted sql

--changeset yusuf:1

--preconditions onFail:HALT onError:HALT
--precondition-sql-check expectedResult:0 SELECT count(*) FROM information_schema.columns where table_name = 'payment_method';


CREATE TABLE payment_method (
  id SERIAL,
  title VARCHAR(100) NOT NULL,
  description VARCHAR(500) NULL,

  created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

 PRIMARY KEY (id)
);

--rollback DROP TABLE IF EXISTS "payment_method";