--liquibase formatted sql


--changeset yusuf:1

--preconditions onFail:MARK_RAN onError:MARK_RAN
--precondition-sql-check expectedResult:0 SELECT count(*) FROM country;

INSERT INTO country
    (id, title, code)
    VALUES
        (1,'Afghanistan','AFG'),
        (2,'Albania','ALB'),
        (3,'Algeria','ALG'),
        (4,'Andorra','ARA'),
        (5,'Angola','ANG'),
        (6,'Antigua and Barbuda','AAB'),
        (7,'Argentina','ARG'),
        (8,'Armenia','ARM'),
        (9,'Australia','AUS'),
        (10,'Austria','AST'),
        (11,'Azerbaijan','AZR'),
        (12,'Bahamas','BHS'),
        (13,'Bahrain','BAH'),
        (14,'Bangladesh','BNG'),
        (15,'Barbados','BAR'),
        (16,'Belarus','BEL'),
        (17,'Belgium','BLG'),
        (18,'Belize','BLZ'),
        (19,'Benin','BEN'),
        (20,'Bhutan','BHU'),
        (21,'Bolivia','BLV'),
        (22,'Bosnia and Herzegovina','BHE'),
        (23,'Botswana','BOT'),
        (24,'Brazil','BRA'),
        (25,'Brunei','BRU'),
        (26,'Bulgaria','BLG'),
        (27,'Burkina Faso','BKF'),
        (28,'Burundi','BRD'),
        (29,'Cote d''Ivoire','CDV'),
        (30,'Cabo Verde','CBV'),
        (31,'Cambodia','CAM'),
        (32,'Cameroon','CER'),
        (33,'Canada','CAN'),
        (34,'Central African Republic','CAR'),
        (35,'Chad','CHA'),
        (36,'Chile','CHI'),
        (37,'China','CHN'),
        (38,'Colombia','COL'),
        (39,'Comoros','COM'),
        (40,'Congo','COG'),
        (41,'Costa Rica','CRI'),
        (42,'Croatia','CRO'),
        (43,'Cuba','CUB'),
        (44,'Cyprus','CYP'),
        (45,'Czech Republic','CZR'),
        (46,'Democratic Republic Of Congo','DRC'),
        (47,'Denmark','DEN'),
        (48,'Djibouti','DJI'),
        (49,'Dominica','DOM'),
        (50,'Dominican Republic','DOR'),
        (51,'Ecuador','ECU'),
        (52,'Egypt','EGY'),
        (53,'El Savador','ESA'),
        (54,'Equitorial Guinea','EQG'),
        (55,'Eritrea','ERI'),
        (56,'Estonia','EST'),
        (57,'Eswatini','ESW'),
        (58,'Ethiopia','ETH'),
        (59,'Fiji','FIJ'),
        (60,'Finland','FIN'),
        (61,'France','FRA'),
        (62,'Gabon','GAB'),
        (63,'Gambia','GAM'),
        (64,'Georgia','GEO'),
        (65,'Germany','GER'),
        (66,'Ghana','GHA'),
        (67,'Greece','GRE'),
        (68,'Grenada','GRN'),
        (69,'Guatemala','GUA'),
        (70,'Guinea','GUI'),
        (71,'Guinea-Bissau','GBI'),
        (72,'Guyana','GUY'),
        (73,'Haiti','HAI'),
        (74,'Holy See','HSE'),
        (75,'Honduras','HON'),
        (76,'Hungary','HUN'),
        (77,'IceLand','ICE'),
        (78,'India','IND'),
        (79,'Indonesia','ISA'),
        (80,'Iran','IRA'),
        (81,'Iraq','IRQ'),
        (82,'Ireland','IRE'),
        (83,'Israel','ISR'),
        (84,'Italy','ITA'),
        (85,'Jamaica','JAM'),
        (86,'Japan','JAP'),
        (87,'Jordan','JOR'),
        (88,'Khazhakstan','KHZ'),
        (89,'Kenya','KEN'),
        (90,'Kiribati','KIR'),
        (91,'Kuwait','KUW'),
        (92,'Kyrgyzstan','KYR'),
        (93,'Laos','LAO'),
        (94,'Latvia','LAT'),
        (95,'Lebanon','LEB'),
        (96,'Lesotho','LES'),
        (97,'Liberia','LIB'),
        (98,'Libya','LIY'),
        (99,'Liechtenstein','LIE'),
        (100,'Lithuania','LIT'),
        (101,'Luxembourg','LUX'),
        (102,'Madagascar','MAD'),
        (103,'Malawi','MAL'),
        (104,'Malaysia','MAY'),
        (105,'Maldives','MLD'),
        (106,'Mali','MAI'),
        (107,'Malta','MAT'),
        (108,'Marshall Islands','MIS'),
        (109,'Mauritania','MAU'),
        (110,'Mauritius','MRI'),
        (111,'Mexico','MEX'),
        (112,'Micronesia','MIC'),
        (113,'Moldova','MOL'),
        (114,'Monaco','MON'),
        (115,'Mongolia','MOG'),
        (116,'Montenegro','MTG'),
        (117,'Morocco','MOR'),
        (118,'Mozambique','MOZ'),
        (119,'Myanmar','MYA'),
        (120,'Namibia','NAM'),
        (121,'Nauru','NAU'),
        (122,'Nepal','NEP'),
        (123,'Netherlands','NET'),
        (124,'New Zealand','NZL'),
        (125,'Nicaragua','NIC'),
        (126,'Niger','NGR'),
        (127,'Nigeria','NIG'),
        (128,'North Korea','NOK'),
        (129,'North Macedonia','NMA'),
        (130,'Norway','NOR'),
        (131,'Oman','OMA'),
        (132,'Pakistan','PAK'),
        (133,'Palau','PAL'),
        (134,'Palestine State','PAS'),
        (135,'Panama','PAN'),
        (136,'Papua New Guinea','PNG'),
        (137,'Paraguay','PAR'),
        (138,'Peru','PER'),
        (139,'Phillipines','PHI'),
        (140,'Poland','POL'),
        (141,'Portugal','POR'),
        (142,'Qatar','QAT'),
        (143,'Romania','ROM'),
        (144,'Russia','RUS'),
        (145,'Rwanda','RWA'),
        (146,'Saint Kitts and Levis','SKL'),
        (147,'Saint Lucia','SLU'),
        (148,'Saint Vincent and the Grenadines','SVG'),
        (149,'Samoa','SAM'),
        (150,'San Marino','SMA'),
        (151,'Sao Tome and Principe','STP'),
        (152,'Saudi Arabia','SAR'),
        (153,'Senegal','SEN'),
        (154,'Serbia','SRB'),
        (155,'Seychelles','SEY'),
        (156,'Sierra Leone','SIL'),
        (157,'Singapore','SNG'),
        (158,'Slovakia','SLO'),
        (159,'Slovenia','SLV'),
        (160,'Solomon Islands','SIS'),
        (161,'Somalia','SOL'),
        (162,'South Africa','SAF'),
        (163,'South Korea','SKO'),
        (164,'South Sudan','SSU'),
        (165,'Spain','SPA'),
        (166,'Sri Lanka','SRL'),
        (167,'Sudan','SUD'),
        (168,'Suriname','SUR'),
        (169,'Sweden','SWE'),
        (170,'Switzerland','SWZ'),
        (171,'Syria','SYR'),
        (172,'Tajikistan','TAJ'),
        (173,'Tanzania','TAZ'),
        (174,'Thailand','THA'),
        (175,'Timor-Leste','TIL'),
        (176,'Togo','TOG'),
        (177,'Tonga','TON'),
        (178,'Trinidad and Tobago','TAT'),
        (179,'Tunisia','TUN'),
        (180,'Turkey','TUR'),
        (181,'Turkmenistan','TUM'),
        (182,'Tuvalu','TUV'),
        (183,'Uganda','UGA'),
        (184,'Ukraine','UKR'),
        (185,'United Arab Emirates','UAE'),
        (186,'United Kingdom','UK'),
        (187,'United State of America','USA'),
        (188,'Uruguay','UGY'),
        (189,'Uzbekistan','UZB'),
        (190,'Vanuatu','VAN'),
        (191,'Venezuela','VNZ'),
        (192,'Vietnam','VIE'),
        (193,'Yemen','YEM'),
        (194,'Zambia','ZAM'),
        (195,'Zimbabwe','ROM');

--rollback DELETE FROM "country";