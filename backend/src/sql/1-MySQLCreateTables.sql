-- Indexes for primary keys have been explicitly created.

DROP TABLE User;
DROP TABLE Tariff;


CREATE TABLE User (
    id BIGINT NOT NULL AUTO_INCREMENT,
    userName VARCHAR(60) COLLATE latin1_bin NOT NULL,
    password VARCHAR(60) NOT NULL, 
    firstName VARCHAR(60) NOT NULL,
    lastName VARCHAR(60) NOT NULL, 
    email VARCHAR(60) NOT NULL,
    role TINYINT NOT NULL,
    CONSTRAINT UserPK PRIMARY KEY (id),
    CONSTRAINT UserNameUniqueKey UNIQUE (userName)
) ENGINE = InnoDB;

CREATE INDEX UserIndexByUserName ON User (userName);


CREATE TABLE Tariff (
    id BIGINT NOT NULL AUTO_INCREMENT,
    tariffName VARCHAR(60) COLLATE latin1_bin NOT NULL,
    tariffCode VARCHAR(60) COLLATE latin1_bin NOT NULL,
    CONSTRAINT TariffPK PRIMARY KEY (id),
    CONSTRAINT TariffNameUniqueKey UNIQUE (tariffName),
    CONSTRAINT TariffCodeUniqueKey UNIQUE (tariffCode)
) ENGINE = InnoDB;

