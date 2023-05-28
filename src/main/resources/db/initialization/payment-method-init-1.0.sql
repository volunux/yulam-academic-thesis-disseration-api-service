--liquibase formatted sql

--changeset yusuf:1

--preconditions onFail:HALT onError:HALT
--precondition-sql-check expectedResult:0 SELECT count(*) FROM payment_method;

INSERT INTO payment_method(title)
VALUES('Credit or Debit Card'),
      ('Bank Transfer'),
      ('Digital Payment'),
      ('USSD');

--rollback DELETE FROM "payment_method";
