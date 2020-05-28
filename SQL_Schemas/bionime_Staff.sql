CREATE TABLE `bionime_Staff` (
  `ID` varchar(40) NOT NULL,
  `NAME` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `SERVICE_SITE` longtext,
  `LAST_UPDATE` datetime,
  `CREATE_DATE` datetime,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4