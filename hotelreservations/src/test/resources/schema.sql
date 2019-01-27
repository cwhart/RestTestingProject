-- drop the database if it already exists, and re-create.
--DROP DATABASE if exists HotelReservation;

--CREATE DATABASE HotelReservation;

--USE HotelReservation;

CREATE TABLE if not exists Room
(
   ID INT NOT NULL auto_increment,
   FloorNumber INT NOT NULL,
   RoomNumber INT NOT NULL,
   Occupancy INT NOT NULL,
   RoomType VARCHAR(20) NOT NULL,
   PRIMARY KEY (ID)
);

CREATE TABLE if not exists RoomRate
(
   ID INT NOT NULL auto_increment,
   RoomID INT NOT NULL,
   DefaultFlag ENUM('D','S') NOT NULL,
   StartDate DATE NOT NULL,
   EndDate DATE NULL,
   Price DECIMAL(6,2) NOT NULL,
   PRIMARY KEY (ID),
   CONSTRAINT fk_RoomRate_Room
   FOREIGN KEY(RoomID) REFERENCES Room(ID)
);

CREATE TABLE if not exists Amenity
(
	ID INT NOT NULL auto_increment,
    Type VARCHAR(30) NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE if not exists RoomAmenity
(
	AmenityID INT NOT NULL,
    RoomID INT NOT NULL,
    CONSTRAINT fk_RoomAmenity_Amenity
    FOREIGN KEY (AmenityID) REFERENCES Amenity(ID),
    CONSTRAINT fk_RoomAmenity_Room
    FOREIGN KEY (RoomID) REFERENCES Room(ID)
);

CREATE TABLE if not exists PromoRate
(
	ID INT NOT NULL auto_increment,
    Type VARCHAR(1) NOT NULL,
    Rate DECIMAL(6,2) NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE if not exists PromoType
(
	ID INT NOT NULL auto_increment,
    PromoRateID INT NOT NULL,
    PromoCode VARCHAR(10),
    Description VARCHAR(50),
    PRIMARY KEY (ID),
    CONSTRAINT fk_PromoType_PromoRate
    FOREIGN KEY (PromoRateID) REFERENCES PromoRate(ID)
);

CREATE TABLE if not exists Promo
(
	ID INT NOT NULL auto_increment,
    PromoTypeID INT NOT NULL,
    StartDate Date NOT NULL,
    EndDate Date NOT NULL,
    PRIMARY KEY (ID),
    CONSTRAINT fk_Promo_PromoType
    FOREIGN KEY (PromoTypeID) REFERENCES PromoType(ID)
);

CREATE TABLE if not exists AddOn
(
	ID INT NOT NULL auto_increment,
    Type VARCHAR(30) NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE if not exists AddOnRate
(
	ID INT NOT NULL auto_increment,
    AddOnID INT NOT NULL,
    StartDate Date NOT NULL,
    EndDate Date NULL,
    DefaultFlag ENUM('D','S') NOT NULL,
    Price Decimal(6,2) NOT NULL,
    Primary Key (ID),
    CONSTRAINT fk_AddOnRate_AddOn
    FOREIGN KEY (AddOnID) REFERENCES AddOn(ID)
);

CREATE TABLE if not exists Tax
(
	ID INT NOT NULL auto_increment,
    Type VARCHAR(20),
    State VARCHAR(2),
    StartDate Date NOT NULL,
    EndDate Date NULL,
    Rate Decimal(4,2) NOT NULL,
    Primary Key (ID)
);

CREATE TABLE if not exists Person
(
	ID INT NOT NULL auto_increment,
    FirstName VARCHAR(30) NOT NULL,
    LastName VARCHAR(30) NOT NULL,
    DateOfBirth Date,
    Phone VARCHAR(12),
    Email VARCHAR(50),
    Primary Key (ID)
);

CREATE TABLE if not exists Guest
(
	ID INT NOT NULL auto_increment,
    PersonID INT NOT NULL,
    Primary Key (ID),
    CONSTRAINT fk_Guest_Person
    FOREIGN KEY (PersonID) REFERENCES Person(ID)
);

CREATE TABLE if not exists ReservationHolder
(
	ID INT NOT NULL auto_increment,
    PersonID INT NOT NULL,
    Primary Key (ID),
    CONSTRAINT fk_ReservationHolder_Person
    FOREIGN KEY (PersonID) REFERENCES Person(ID)
);

CREATE TABLE if not exists Reservation
(
	ID INT NOT NULL auto_increment,
    PromoID INT NOT NULL,
    ReservationHolderID INT,
    StartDate Date NOT NULL,
    EndDate Date NOT NULL,
    Primary Key (ID),
    CONSTRAINT fk_Reservation_PromoID
    FOREIGN KEY (PromoID) REFERENCES Promo(ID),
    CONSTRAINT fk_Reservation_ReservationHolder
    FOREIGN KEY (ReservationHolderID) REFERENCES ReservationHolder(ID)
);

CREATE TABLE if not exists GuestReservation
(
	GuestID INT NOT NULL,
    ReservationID INT NOT NULL,
    CONSTRAINT fk_GuestReservation_Reservation
    FOREIGN KEY (ReservationID) REFERENCES Reservation(ID),
    CONSTRAINT fk_GuestReservation_Guest
    FOREIGN KEY (GuestID) REFERENCES Guest(ID)
);

CREATE TABLE if not exists ReservationRoom
(
	ReservationID INT NOT NULL,
    RoomID INT NOT NULL,
    CONSTRAINT fk_ReservationRoom_Reservation
    FOREIGN KEY (ReservationID) REFERENCES Reservation(ID),
    CONSTRAINT fk_ReservationRoom_Room
    FOREIGN KEY (RoomID) REFERENCES Room(ID)
);

CREATE TABLE if not exists Bill
(
	ID INT NOT NULL auto_increment,
    ReservationID INT NOT NULL,
    Total Decimal(7,2) NOT NULL,
    PRIMARY KEY (ID),
    CONSTRAINT fk_Billing_Reservation
    FOREIGN KEY (ReservationID) REFERENCES Reservation(ID)
);

CREATE TABLE if not exists RoomBillDetail
(
	ID INT NOT NULL auto_increment,
    TaxID INT NOT NULL,
    PromoID INT,
    RoomRateID INT NOT NULL,
    BillId INT NOT NULL,
    TaxAmount DECIMAL(5,2) NOT NULL,
    Price DECIMAL(6,2) NOT NULL,
    TransactionDate DATE NOT NULL,
    PRIMARY KEY (ID),
    CONSTRAINT fk_RoomBillDetail_TaxID
    FOREIGN KEY (TaxID) REFERENCES Tax(ID),
    CONSTRAINT fk_RoomBillDetail_Promo
    FOREIGN KEY (PromoID) REFERENCES Promo(ID),
    CONSTRAINT fk_RoomBillDetail_RoomRate
    FOREIGN KEY (RoomRateID) REFERENCES RoomRate(ID),
    CONSTRAINT fk_RoomBillDetail_Billing
    FOREIGN KEY (BillId) REFERENCES Bill(ID)
);

CREATE TABLE if not exists AddOnBillDetail
(
	ID INT NOT NULL auto_increment,
    TaxID INT NOT NULL,
    PromoID INT,
    AddOnRateID INT NOT NULL,
    BillId INT NOT NULL,
    TaxAmount DECIMAL(5,2) NOT NULL,
    Price DECIMAL(6,2) NOT NULL,
    TransactionDate DATE NOT NULL,
    PRIMARY KEY (ID),
    CONSTRAINT fk_AddOnBillDetail_Tax
    FOREIGN KEY (TaxID) REFERENCES Tax(ID),
    CONSTRAINT fk_AddOnBillDetail_Promo
    FOREIGN KEY (PromoID) REFERENCES Promo(ID),
    CONSTRAINT fk_AddOnBillDetail_AddOnRate
    FOREIGN KEY (AddOnRateID) REFERENCES AddOnRate(ID),
    CONSTRAINT fk_AddOnBillDetail_Billing
    FOREIGN KEY (BillId) REFERENCES Bill(ID)
);

