-- Indexes for primary keys have been explicitly created.

DROP TABLE User;
DROP TABLE BookingDay;
DROP TABLE BookingRoom;
DROP TABLE Booking;
DROP TABLE SaleRoomTariff;
DROP TABLE Tariff;
DROP TABLE SaleRoom;
DROP TABLE RoomType;


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
	idSaleRoom BIGINT NOT NULL AUTO_INCREMENT,
    idRoomType BIGINT NOT NULL,
    freeRooms SMALLINT NOT NULL,
    date DATE NOT NULL,
    CONSTRAINT AK_SaleRoom UNIQUE(idRoomType,date),
    CONSTRAINT SaleRoomPK PRIMARY KEY (idSaleRoom),
    CONSTRAINT idRoomTypeFK FOREIGN KEY(idRoomType)
        REFERENCES RoomType (id)
) ENGINE = InnoDB;

CREATE TABLE SaleRoomTariff (
	id BIGINT NOT NULL AUTO_INCREMENT,
	price DECIMAL(11,2),
	idTariff BIGINT NOT NULL,
	idSaleRoom BIGINT NOT NULL,
	CONSTRAINT SaleRoomTariffPK PRIMARY KEY (id),
	CONSTRAINT idTariffFK FOREIGN KEY(idTariff) REFERENCES Tariff (id),
	CONSTRAINT idSaleRoomFK FOREIGN KEY(idSaleRoom) REFERENCES SaleRoom (idSaleRoom)
) Engine = InnoDB;


CREATE TABLE Booking (
	id BIGINT NOT NULL AUTO_INCREMENT,
	bookingKey VARCHAR(15),
	locator VARCHAR(15),
    date DATE NOT NULL,
	startDate DATE NOT NULL,
	endDate DATE NOT NULL,
	duration SMALLINT, 
	name VARCHAR(60) NOT NULL,
	surName VARCHAR(60) NOT NULL,
	phone VARCHAR(20) NOT NULL,
	email VARCHAR(60) NOT NULL,
	petition VARCHAR(299),
	totalPrice DECIMAL(11,2),
	CONSTRAINT BookingItemPK PRIMARY KEY (id)
) Engine = InnoDB;

	CREATE TABLE BookingRoom (
	id BIGINT NOT NULL AUTO_INCREMENT,
	roomTotalPrice DECIMAL(11,2),
	idBooking BIGINT NOT NULL,
	quantity SMALLINT NOT NULL,
	CONSTRAINT BookingRoomPK PRIMARY KEY (id),
	CONSTRAINT idBookingFK FOREIGN KEY(idBooking) REFERENCES Booking (id)
) Engine = InnoDB;

	CREATE TABLE BookingDay (
	id BIGINT NOT NULL AUTO_INCREMENT,
	dayPrice DECIMAL(11,2),
	idSaleRoomTariff BIGINT NOT NULL,
	idBookingRoom BIGINT NOT NULL,
	CONSTRAINT BookingDayPK PRIMARY KEY (id),
	CONSTRAINT idBookingRoomFK FOREIGN KEY(idBookingRoom) REFERENCES BookingRoom (id),
	CONSTRAINT idSaleRoomTariffFK FOREIGN KEY(idSaleRoomTariff) REFERENCES SaleRoomTariff (id)
) Engine = InnoDB;
