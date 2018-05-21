DROP DATABASE if exists HotelReservation;

CREATE DATABASE HotelReservation;

USE HotelReservation;

CREATE TABLE if not exists Room
(
   RoomID INT NOT NULL auto_increment,
   FloorNumber INT NOT NULL,
   RoomNumber INT NOT NULL,
   Occupancy INT NOT NULL,
   RoomType VARCHAR(20) NOT NULL,
   PRIMARY KEY (RoomID)
);

CREATE TABLE if not exists RoomRate
(
   RoomRateID INT NOT NULL auto_increment,
   RoomID INT NOT NULL,
   StartDate DATE NOT NULL,
   EndDate DATE NULL,
   Price DECIMAL(6,2) NOT NULL,
   PRIMARY KEY (RoomRateID),
   CONSTRAINT fk_RoomRate_Room
   FOREIGN KEY(RoomID) REFERENCES Room(RoomID)
);

CREATE TABLE if not exists Amenity
(
	AmenityID INT NOT NULL auto_increment,
    Type VARCHAR(30) NOT NULL,
    PRIMARY KEY (AmenityID)
);

CREATE TABLE if not exists RoomAmenity
(
	AmenityID INT NOT NULL,
    RoomID INT NOT NULL,
    CONSTRAINT fk_RoomAmenity_Amenity
    FOREIGN KEY (AmenityID) REFERENCES Amenity(AmenityID),
    CONSTRAINT fk_RoomAmenity_Room
    FOREIGN KEY (RoomID) REFERENCES Room(RoomID)
);

CREATE TABLE if not exists PromoRate
(
	PromoRateID INT NOT NULL auto_increment,
    Type VARCHAR(1) NOT NULL,
    Rate INT NOT NULL,
    PRIMARY KEY (PromoRateID)
);

CREATE TABLE if not exists PromoType
(
	PromoTypeID INT NOT NULL auto_increment,
    PromoRateID INT NOT NULL,
    PromoCode VARCHAR(10),
    Description VARCHAR(50),
    PRIMARY KEY (PromoTypeID),
    CONSTRAINT fk_PromoType_PromoRate
    FOREIGN KEY (PromoRateID) REFERENCES PromoRate(PromoRateID)
);

CREATE TABLE if not exists Promo
(
	PromoID INT NOT NULL auto_increment,
    PromoTypeID INT NOT NULL,
    StartDate Date NOT NULL,
    EndDate Date NOT NULL,
    PRIMARY KEY (PromoID),
    CONSTRAINT fk_Promo_PromoType
    FOREIGN KEY (PromoTypeID) REFERENCES PromoType(PromoTypeID)
);

CREATE TABLE if not exists AddOn
(
	AddOnID INT NOT NULL auto_increment,
    Type VARCHAR(30) NOT NULL,
    PRIMARY KEY (AddOnID)
);

CREATE TABLE if not exists AddOnRate
(
	AddOnRateID INT NOT NULL auto_increment,
    AddOnID INT NOT NULL,
    StartDate Date NOT NULL,
    EndDate Date NULL,
    Price Decimal(6,2) NOT NULL,
    Primary Key (AddOnRateID),
    CONSTRAINT fk_AddOnRate_AddOn
    FOREIGN KEY (AddOnID) REFERENCES AddOn(AddOnID)
);

CREATE TABLE if not exists Tax
(
	TaxID INT NOT NULL auto_increment,
    Type VARCHAR(20),
    StartDate Date NOT NULL,
    EndDate Date NULL,
    Rate Decimal(4,2) NOT NULL,
    Primary Key (TaxID)
);

CREATE TABLE if not exists Person
(
	PersonID INT NOT NULL auto_increment,
    FirstName VARCHAR(30) NOT NULL,
    LastName VARCHAR(30) NOT NULL,
    DateOfBirth Date NOT NULL,
    Phone VARCHAR(12) NOT NULL,
    Email VARCHAR(50) NOT NULL,
    Primary Key (PersonID)
);

CREATE TABLE if not exists Guest
(
	GuestID INT NOT NULL auto_increment,
    PersonID INT NOT NULL,
    Primary Key (GuestID),
    CONSTRAINT fk_Guest_Person
    FOREIGN KEY (PersonID) REFERENCES Person(PersonID)
);

CREATE TABLE if not exists ReservationHolder
(
	ReservationHolderID INT NOT NULL auto_increment,
    PersonID INT NOT NULL,
    Primary Key (ReservationHolderID),
    CONSTRAINT fk_ReservationHolder_Person
    FOREIGN KEY (PersonID) REFERENCES Person(PersonID)
);

CREATE TABLE if not exists Reservation
(
	ReservationID INT NOT NULL auto_increment,
    PromoID INT,
    ReservationHolderID INT NOT NULL,
    StartDate Date NOT NULL,
    EndDate Date NOT NULL,
    Primary Key (ReservationID),
    CONSTRAINT fk_Reservation_PromoID
    FOREIGN KEY (PromoID) REFERENCES Promo(PromoID),
    CONSTRAINT fk_Reservation_ReservationHolder
    FOREIGN KEY (ReservationHolderID) REFERENCES ReservationHolder(ReservationHolderID)
);

CREATE TABLE if not exists GuestReservation
(
	GuestID INT NOT NULL,
    ReservationID INT NOT NULL,
    CONSTRAINT fk_GuestReservation_Reservation
    FOREIGN KEY (ReservationID) REFERENCES Reservation(ReservationID),
    CONSTRAINT fk_GuestReservation_Guest
    FOREIGN KEY (GuestID) REFERENCES Guest(GuestID)
);

CREATE TABLE if not exists ReservationRoom
(
	ReservationID INT NOT NULL,
    RoomID INT NOT NULL,
    CONSTRAINT fk_ReservationRoom_Reservation
    FOREIGN KEY (ReservationID) REFERENCES Reservation(ReservationID),
    CONSTRAINT fk_ReservationRoom_Room
    FOREIGN KEY (RoomID) REFERENCES Room(RoomID)
);

CREATE TABLE if not exists Billing
(
	BillingID INT NOT NULL auto_increment,
    ReservationID INT NOT NULL,
    Total Decimal(7,2) NOT NULL,
    PRIMARY KEY (BillingID),
    CONSTRAINT fk_Billing_Reservation
    FOREIGN KEY (ReservationID) REFERENCES Reservation(ReservationID)
);

CREATE TABLE if not exists RoomBillDetail
(
	RoomBillDetailID INT NOT NULL auto_increment,
    TaxID INT NOT NULL,
    PromoID INT,
    RoomRateID INT NOT NULL,
    BillingID INT NOT NULL,
    TaxAmount DECIMAL(5,2) NOT NULL,
    Price DECIMAL(6,2) NOT NULL,
    TransactionDate DATE NOT NULL,
    PRIMARY KEY (RoomBillDetailID),
    CONSTRAINT fk_RoomBillDetail_TaxID
    FOREIGN KEY (TaxID) REFERENCES Tax(TaxID),
    CONSTRAINT fk_RoomBillDetail_Promo
    FOREIGN KEY (PromoID) REFERENCES Promo(PromoID),
    CONSTRAINT fk_RoomBillDetail_RoomRate
    FOREIGN KEY (RoomRateID) REFERENCES RoomRate(RoomRateID),
    CONSTRAINT fk_RoomBillDetail_Billing
    FOREIGN KEY (BillingID) REFERENCES Billing(BillingID)
);

CREATE TABLE if not exists AddOnBillDetail
(
	AddOnBillDetailID INT NOT NULL auto_increment,
    TaxID INT NOT NULL,
    PromoID INT,
    AddOnRateID INT NOT NULL,
    BillingID INT NOT NULL,
    TaxAmount DECIMAL(5,2) NOT NULL,
    Price DECIMAL(6,2) NOT NULL,
    TransactionDate DATE NOT NULL,
    PRIMARY KEY (AddOnBillDetailID),
    CONSTRAINT fk_AddOnBillDetail_Tax
    FOREIGN KEY (TaxID) REFERENCES Tax(TaxID),
    CONSTRAINT fk_AddOnBillDetail_Promo
    FOREIGN KEY (PromoID) REFERENCES Promo(PromoID),
    CONSTRAINT fk_AddOnBillDetail_AddOnRate
    FOREIGN KEY (AddOnRateID) REFERENCES AddOnRate(AddOnRateID),
    CONSTRAINT fk_AddOnBillDetail_Billing
    FOREIGN KEY (BillingID) REFERENCES Billing(BillingID)
);