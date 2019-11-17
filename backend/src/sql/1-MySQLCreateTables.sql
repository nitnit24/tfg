-- Indexes for primary keys have been explicitly created.

DROP TABLE User;
DROP TABLE Tariff;
DROP TABLE SaleRoom;
DROP TABLE RoomType;
--DROP TABLE Tariff_SaleRoom


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

CREATE TABLE RoomType (
    id BIGINT NOT NULL AUTO_INCREMENT,
    typeName VARCHAR(60) NOT NULL,
    capacity SMALLINT NOT NULL,
    minPrice DECIMAL(11, 2),
    maxPrice DECIMAL(11, 2),
    CONSTRAINT RoomTypePK PRIMARY KEY (id),
    CONSTRAINT TypeNameUniqueKey UNIQUE (typeName)
) ENGINE = InnoDB;

CREATE TABLE SaleRoom (
    id BIGINT NOT NULL,
    freeRooms SMALLINT NOT NULL,
    date DATETIME NOT NULL,
    CONSTRAINT SaleRoomPK PRIMARY KEY (id, date),
      CONSTRAINT idRoomTypeFK FOREIGN KEY(id)
        REFERENCES RoomType (id)
) ENGINE = InnoDB;

--CREATE TABLE Tariff_SaleRoom (
--	id_tariff NOT NULL,
--	id_roomType NOT NULL,
--	date DATETIME NOT NULL,
--	price DECIMAL(11,2),
--	CONSTRAINT TariffSaleRoomPK PRIMARY KEY (id_tariff, id_roomType, date),
--	CONSTRAINT idTariffFK FOREIGN KEY(id_tariff) REFERENCES Tariff (id),
--	CONSTRAINT idSaleRoomFK FOREIGN KEY(id_roomType) REFERENCES SaleRoom (id),
--	CONSTRAINT dateFK FOREIGN KEY(date) REFERENCES SaleRoom (date)
	
--) Engine = InnoDB

