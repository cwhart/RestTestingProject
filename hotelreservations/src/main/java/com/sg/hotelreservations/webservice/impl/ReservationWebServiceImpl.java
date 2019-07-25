package com.sg.hotelreservations.webservice.impl;

import com.sg.hotelreservations.dto.*;
import com.sg.hotelreservations.service.serviceinterface.*;
import com.sg.hotelreservations.viewmodels.reservation.*;
import com.sg.hotelreservations.webservice.exception.InvalidDatesException;
import com.sg.hotelreservations.webservice.exception.InvalidPromoException;
import com.sg.hotelreservations.webservice.webinterface.ReservationWebService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationWebServiceImpl implements ReservationWebService {

    ReservationService reservationService;
    RoomService roomService;
    PersonService personService;
    ReservationHolderService reservationHolderService;
    ReservationRoomService reservationRoomService;
    RoomRateService roomRateService;
    TaxService taxService;
    BillService billService;
    RoomBillDetailService roomBillDetailService;
    PromoTypeService promoTypeService;
    PromoService promoService;
    GuestService guestService;
    GuestReservationService guestReservationService;
    AddOnBillDetailService addOnBillDetailService;


    @Inject
    public void ReservationWebServiceImpl (ReservationService reservationService, RoomService roomService,
                                           PersonService personService, ReservationHolderService reservationHolderService,
                                           ReservationRoomService reservationRoomService, RoomRateService roomRateService,
                                           TaxService taxService, BillService billService,
                                           RoomBillDetailService roomBillDetailService,
                                           PromoTypeService promoTypeService, PromoService promoService,
                                           GuestService guestService, GuestReservationService guestReservationService,
                                            AddOnBillDetailService addOnBillDetailService) {
        this.reservationService = reservationService;
        this.roomService = roomService;
        this.personService = personService;
        this.reservationHolderService = reservationHolderService;
        this.reservationRoomService = reservationRoomService;
        this.roomRateService = roomRateService;
        this.taxService = taxService;
        this.billService = billService;
        this.roomBillDetailService = roomBillDetailService;
        this.promoTypeService = promoTypeService;
        this.promoService = promoService;
        this.guestService = guestService;
        this.guestReservationService = guestReservationService;
        this.addOnBillDetailService = addOnBillDetailService;

    }

    @Override
    public SearchAvailableRoomsViewModel getSearchAvailableRoomsViewModel() {
        SearchAvailableRoomsViewModel viewModel = new SearchAvailableRoomsViewModel();
        viewModel.setCommandModel(new SearchAvailableRoomsCommandModel());

        return viewModel;
    }

    @Override
    public List<Reservation> retrieveAllReservations() {
        return reservationService.retrieveAll(Integer.MAX_VALUE, 0);
    }


    @Override
    public InputPersonDetailsViewModel getInputPersonDetailsViewModel() {

        InputPersonDetailsViewModel viewModel = new InputPersonDetailsViewModel();
        viewModel.setCommandModel(new InputPersonDetailsCommandModel());
        return viewModel;
    }

    @Override
    public ReservationModel getReservationModel(Long id) {
        //Instantiate stuff
        ReservationModel model = new ReservationModel();

        if (reservationService.retrieve(id) == null) return null;
        Reservation reservation = reservationService.retrieve(id);

        if(reservationRoomService.retrieveByReservationId(reservation) == null) {
            return null;
        }

        List<Person> personList = new ArrayList<>();
        List<GuestReservation> guests = guestReservationService.retrieveByReservationId(id);
        for (GuestReservation thisGuestRes : guests) {
            Person person = personService.retrieve(thisGuestRes.getGuest().getPerson().getId());
            personList.add(person);
        }

        model.setGuestDetails(personList);

        List<ReservationRoom> reservationRoomList =
                    reservationRoomService.retrieveByReservationId(reservation);

        SearchAvailableRoomsCommandModel reservationDetails = new SearchAvailableRoomsCommandModel();
        reservationDetails.setStartDate(reservation.getStartDate().toString());
        reservationDetails.setEndDate(reservation.getEndDate().toString());

        model.setReservationId(id);
        if(reservation.getPromo() != null) {
            model.setPromoCode(reservation.getPromo().getPromoType().getPromoCode());
        }
        List<ReservationRoom> rooms = new ArrayList<>();
        //for now, we're only implementing 1 room per reservation, so just set the first one.
        int roomNumber = reservationRoomList.get(0).getRoom().getRoomNumber();
        reservationDetails.setRoomNumber(roomNumber);
        model.setReservationDetails(reservationDetails);
        Bill bill = billService.retrieveByReservationId(id);
        model.setBillId(bill.getId());

//        //Return stuff
        return model;
    }

    @Override
    public SearchReservationCommandModel getSearchReservationCommandModel() {
        return new SearchReservationCommandModel();
    }

    @Override
    public ReservationModel saveCreate(ReservationModel reservationModel) throws InvalidDatesException,
            InvalidPromoException {

        Reservation reservation = new Reservation();
        List<Person> persons = new ArrayList<>();
        int numPersons = reservationModel.getGuestDetails().size();
        ReservationHolder reservationHolder = new ReservationHolder();
        ReservationRoom reservationRoom = new ReservationRoom();
        Promo promo = new Promo();
        List<Promo> promos = new ArrayList<>();
        String promoCode = reservationModel.getPromoCode();
        Bill bill = new Bill();

        int roomNum = reservationModel.getReservationDetails().getRoomNumber();
        LocalDate startDate = LocalDate.parse(reservationModel.getReservationDetails().getStartDate());
        LocalDate endDate = LocalDate.parse(reservationModel.getReservationDetails().getEndDate());
        if (reservationRoomService.isBookedForDateRange(roomNum, startDate, endDate)) {
            throw new InvalidDatesException("Sorry, this room is already booked. Room #: " + String.valueOf(roomNum));
        }

        for (Person person : reservationModel.getGuestDetails()) {
            person = personService.create(person);
            persons.add(person);
        }

        //Reservation Holder will always be the first in the list
        reservationHolder.setPerson(persons.get(0));
        reservationHolderService.create(reservationHolder);
        reservationModel.getGuestDetails().get(0).setId(reservationHolder.getPerson().getId());

        if(promoCode != "" && promoCode != null) {
            PromoType promoType = promoTypeService.retrieveByPromoCode(promoCode);
            if (promoType == null) {
                throw new InvalidPromoException("Promotion not found: " + String.valueOf(promoCode));
            } else {
                promos = promoService.retrieveByPromoTypeId(promoType.getId());//
            }

            if (promos.size() > 0) {
                promo = determineCurrentPromo(promos);

                if (promo.getId() == null) {
                    throw new InvalidPromoException("Sorry, that promotion is not active.");
                }
                reservation.setPromo(promo);
            }
        }

        reservation.setReservationHolder(reservationHolder);
        reservation.setStartDate(reservationModel.getReservationDetails().getStartDate());
        reservation.setEndDate(reservationModel.getReservationDetails().getEndDate());
        reservation = reservationService.create(reservation);
        reservationModel.setReservationId(reservation.getId());

        for (int i=0; i<numPersons; i++) {
            Guest guest = new Guest();
            guest.setPerson(persons.get(i));
            guest = guestService.create(guest);
            GuestReservation guestReservation = new GuestReservation();
            guestReservation.setGuest(guest);
            guestReservation.setReservation(reservation);
            guestReservationService.create(guestReservation);
        }

        Room room = roomService.retrieveByRoomNumber((roomNum));

        reservationRoom.setRoom(room);
        reservationRoom.setReservation(reservation);
        reservationRoomService.create(reservationRoom);

        bill.setReservation(reservation);
        bill.setTotal(BigDecimal.valueOf(0));
        billService.create(bill);
        reservationModel.setBillId(bill.getId());

        //Iterate over the days in the reservation and create a RoomBillDetail object with the day's
        //room charges
        for (LocalDate thisDate = startDate; thisDate.isBefore(endDate); thisDate = thisDate.plusDays(1)) {
            //Retrieve RoomRate for this date
            RoomRate roomRate = new RoomRate();
            if (roomRateService.retrieveCurrentRate(room.getId(), thisDate, thisDate).getDefaultFlag() == null) {
                roomRate = roomRateService.retrieveDefaultRate(room.getId());
            } else {
                roomRate = roomRateService.retrieveCurrentRate(room.getId(), thisDate, thisDate);
            }

            Tax tax = new Tax();
            List<Tax> taxList = taxService.retrieveByType("Rooms");
            if(taxList.size() != 0) {
                tax = taxList.get(0);
            } else
             {
                tax = new Tax();
                tax.setState("NH");
                tax.setRate(BigDecimal.valueOf(0));
                tax.setStartDate(LocalDate.parse("1900-01-01"));
                tax.setEndDate(null);
                tax.setType("NH Default tax");
                taxService.create(tax);
            }

            RoomBillDetail roomBillDetail = new RoomBillDetail();
            roomBillDetail.setTransactionDate(thisDate);
            roomBillDetail.setTax(tax);
            roomBillDetail.setBill(bill);
            roomBillDetail.setRoomRate(roomRate);
            roomBillDetail.setPrice((roomRate.getPrice()));
            BigDecimal taxRate = tax.getRate();
            BigDecimal taxAmount = taxRate.multiply(BigDecimal.valueOf(.01).multiply(roomBillDetail.getPrice()));
            roomBillDetail.setTaxAmount(taxAmount);
            roomBillDetail.setPromo(reservation.getPromo());
            roomBillDetailService.create(roomBillDetail);
        }

        return reservationModel;

    }

    private Person translatePersonFromCommandModel(InputPersonDetailsCommandModel commandModel) {
        Person person = new Person();
        person.setFirstName(commandModel.getFirstName());
        person.setLastName(commandModel.getLastName());
        if(commandModel.getDateOfBirth() != null && !commandModel.getDateOfBirth().equals("")) {
            person.setDateOfBirth((commandModel.getDateOfBirth()));
        }
        if(commandModel.getPhone() != null && !commandModel.getPhone().equals("")) {
            person.setPhoneNo(commandModel.getPhone());
        }
            if(commandModel.getEmail() != null && !commandModel.getEmail().equals("")) {
                person.setEmail(commandModel.getEmail());
            }

        return personService.create(person);
    }

    private Promo determineCurrentPromo (List<Promo> promos) {

        Promo currentPromo = new Promo();

        for (Promo thisPromo : promos) {
            if (thisPromo.getStartDate().isBefore(LocalDate.now())
                    && thisPromo.getEndDate().isAfter(LocalDate.now())) {
                currentPromo = thisPromo;
            }
        }

        return currentPromo;
    }

    @Override
    public void cancelReservation(Long reservationId) {

        Reservation reservation = reservationService.retrieve(reservationId);
        Bill bill = billService.retrieveByReservationId(reservationId);
        List<ReservationRoom> reservationRooms = reservationRoomService.retrieveByReservationId(reservation);
        if (reservationRooms.size() > 0) {
            for(ReservationRoom currentResRoom : reservationRooms) {
                reservationRoomService.delete(currentResRoom);
            }
        }

        List<RoomBillDetail> roomBillDetails = roomBillDetailService.retrieveByBillId(bill.getId());
        if (roomBillDetails.size() > 0) {
            for(RoomBillDetail currentRoomBillDetail : roomBillDetails) {
                roomBillDetailService.delete(currentRoomBillDetail);
            }
        }

        List<AddOnBillDetail> addOnBillDetails = addOnBillDetailService.retrieveByBillId(bill.getId());
        if (addOnBillDetails.size() > 0) {
            for(AddOnBillDetail currentAddOnBillDetail : addOnBillDetails) {
                addOnBillDetailService.delete(currentAddOnBillDetail);
            }
        }

        List<GuestReservation> guestReservations1 = guestReservationService.retrieveByReservationId(reservationId);
        if (guestReservations1.size() > 0) {
            for (GuestReservation currentGuestRes : guestReservations1) {
                guestReservationService.delete(currentGuestRes);
            }
        }

        billService.delete(bill);
        reservationService.delete(reservation);

    }

    @Override
    public EditReservationViewModel getEditReservationViewModel(Long id) {

        EditReservationViewModel viewModel = new EditReservationViewModel();
        Reservation reservation = reservationService.retrieve(id);

        EditReservationCommandModel commandModel = new EditReservationCommandModel();

        commandModel.setBillId(billService.retrieveByReservationId(id).getId());
        commandModel.setEmail(reservation.getReservationHolder().getPerson().getEmail());
        commandModel.setEndDate(reservation.getEndDate().toString());

        List<GuestReservation> guestReservations = guestReservationService.retrieveByReservationId(id);
        List<String> guests = new ArrayList<>();
        for (GuestReservation currentGuestRes : guestReservations) {
            Person person = personService.retrieve(currentGuestRes.getGuest().getPerson().getId());
            String firstName = person.getFirstName();
            String lastName = person.getLastName();
            guests.add(firstName + " " + lastName);
        }
        commandModel.setReservationHolder(guests.get(0));

        List<ReservationRoom> reservationRoomList = reservationRoomService.retrieveByReservationId(reservationService.retrieve(id));

        commandModel.setGuests(guests);
        commandModel.setPhone(reservation.getReservationHolder().getPerson().getPhoneNo());
        if(reservation.getPromo() != null) {
            commandModel.setPromoCode(reservation.getPromo().getPromoType().getPromoCode());
        }
        commandModel.setReservationId(id);
        commandModel.setRoomNumber(String.valueOf(reservationRoomList.get(0).getRoom().getRoomNumber()));
        commandModel.setStartDate(reservation.getStartDate().toString());

//
        viewModel.setCommandModel(commandModel);
        return viewModel;
    }

    @Override
    public ReservationModel saveEditReservation(ReservationModel reservationModel) throws InvalidPromoException {

        //User should not be able to edit rooms or dates - in that case they should cancel and rebook.
        //User should be able to edit:
        //-Reservation Holder
        //-Guests (throw an error if room capacity is exceeded.)
        //-AddOns
        //TODO: implement ability to add rooms to a reservation. (Can add, but can't remove.)


        Reservation reservation = reservationService.retrieve(reservationModel.getReservationId());
        //TODO: refactor so there aren't multiple return statements.
        if(reservation == null) return null;

        //Need to take reservationholder's name and find the person/guest associated.
        //String[] names = reservationModel.getGuestDetails().split("\\s+");
        String firstName = reservationModel.getGuestDetails().get(0).getFirstName();
        String lastName = reservationModel.getGuestDetails().get(0).getLastName();
        List<Person> persons = personService.retrieveByName(firstName, lastName);
        Person person = persons.get(0);
        ReservationHolder reservationHolder = new ReservationHolder();
        //Now see if there's an existing reservationHolder for this person. If not, create it.
        //Then add the reservationHolder to the reservation.

        if(reservationHolderService.retrieveByPersonId(person.getId()) != null) {
            reservationHolder = reservationHolderService.retrieveByPersonId(person.getId());
        } else {
            reservationHolder.setPerson(person);
            reservationHolder = reservationHolderService.create(reservationHolder);
        }

        String promoCode = reservationModel.getPromoCode();
        List<Promo> promos = new ArrayList<>();

        if(promoCode != "") {
            PromoType promoType = promoTypeService.retrieveByPromoCode(promoCode);
            if (promoType == null) {
                throw new InvalidPromoException("Promotion not found: " + String.valueOf(promoCode));
            } else {
                promos = promoService.retrieveByPromoTypeId(promoType.getId());//
            }

            if (promos.size() > 0) {
                Promo promo = determineCurrentPromo(promos);

                if (promo.getId() == null) {
                    throw new InvalidPromoException("Sorry, that promotion is not active.");
                }
                reservation.setPromo(promo);
            }
        }
        reservation.setReservationHolder(reservationHolder);

        reservationService.update(reservation);
        return reservationModel;


    }
}
