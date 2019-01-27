package com.sg.hotelreservations.webservice.impl;

import com.sg.TestHelper;
import com.sg.hotelreservations.config.UnitTestConfiguration;
import com.sg.hotelreservations.dto.*;
import com.sg.hotelreservations.service.serviceinterface.*;
import com.sg.hotelreservations.viewmodels.reservation.*;
import com.sg.hotelreservations.webservice.exception.InvalidDatesException;
import com.sg.hotelreservations.webservice.exception.InvalidPromoException;
import com.sg.hotelreservations.webservice.webinterface.ReservationWebService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalAnswers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UnitTestConfiguration.class})
@Rollback
@Transactional
@SpringBootTest(classes = {ReservationWebServiceImpl.class, TestHelper.class})
public class ReservationWebServiceImplIntegrationTest {

    @Autowired
    ReservationService reservationService;

    @Autowired
    RoomService roomService;

    @Autowired
    PersonService personService;

    @Autowired
    ReservationHolderService reservationHolderService;

    @Autowired
    ReservationRoomService reservationRoomService;

    @Autowired
    RoomRateService roomRateService;

    @Autowired
    TaxService taxService;

    @Autowired
    BillService billService;

    @Autowired
    RoomBillDetailService roomBillDetailService;

    @Autowired
    PromoTypeService promoTypeService;

    @Autowired
    PromoService promoService;

    @Autowired
    AddOnBillDetailService addOnBillDetailService;

    @Inject
    ReservationWebService reservationWebService;

    @Inject
    GuestService guestService;

    @Inject
    GuestReservationService guestReservationService;

    @Inject
    TestHelper testHelper;



    @Test
    public void cancelReservation() {
        Reservation reservation = createReservation();
        Bill bill = billService.retrieveByReservationId(reservation.getId());

        assert(reservationService.retrieve(reservation.getId()) != null);
        assert(billService.retrieve(bill.getId()).getId() != null);
        assert(guestReservationService.retrieveByReservationId(reservation.getId()).size() != 0);

        reservationWebService.cancelReservation(reservation.getId());
        assert(reservationService.retrieve(reservation.getId()) == null);
        assert(billService.retrieve(bill.getId()).getId() == null);
        assert(guestReservationService.retrieveByReservationId(reservation.getId()).size() == 0);
        assert(reservationRoomService.retrieveByReservationId(reservation).size() == 0);
        assert(roomBillDetailService.retrieveByBillId(bill.getId()).size() == 0);
        assert (addOnBillDetailService.retrieveByBillId(bill.getId()).size() == 0);



    }

    @Test
    public void getEditReservationViewModel() {

        Reservation reservation = createReservation();
        reservation.setPromo(testHelper.createTestPromo());

        assert(reservationService.retrieve(reservation.getId()) != null);
        assert(guestReservationService.retrieveByReservationId(reservation.getId()).size() != 0);

        EditReservationViewModel viewModel = reservationWebService.getEditReservationViewModel(reservation.getId());

        assertEquals("Joe Schmoe", viewModel.getCommandModel().getReservationHolder());
        assert(viewModel.getCommandModel().getReservationId() != null);
        assertEquals("AAADISC", viewModel.getCommandModel().getPromoCode());
        assert(viewModel.getCommandModel().getBillId() != null);
        assertEquals("me@nospam.com", viewModel.getCommandModel().getEmail());
        assertEquals("2018-08-12", viewModel.getCommandModel().getEndDate());
        assertEquals(4, viewModel.getCommandModel().getGuests().size());
        assertEquals("555-333-6666", viewModel.getCommandModel().getPhone());
        assertEquals(String.valueOf(100), viewModel.getCommandModel().getRoomNumber());
        assertEquals("2018-08-10", viewModel.getCommandModel().getStartDate());

    }

    private Reservation createReservation() {
        Reservation reservation = testHelper.createTestReservation();
        Bill bill = testHelper.createTestBill();
        bill.setReservation(reservation);
        billService.update(bill);
        for (int i=0; i<4; i++) {
            Person person = testHelper.createTestPerson();
            Guest guest = new Guest();
            guest.setPerson(person);
            guestService.create(guest);
            GuestReservation guestReservation = new GuestReservation();
            guestReservation.setReservation(reservation);
            guestReservation.setGuest(guest);
            guestReservationService.create(guestReservation);
        }

        for(int i=0; i<2; i++) {
            Room room = testHelper.createTestRoom();
            room.setRoomNumber(100 + i);
            roomService.update(room);
            ReservationRoom resRoom = new ReservationRoom();
            resRoom.setReservation(reservation);
            resRoom.setRoom(room);
            reservationRoomService.create(resRoom);
        }

        for(int i=0; i<3; i++) {
            RoomBillDetail roomBillDetail = testHelper.createTestRoomBillDetailSpecifyBill(bill.getId());
        }

        for(int i=0; i<4; i++) {
            AddOnBillDetail addOnBillDetail = testHelper.createTestAddOnBillDetailSpecifyBill(bill.getId());
        }

        return reservation;
    }

}