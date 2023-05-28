--liquibase formatted sql

--changeset yusuf:1

--preconditions onFail:HALT onError:HALT

ALTER TABLE country ADD CONSTRAINT FK5p8a3x63k57nl1om812qkcvqbs UNIQUE (code);

--rollback ALTER TABLE country DROP CONSTRAINT FK5p8a3x63k57nl1om812qkcvqbs;





--changeset yusuf:2

--preconditions onFail:HALT onError:HALT

ALTER TABLE faculty ADD CONSTRAINT FK5p7a3x64k28nl1om812qkcvqb UNIQUE (code);

--rollback ALTER TABLE faculty DROP CONSTRAINT FK5p7a3x64k28nl1om812qkcvqb;





--changeset yusuf:3

--preconditions onFail:HALT onError:HALT

ALTER TABLE department ADD CONSTRAINT FK5p7a3x63k57nl1om812qkcvqb UNIQUE (code);

--rollback ALTER TABLE department DROP CONSTRAINT FK5p7a3x63k57nl1om812qkcvqb;





--changeset yusuf:4

--preconditions onFail:HALT onError:HALT

ALTER TABLE grade ADD CONSTRAINT FK5p7a3x63k67nl1om812qkcvqb UNIQUE (code);

--rollback ALTER TABLE grade DROP CONSTRAINT FK5p7a3x63k67nl1om812qkcvqb;





--changeset yusuf:5

--preconditions onFail:HALT onError:HALT

ALTER TABLE material_award ADD CONSTRAINT FK5p7a3x63k67nl1om812qkcvqbxvx UNIQUE (code);

--rollback ALTER TABLE material_award DROP CONSTRAINT FK5p7a3x63k67nl1om812qkcvqbxvx;





--changeset yusuf:6

--preconditions onFail:HALT onError:HALT

ALTER TABLE role ADD CONSTRAINT FK5p7a3x63k67nl1om812qkcvqbxva UNIQUE (code);

--rollback ALTER TABLE role DROP CONSTRAINT FK5p7a3x63k67nl1om812qkcvqbxva;