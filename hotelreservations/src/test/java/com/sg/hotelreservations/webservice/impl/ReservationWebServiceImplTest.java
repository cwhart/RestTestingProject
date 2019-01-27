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
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

import javax.inject.Inject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UnitTestConfiguration.class})
@Rollback
@Transactional
@SpringBootTest(classes = {ReservationWebServiceImpl.class, TestHelper.class})
public class ReservationWebServiceImplTest {

    @MockBean
    ReservationService reservationService;

    @MockBean
    RoomService roomService;

    @MockBean
    PersonService personService;

    @MockBean
    ReservationHolderService reservationHolderService;

    @MockBean
    ReservationRoomService reservationRoomService;

    @MockBean
    RoomRateService roomRateService;

    @MockBean
    TaxService taxService;

    @MockBean
    BillService billService;

    @MockBean
    RoomBillDetailService roomBillDetailService;

    @MockBean
    PromoTypeService promoTypeService;

    @MockBean
    PromoService promoService;

    @MockBean
    AddOnBillDetailService addOnBillDetailService;

    @MockBean
    GuestService guestService;

    @MockBean
    GuestReservationService guestReservationService;

    @Autowired
    ReservationWebService reservationWebService;

    @Inject
    TestHelper testHelper;


    @Test
    public void getSearchAvailableRoomsViewModel() {

        //Act
        SearchAvailableRoomsViewModel viewModel = reservationWebService.getSearchAvailableRoomsViewModel();

        //Assert
        assert (viewModel.getCommandModel().getStartDate() == null);
        assert (viewModel.getCommandModel().getEndDate() == null);
        assert (viewModel.getCommandModel().getRoomNum() == 0);
        assert (viewModel.getCommandModel().getNumInParty() == 0);

    }

    @Test
    public void getInputPersonDetailsViewModel() {

        //Arrange
        //Act
        InputPersonDetailsViewModel viewModel = reservationWebService.getInputPersonDetailsViewModel();

        //Assert
        assert (viewModel.getMessage() == null);
        assert (viewModel.getCommandModel().getDateOfBirth() == null);
        assert (viewModel.getCommandModel().getEmail() == null);
        assert (viewModel.getCommandModel().getFirstName() == null);
        assert (viewModel.getCommandModel().getLastName() == null);
        assert (viewModel.getCommandModel().getPhone() == null);

    }

    @Test
    public void getReservationProfileViewModel() {

        //Arrange
        Bill bill = testHelper.createTestBill();
        Reservation reservation = bill.getReservation();

        when(reservationService.retrieve(anyLong())).thenReturn(reservation);
        when(reservationHolderService.retrieve(anyLong())).thenReturn(testHelper.createTestReservationHolder());
        when(personService.retrieve(anyLong())).thenReturn(testHelper.createTestPerson());
        when(reservationRoomService.retrieveByReservationId(any()))
                .thenReturn(testHelper.createMultipleReservationRooms(1));
        when(billService.retrieveByReservationId(anyLong())).thenReturn(bill);
        ReservationRoom resRoom1 = testHelper.createTestReservationRoomSpecifyReservation(reservation.getId());


        //Act
        ProfileReservationViewModel viewModel = reservationWebService.getReservationProfileViewModel(reservation.getId());

        //Assert
        assert (viewModel.getEmail().equals("me@nospam.com"));
        assert(viewModel.getStartDate().equals("2018-08-10"));
        assert(viewModel.getEndDate().equals("2018-08-12"));
        assert(viewModel.getRoomNumber().equals("201"));
        assert(viewModel.getFirstName().equals("Joe"));
        assert(viewModel.getLastName().equals("Schmoe"));
        assert(viewModel.getPhone().equals("555-333-6666"));

    }

    @Test
    public void saveCreateCurrentRate() throws InvalidDatesException, InvalidPromoException {

        SaveReservationCommandModel commandModel = new SaveReservationCommandModel();
        //commandModel.setPersonCommandModel(testHelper.createTestInputPersonDetailsCommandModel());
        List<InputPersonDetailsViewModel> personDetailsViewModels = new ArrayList<>();

        for (int i=0; i<5; i++) {
            InputPersonDetailsViewModel personDetailsViewModel = testHelper.createTestInputPersonDetailsViewModel();
            personDetailsViewModels.add(personDetailsViewModel);
        }
        commandModel.setPersonDetailsViewModels(personDetailsViewModels);
        commandModel.setRoomsViewModel(testHelper.createTestRoomViewModel(201));

        when(roomRateService.retrieveCurrentRate(anyLong(), anyObject(), anyObject())).thenReturn(testHelper.createTestRoomRate());
        when(roomService.retrieveByRoomNumber(anyInt())).thenReturn(testHelper.createTestRoom());
        when(personService.create(any())).then(AdditionalAnswers.returnsFirstArg());
        when(reservationHolderService.create(any())).then(AdditionalAnswers.returnsFirstArg());
        when(reservationService.create(any())).then(AdditionalAnswers.returnsFirstArg());
        when(reservationRoomService.create(any())).then(AdditionalAnswers.returnsFirstArg());
        when(guestService.create(any())).then(AdditionalAnswers.returnsFirstArg());
        when(guestReservationService.create(any())).then(AdditionalAnswers.returnsFirstArg());
        when(promoTypeService.retrieveByPromoCode(anyString())).thenReturn(testHelper.createTestPromoType());
        when(promoService.retrieveByPromoTypeId(anyLong())).thenReturn(testHelper.createMultiplePromos(2));

        //Act
        Reservation reservation = reservationWebService.saveCreate(commandModel);

        //Assert
        //assert (reservation.getId() != null);
        //assert (reservation.getPromo() != null);
        assert (reservation.getStartDate().equals(LocalDate.parse("2018-11-01")));
        assert (reservation.getEndDate().equals(LocalDate.parse("2018-11-10")));
        assert (reservation.getReservationHolder().getPerson().getFirstName().equals("Joe"));
        assert (reservation.getReservationHolder().getPerson().getLastName().equals("Schmoe"));

    }

    @Test
    public void saveCreateDefaultRate() throws InvalidDatesException, InvalidPromoException {

        SaveReservationCommandModel commandModel = new SaveReservationCommandModel();
        List<InputPersonDetailsViewModel> personDetailsViewModels = new ArrayList<>();

        for (int i=0; i<5; i++) {
            InputPersonDetailsViewModel personDetailsViewModel = testHelper.createTestInputPersonDetailsViewModel();
            personDetailsViewModels.add(personDetailsViewModel);
        }
        commandModel.setPersonDetailsViewModels(personDetailsViewModels);

        SearchAvailableRoomsCommandModel searchAvailableRoomsCommandModel = testHelper.createTestRoomCommandModel(201);
        searchAvailableRoomsCommandModel.setStartDate("2018-06-01");
        searchAvailableRoomsCommandModel.setEndDate("2018-06-14");
        SearchAvailableRoomsViewModel roomsViewModel = new SearchAvailableRoomsViewModel();
        roomsViewModel.setCommandModel(searchAvailableRoomsCommandModel);
        commandModel.setRoomsViewModel(roomsViewModel);

        when(roomRateService.retrieveCurrentRate(anyLong(), anyObject(), anyObject())).thenReturn(testHelper.createTestRoomRate());
        when(roomService.retrieveByRoomNumber(anyInt())).thenReturn(testHelper.createTestRoom());
        when(personService.create(any())).then(AdditionalAnswers.returnsFirstArg());
        when(reservationHolderService.create(any())).then(AdditionalAnswers.returnsFirstArg());
        when(reservationService.create(any())).then(AdditionalAnswers.returnsFirstArg());
        when(reservationRoomService.create(any())).then(AdditionalAnswers.returnsFirstArg());
        when(guestService.create(any())).then(AdditionalAnswers.returnsFirstArg());
        when(guestReservationService.create(any())).then(AdditionalAnswers.returnsFirstArg());
        when(promoTypeService.retrieveByPromoCode(anyString())).thenReturn(testHelper.createTestPromoType());
        when(promoService.retrieveByPromoTypeId(anyLong())).thenReturn(testHelper.createMultiplePromos(2));

        //Act
        Reservation reservation = reservationWebService.saveCreate(commandModel);

        //Assert
        //assert (reservation.getId() != null);
        //assert (reservation.getPromo() != null);
        assert (reservation.getStartDate().equals(LocalDate.parse("2018-06-01")));
        assert (reservation.getEndDate().equals(LocalDate.parse("2018-06-14")));
        assert (reservation.getReservationHolder().getPerson().getFirstName().equals("Joe"));
        assert (reservation.getReservationHolder().getPerson().getLastName().equals("Schmoe"));

    }

    @Test(expected = InvalidPromoException.class)
    public void saveCreateInvalidPromo() throws InvalidDatesException, InvalidPromoException {

        SaveReservationCommandModel commandModel = new SaveReservationCommandModel();
        List<InputPersonDetailsViewModel> personDetailsViewModels = new ArrayList<>();

        for (int i=0; i<5; i++) {
            InputPersonDetailsViewModel personDetailsViewModel = testHelper.createTestInputPersonDetailsViewModel();
            personDetailsViewModels.add(personDetailsViewModel);
        }
        commandModel.setPersonDetailsViewModels(personDetailsViewModels);

        SearchAvailableRoomsCommandModel searchAvailableRoomsCommandModel = testHelper.createTestRoomCommandModel(201);
        searchAvailableRoomsCommandModel.setStartDate("2018-06-01");
        searchAvailableRoomsCommandModel.setEndDate("2018-06-14");
        SearchAvailableRoomsViewModel roomsViewModel = new SearchAvailableRoomsViewModel();
        roomsViewModel.setCommandModel(searchAvailableRoomsCommandModel);
        commandModel.setRoomsViewModel(roomsViewModel);

        when(roomRateService.retrieveCurrentRate(anyLong(), anyObject(), anyObject())).thenReturn(testHelper.createTestRoomRate());
        when(roomService.retrieveByRoomNumber(anyInt())).thenReturn(testHelper.createTestRoom());
        when(personService.create(any())).then(AdditionalAnswers.returnsFirstArg());
        when(reservationHolderService.create(any())).then(AdditionalAnswers.returnsFirstArg());
        when(reservationService.create(any())).then(AdditionalAnswers.returnsFirstArg());
        when(reservationRoomService.create(any())).then(AdditionalAnswers.returnsFirstArg());
        when(promoTypeService.retrieveByPromoCode(anyString())).thenReturn(null);
        when(promoService.retrieveByPromoTypeId(anyLong())).thenReturn(testHelper.createMultiplePromos(2));

        //Act
        Reservation reservation = reservationWebService.saveCreate(commandModel);
    }

    @Test(expected = InvalidPromoException.class)
    public void saveCreateInvalidPromoDate() throws InvalidDatesException, InvalidPromoException {

        SaveReservationCommandModel commandModel = new SaveReservationCommandModel();
        List<InputPersonDetailsViewModel> personDetailsViewModels = new ArrayList<>();

        for (int i=0; i<5; i++) {
            InputPersonDetailsViewModel personDetailsViewModel = testHelper.createTestInputPersonDetailsViewModel();
            personDetailsViewModels.add(personDetailsViewModel);
        }
        commandModel.setPersonDetailsViewModels(personDetailsViewModels);

        SearchAvailableRoomsCommandModel searchAvailableRoomsCommandModel = testHelper.createTestRoomCommandModel(201);
        searchAvailableRoomsCommandModel.setStartDate("2018-06-01");
        searchAvailableRoomsCommandModel.setEndDate("2018-06-14");
        SearchAvailableRoomsViewModel roomsViewModel = new SearchAvailableRoomsViewModel();
        roomsViewModel.setCommandModel(searchAvailableRoomsCommandModel);
        commandModel.setRoomsViewModel(roomsViewModel);

        List<Promo> promos = new ArrayList<>();
        for (int i=0; i<3; i++) {
            Promo promo = testHelper.createTestPromo();
            promo.setStartDate(LocalDate.parse("2017-01-01"));
            promo.setEndDate(LocalDate.parse("2017-01-02"));
            promos.add(promo);
        }

        when(roomRateService.retrieveCurrentRate(anyLong(), anyObject(), anyObject())).thenReturn(testHelper.createTestRoomRate());
        when(roomService.retrieveByRoomNumber(anyInt())).thenReturn(testHelper.createTestRoom());
        when(personService.create(any())).then(AdditionalAnswers.returnsFirstArg());
        when(reservationHolderService.create(any())).then(AdditionalAnswers.returnsFirstArg());
        when(reservationService.create(any())).then(AdditionalAnswers.returnsFirstArg());
        when(reservationRoomService.create(any())).then(AdditionalAnswers.returnsFirstArg());
        when(promoTypeService.retrieveByPromoCode(anyString())).thenReturn(testHelper.createTestPromoType());
        when(promoService.retrieveByPromoTypeId(anyLong())).thenReturn(promos);

        //Act
        Reservation reservation = reservationWebService.saveCreate(commandModel);
    }


}