package com.sg;

import com.sg.hotelreservations.dao.daoInterface.*;
import com.sg.hotelreservations.dto.*;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestHelper {

    @Inject
    AddOnDAO addOnDao;

    @Inject
    RoomDAO roomDao;

    @Inject
    RoomRateDAO roomRateDAO;

    @Inject
    AmenityDAO amenityDAO;

    @Inject
    RoomAmenityDAO roomAmenityDAO;

    @Inject
    PromoRateDAO promoRateDAO;

    @Inject
    PromoTypeDAO promoTypeDAO;

    @Inject
    PromoDAO promoDAO;

    @Inject
    AddOnRateDAO addOnRateDAO;

    @Inject
    TaxDAO taxDAO;

    @Inject
    PersonDAO personDAO;

    @Inject
    GuestDAO guestDAO;

    @Inject
    ReservationHolderDAO reservationHolderDAO;

    @Inject
    ReservationDAO reservationDAO;

    @Inject
    GuestReservationDAO guestReservationDAO;

    @Inject
    ReservationRoomDAO reservationRoomDAO;

    @Inject
    BillDAO billDAO;

    @Inject
    RoomBillDetailDAO roomBillDetailDAO;

    @Inject
    AddOnBillDetailDAO addOnBillDetailDAO;

    public AddOn createTestAddOn() {
        AddOn addOn = new AddOn();
        addOn.setType("Pedicure");
        addOnDao.create(addOn);
        return addOn;
    }

    public List<AddOn> createMultipleAddOns(int numToCreate) {
        List<AddOn> returnList = new ArrayList<>();

        for (int i=0; i<numToCreate; i++) {
            returnList.add(createTestAddOn());
        }
        return returnList;
    }

    public Room createTestRoom() {
        Room room = new Room();
        room.setType("King");
        room.setOccupancy(2);
        room.setRoomNumber(201);
        room.setFloorNumber(2);
        roomDao.create(room);
        return room;

    }

    public List<Room> createMultipleRooms(int numToCreate) {
        List<Room> returnList = new ArrayList<>();

        for (int i=0; i<numToCreate; i++) {
            returnList.add(createTestRoom());
        }
        return returnList;
    }

    public RoomRate createTestRoomRate() {
        RoomRate roomRate = new RoomRate();
        roomRate.setRoom(createTestRoom());
        roomRate.setStartDate(LocalDate.parse("2018-07-01"));
        roomRate.setEndDate(LocalDate.parse("2018-09-05"));
        roomRate.setPrice(new BigDecimal("200"));
        roomRateDAO.create(roomRate);
        return roomRate;

    }

    public List<RoomRate> createMultipleRoomRates(int numToCreate) {
        List<RoomRate> returnList = new ArrayList<>();

        for (int i=0; i<numToCreate; i++) {
            returnList.add(createTestRoomRate());
        }
        return returnList;
    }

    public Amenity createTestAmenity() {
        Amenity amenity = new Amenity();
        amenity.setType("Hot Tub");
        amenityDAO.create(amenity);
        return amenity;

    }

    public List<Amenity> createMultipleAmenities(int numToCreate) {
        List<Amenity> returnList = new ArrayList<>();

        for (int i=0; i<numToCreate; i++) {
            returnList.add(createTestAmenity());
        }
        return returnList;
    }

    public RoomAmenity createTestRoomAmenity() {
        Amenity amenity = createTestAmenity();
        Room room = createTestRoom();
        RoomAmenity roomAmenity = new RoomAmenity();
        roomAmenity.setRoom(room);
        roomAmenity.setAmenity(amenity);
        roomAmenityDAO.create(roomAmenity);
        return roomAmenity;

    }

    public RoomAmenity createTestRoomAmenity(Long roomid) {
        Amenity amenity = createTestAmenity();
        //Room room = createTestRoom();
        Room room = new Room();
        room.setId(roomid);
        RoomAmenity roomAmenity = new RoomAmenity();
        roomAmenity.setRoom(room);
        roomAmenity.setAmenity(amenity);
        roomAmenityDAO.create(roomAmenity);
        return roomAmenity;

    }

    public RoomAmenity createTestRoomAmenitySpecifyAmenity(Long amenityid) {
        //Amenity amenity = createTestAmenity();
        Room room = createTestRoom();
        Amenity amenity = new Amenity();
        amenity.setId(amenityid);
        RoomAmenity roomAmenity = new RoomAmenity();
        roomAmenity.setRoom(room);
        roomAmenity.setAmenity(amenity);
        roomAmenityDAO.create(roomAmenity);
        return roomAmenity;

    }

    public List<RoomAmenity> createMultipleRoomAmenities(int numToCreate) {
        List<RoomAmenity> returnList = new ArrayList<>();

        for (int i=0; i<numToCreate; i++) {
            returnList.add(createTestRoomAmenity());
        }
        return returnList;
    }

    public PromoRate createTestPromoRate() {
        PromoRate promoRate = new PromoRate();
        promoRate.setRate(new BigDecimal("15"));
        promoRate.setType("%");
        promoRateDAO.create(promoRate);
        return promoRate;

    }

    public List<PromoRate> createMultiplePromoRates(int numToCreate) {
        List<PromoRate> returnList = new ArrayList<>();

        for (int i=0; i<numToCreate; i++) {
            returnList.add(createTestPromoRate());
        }
        return returnList;
    }

    public PromoType createTestPromoType() {
        PromoType promoType = new PromoType();
        promoType.setPromoRate(createTestPromoRate());
        promoType.setPromoCode("AAADISC");
        promoType.setDescription("Discount for AAA members");

        promoTypeDAO.create(promoType);
        return promoType;

    }

    public List<PromoType> createMultiplePromoTypes(int numToCreate) {
        List<PromoType> returnList = new ArrayList<>();

        for (int i=0; i<numToCreate; i++) {
            returnList.add(createTestPromoType());
        }
        return returnList;
    }

    public Promo createTestPromo() {
        Promo promo = new Promo();
        promo.setPromoType(createTestPromoType());
        promo.setStartDate(LocalDate.parse("2018-07-01"));
        promo.setEndDate(LocalDate.parse("2018-08-15"));

        promoDAO.create(promo);
        return promo;

    }

    public List<Promo> createMultiplePromos(int numToCreate) {
        List<Promo> returnList = new ArrayList<>();

        for (int i=0; i<numToCreate; i++) {
            returnList.add(createTestPromo());
        }
        return returnList;
    }

    public AddOnRate createTestAddOnRate() {
        AddOnRate addOnRate = new AddOnRate();
        addOnRate.setAddOn(createTestAddOn());
        addOnRate.setStartDate(LocalDate.parse("2018-07-01"));
        addOnRate.setEndDate(LocalDate.parse("2018-09-05"));
        addOnRate.setPrice(new BigDecimal("25"));
        addOnRateDAO.create(addOnRate);
        return addOnRate;

    }

    public List<AddOnRate> createMultipleAddOnRates(int numToCreate) {
        List<AddOnRate> returnList = new ArrayList<>();

        for (int i=0; i<numToCreate; i++) {
            returnList.add(createTestAddOnRate());
        }
        return returnList;
    }

    public Tax createTestTax() {
        Tax tax = new Tax();
        tax.setType("NH Meals Tax");
        tax.setStartDate(LocalDate.parse("2018-01-01"));
        tax.setEndDate(LocalDate.parse("2018-12-31"));
        tax.setRate(new BigDecimal("5"));
        taxDAO.create(tax);
        return tax;

    }

    public List<Tax> createMultipleTaxes(int numToCreate) {
        List<Tax> returnList = new ArrayList<>();

        for (int i=0; i<numToCreate; i++) {
            returnList.add(createTestTax());
        }
        return returnList;
    }

    public Person createTestPerson() {
        Person person = new Person();
        person.setFirstName("Joe");
        person.setLastName("Schmoe");
        person.setDateOfBirth(LocalDate.parse("1970-10-04"));
        person.setPhoneNo("555-333-6666");
        person.setEmail("me@nospam.com");
        personDAO.create(person);
        return person;

    }

    public List<Person> createMultiplePersons(int numToCreate) {
        List<Person> returnList = new ArrayList<>();

        for (int i=0; i<numToCreate; i++) {
            returnList.add(createTestPerson());
        }
        return returnList;
    }

    public Guest createTestGuest() {
        Guest guest = new Guest();
        guest.setPerson(createTestPerson());
        guestDAO.create(guest);
        return guest;

    }

    public List<Guest> createMultipleGuests(int numToCreate) {
        List<Guest> returnList = new ArrayList<>();

        for (int i=0; i<numToCreate; i++) {
            returnList.add(createTestGuest());
        }
        return returnList;
    }

    public ReservationHolder createTestReservationHolder() {
        ReservationHolder reservationHolder = new ReservationHolder();
        reservationHolder.setPerson(createTestPerson());
        reservationHolderDAO.create(reservationHolder);
        return reservationHolder;

    }

    public List<ReservationHolder> createMultipleReservationHolders(int numToCreate) {
        List<ReservationHolder> returnList = new ArrayList<>();

        for (int i=0; i<numToCreate; i++) {
            returnList.add(createTestReservationHolder());
        }
        return returnList;
    }

    public Reservation createTestReservation() {
        Reservation reservation = new Reservation();
        reservation.setPromo(createTestPromo());
        reservation.setReservationHolder(createTestReservationHolder());
        reservation.setStartDate(LocalDate.parse("2018-08-10"));
        reservation.setEndDate(LocalDate.parse("2018-08-12"));
        reservationDAO.create(reservation);
        return reservation;

    }

    public Reservation createTestReservationSpecifyDates(LocalDate start, LocalDate end) {
        Reservation reservation = new Reservation();
        reservation.setPromo(createTestPromo());
        reservation.setReservationHolder(createTestReservationHolder());
        reservation.setStartDate(start);
        reservation.setEndDate(end);
        reservationDAO.create(reservation);
        return reservation;
    }

    public List<Reservation> createMultipleReservations(int numToCreate) {
        List<Reservation> returnList = new ArrayList<>();

        for (int i=0; i<numToCreate; i++) {
            returnList.add(createTestReservation());
        }
        return returnList;
    }


    public ReservationRoom createTestReservationRoom() {
        Room room = createTestRoom();
        Reservation reservation = createTestReservation();
        ReservationRoom reservationRoom = new ReservationRoom();
        reservationRoom.setRoom(room);
        reservationRoom.setReservation(reservation);
        reservationRoomDAO.create(reservationRoom);
        return reservationRoom;

    }

    public ReservationRoom createTestReservationRoom(Long roomId) {
        Reservation reservation = createTestReservation();
        //Room room = createTestRoom();
        Room room = new Room();
        room.setId(roomId);
        ReservationRoom reservationRoom = new ReservationRoom();
        reservationRoom.setRoom(room);
        reservationRoom.setReservation(reservation);
        reservationRoomDAO.create(reservationRoom);
        return reservationRoom;

    }

    public ReservationRoom createTestReservationRoomSpecifyReservation(Long reservationId) {
        //Amenity amenity = createTestAmenity();
        Room room = createTestRoom();
        Reservation reservation = new Reservation();
        reservation.setId(reservationId);
        ReservationRoom guestReservation = new ReservationRoom();
        guestReservation.setRoom(room);
        guestReservation.setReservation(reservation);
        reservationRoomDAO.create(guestReservation);
        return guestReservation;

    }

    public ReservationRoom createTestResRoomSpecifyResAndRoom(Reservation res, Room room) {
        ReservationRoom resRoom = new ReservationRoom();
        resRoom.setRoom(room);
        resRoom.setReservation(res);
        reservationRoomDAO.create(resRoom);
        return resRoom;
    }

    public List<ReservationRoom> createMultipleReservationRooms(int numToCreate) {
        List<ReservationRoom> returnList = new ArrayList<>();

        for (int i=0; i<numToCreate; i++) {
            returnList.add(createTestReservationRoom());
        }
        return returnList;
    }

    public GuestReservation createTestGuestReservation() {
        Guest guest = createTestGuest();
        Reservation reservation = createTestReservation();
        GuestReservation guestReservation = new GuestReservation();
        guestReservation.setGuest(guest);
        guestReservation.setReservation(reservation);
        guestReservationDAO.create(guestReservation);
        return guestReservation;

    }

    public GuestReservation createTestGuestReservation(Long guestId) {
        Reservation reservation = createTestReservation();
        //Room room = createTestRoom();
        Guest guest = new Guest();
        guest.setId(guestId);
        GuestReservation guestReservation = new GuestReservation();
        guestReservation.setGuest(guest);
        guestReservation.setReservation(reservation);
        guestReservationDAO.create(guestReservation);
        return guestReservation;

    }

    public GuestReservation createTestGuestReservationSpecifyReservation(Long reservationId) {
        //Amenity amenity = createTestAmenity();
        Guest guest = createTestGuest();
        Reservation reservation = new Reservation();
        reservation.setId(reservationId);
        GuestReservation guestReservation = new GuestReservation();
        guestReservation.setGuest(guest);
        guestReservation.setReservation(reservation);
        guestReservationDAO.create(guestReservation);
        return guestReservation;

    }

    public List<GuestReservation> createMultipleGuestReservations(int numToCreate) {
        List<GuestReservation> returnList = new ArrayList<>();

        for (int i=0; i<numToCreate; i++) {
            returnList.add(createTestGuestReservation());
        }
        return returnList;
    }

    public Bill createTestBill() {
        Bill bill = new Bill();
        bill.setReservation(createTestReservation());
        bill.setTotal(new BigDecimal("893.71"));
        billDAO.create(bill);
        return bill;

    }

    public List<Bill> createMultipleBills(int numToCreate) {
        List<Bill> returnList = new ArrayList<>();

        for (int i=0; i<numToCreate; i++) {
            returnList.add(createTestBill());
        }
        return returnList;
    }

    public RoomBillDetail createTestRoomBillDetail() {
        RoomBillDetail roomBillDetail = new RoomBillDetail();
        roomBillDetail.setTax(createTestTax());
        roomBillDetail.setPromo(createTestPromo());
        roomBillDetail.setRoomRate(createTestRoomRate());
        roomBillDetail.setBill(createTestBill());
        roomBillDetail.setTaxAmount(new BigDecimal("77.24"));
        roomBillDetail.setPrice(new BigDecimal("921.11"));
        roomBillDetail.setTransactionDate(LocalDate.parse("2018-08-15"));
        roomBillDetailDAO.create(roomBillDetail);
        return roomBillDetail;

    }

    public List<RoomBillDetail> createMultipleRoomBillDetails(int numToCreate) {
        List<RoomBillDetail> returnList = new ArrayList<>();

        for (int i=0; i<numToCreate; i++) {
            returnList.add(createTestRoomBillDetail());
        }
        return returnList;
    }

    public AddOnBillDetail createTestAddOnBillDetail() {
        AddOnBillDetail addOnBillDetail = new AddOnBillDetail();
        addOnBillDetail.setTax(createTestTax());
        addOnBillDetail.setPromo(createTestPromo());
        addOnBillDetail.setAddOnRate(createTestAddOnRate());
        addOnBillDetail.setBill(createTestBill());
        addOnBillDetail.setTaxAmount(new BigDecimal("77.24"));
        addOnBillDetail.setPrice(new BigDecimal("921.11"));
        addOnBillDetail.setTransactionDate(LocalDate.parse("2018-08-15"));
        addOnBillDetailDAO.create(addOnBillDetail);
        return addOnBillDetail;

    }

    public List<AddOnBillDetail> createMultipleAddOnBillDetails(int numToCreate) {
        List<AddOnBillDetail> returnList = new ArrayList<>();

        for (int i=0; i<numToCreate; i++) {
            returnList.add(createTestAddOnBillDetail());
        }
        return returnList;
    }

}
