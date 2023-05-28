--liquibase formatted sql

--changeset yusuf:1

--preconditions onFail:HALT onError:HALT
--precondition-sql-check expectedResult:0 SELECT count(*) FROM department;

INSERT INTO department(title, code, faculty_id)
    VALUES('Agric Economics and Farm Management', 'AEC', (SELECT id from faculty where code = 'AAT')),
          ('Agric Extension and Rural Development', 'AEX', (SELECT id from faculty where code = 'AAT')),
          ('Food Science Technology', 'FST', (SELECT id from faculty where code = 'AAT')),
          ('Animal Production', 'APT', (SELECT id from faculty where code = 'AAT')),
          ('Crop Production', 'CRP', (SELECT id from faculty where code = 'AAT')),
          ('Soil Science and Land Management', 'SLM', (SELECT id from faculty where code = 'AAT')),
          ('Water Resources, Aquaculture and Fisheries Technology', 'WAF', (SELECT id from faculty where code = 'AAT')),
          ('Horticulture', 'HRT', (SELECT id from faculty where code = 'AAT'));

INSERT INTO department(title, code, faculty_id)
    VALUES('Industrial and Technology Education', 'ITE', (SELECT id from faculty where code = 'EDT')),
          ('Chemistry Education', 'EDC', (SELECT id from faculty where code = 'EDT')),
          ('Educational Technology', 'EDT', (SELECT id from faculty where code = 'EDT')),
          ('Biology Education', 'EDB', (SELECT id from faculty where code = 'EDT')),
          ('Mathematics Education', 'EDM', (SELECT id from faculty where code = 'EDT'));

INSERT INTO department(title, code, faculty_id)
    VALUES('Agric and BioResources Engineering', 'ABE', (SELECT id from faculty where code = 'IPT')),
          ('Chemical Engineering', 'CHE', (SELECT id from faculty where code = 'IPT')),
          ('Civil Engineering', 'CIE', (SELECT id from faculty where code = 'IPT')),
          ('Mechanical Engineering', 'MEE', (SELECT id from faculty where code = 'IPT')),
          ('Material and Metallurgical Engineering', 'MME', (SELECT id from faculty where code = 'IPT')),
          ('Petroleum and Gas Engineering', 'PGE', (SELECT id from faculty where code = 'IPT'));

INSERT INTO department(title, code, faculty_id)
    VALUES('Telecommunication Engineering', 'TME', (SELECT id from faculty where code = 'EET')),
          ('Computer Engineering', 'CPE', (SELECT id from faculty where code = 'EET')),
          ('Mechatronics Engineering', 'MCE', (SELECT id from faculty where code = 'EET')),
          ('Electrical and Electronics Engineering', 'EEE', (SELECT id from faculty where code = 'EET'));

INSERT INTO department(title, code, faculty_id)
    VALUES('Architecture', 'ARC', (SELECT id from faculty where code = 'ET')),
          ('Building Technology', 'BDT', (SELECT id from faculty where code = 'ET')),
          ('Quantity Surveying', 'QTS', (SELECT id from faculty where code = 'ET')),
          ('Surveying and Geo-Informatics', 'SVG', (SELECT id from faculty where code = 'ET')),
          ('Urban and Regional Planning', 'URP', (SELECT id from faculty where code = 'ET')),
          ('Estate Management and Valuation', 'EMV', (SELECT id from faculty where code = 'ET'));

INSERT INTO department(title, code, faculty_id)
    VALUES('Logistics and Transport Management', 'LTM', (SELECT id from faculty where code = 'IT')),
          ('Project Management Technology', 'PMT', (SELECT id from faculty where code = 'IT'));

INSERT INTO department(title, code, faculty_id)
    VALUES('Animal Biology', 'AMB', (SELECT id from faculty where code = 'LS')),
          ('Biochemistry', 'BCH', (SELECT id from faculty where code = 'LS')),
          ('Microbiology', 'MCB', (SELECT id from faculty where code = 'LS')),
          ('Plant Biology', 'PLB', (SELECT id from faculty where code = 'LS'));

INSERT INTO department(title, code, faculty_id)
    VALUES('Mathematics', 'MAT', (SELECT id from faculty where code = 'PS')),
          ('Physics', 'PHY', (SELECT id from faculty where code = 'PS')),
          ('Chemistry', 'CHM', (SELECT id from faculty where code = 'PS')),
          ('Geography', 'GRY', (SELECT id from faculty where code = 'PS')),
          ('Geology', 'GEL', (SELECT id from faculty where code = 'PS')),
          ('Statistics', 'STA', (SELECT id from faculty where code = 'PS')),
          ('Applied Geophysics', 'GPH', (SELECT id from faculty where code = 'PS'));

INSERT INTO department(title, code, faculty_id)
    VALUES('Computer Science', 'CPT', (SELECT id from faculty where code = 'ICT')),
          ('Cyber Security Science', 'CSS', (SELECT id from faculty where code = 'ICT')),
          ('Information Technology', 'IMT', (SELECT id from faculty where code = 'ICT')),
          ('Information Science and Media Studies', 'IMS', (SELECT id from faculty where code = 'ICT')),
          ('Library and Information Technology', 'LIT', (SELECT id from faculty where code = 'ICT'));

--rollback DELETE FROM "department";