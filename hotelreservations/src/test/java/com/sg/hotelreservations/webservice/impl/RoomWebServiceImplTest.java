package com.sg.hotelreservations.webservice.impl;

import com.sg.TestHelper;
import com.sg.hotelreservations.config.UnitTestConfiguration;
import com.sg.hotelreservations.dto.Room;
import com.sg.hotelreservations.service.serviceinterface.AmenityService;
import com.sg.hotelreservations.service.serviceinterface.ReservationRoomService;
import com.sg.hotelreservations.service.serviceinterface.RoomRateService;
import com.sg.hotelreservations.service.serviceinterface.RoomService;
import com.sg.hotelreservations.viewmodels.room.ListRoomViewModel;
import com.sg.hotelreservations.webservice.exception.InvalidDatesException;
import com.sg.hotelreservations.webservice.webinterface.RoomWebService;
import org.junit.Test;
import org.junit.runner.RunWith;
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

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UnitTestConfiguration.class})
@Rollback
@Transactional
@SpringBootTest(classes = {RoomWebServiceImpl.class, TestHelper.class})
public class RoomWebServiceImplTest {

    @MockBean
    AmenityService amenityService;

    @MockBean
    ReservationRoomService reservationRoomService;

    @MockBean
    RoomRateService roomRateService;

    @Inject
    RoomWebService roomWebService;

    @MockBean
    RoomService roomService;

    @Inject
    TestHelper testHelper;

    @Test
    public void getRoomListViewModel() {

        List<Room> roomList = new ArrayList<>();
        for (int i=0; i<10; i++) {
            roomList.add(testHelper.createTestRoom());
        }

        when(roomService.retrieveAll(anyInt(), anyInt())).thenReturn(roomList);
        when(roomRateService.retrieveDefaultRate(anyLong())).thenReturn(testHelper.createTestRoomRate());

        ListRoomViewModel listRoomViewModel = roomWebService.getRoomListViewModel(0);

        assertEquals(10, listRoomViewModel.getRooms().size());
        assertEquals(null, listRoomViewModel.getMessage());
        assertEquals(1, listRoomViewModel.getPageNumbers()[0]);
        assertEquals(5, listRoomViewModel.getPageNumbers().length);
        assertEquals(1, listRoomViewModel.getSelectedPage());
    }

    @Test
    public void getReservationRoomListViewModelHappyPath() throws InvalidDatesException {

        List<Room> roomList = new ArrayList<>();
        for (int i=0; i<10; i++) {
            roomList.add(testHelper.createTestRoom());
        }
        when(roomService.retrieveAll(anyInt(), anyInt())).thenReturn(roomList);
        when(roomRateService.retrieveCurrentRate(anyLong(), any(), any())).thenReturn(testHelper.createTestRoomRate());

        //Verify numPersons filters out results,
        //start date and end date filter out results. Verify edge conditions. Test no rooms available for dates.
        //Test start date after end date, test start date before today.

        ListRoomViewModel listRoomViewModel = roomWebService.getReservationRoomListViewModel(0, 1,
                LocalDate.now(), LocalDate.now().plusDays(2));

        assertEquals(10, listRoomViewModel.getRooms().size());
    }

    @Test
    public void getReservationRoomListViewModelFilterNumPersons() throws InvalidDatesException{

        List<Room> roomList = new ArrayList<>();
        for (int i=0; i<5; i++) {
            Room room = testHelper.createTestRoom();
            room.setOccupancy(4);
            roomList.add(room);
        }
        for (int i=0; i<5; i++) {
            Room room = testHelper.createTestRoom();
            room.setOccupancy(2);
            roomList.add(room);
        }
        when(roomService.retrieveAll(anyInt(), anyInt())).thenReturn(roomList);
        when(reservationRoomService.isBookedForDateRange(anyInt(), any(), any())).thenReturn(false);
        when(roomRateService.retrieveCurrentRate(anyLong(), any(), any())).thenReturn(testHelper.createTestRoomRate());

        ListRoomViewModel listRoomViewModel = roomWebService.getReservationRoomListViewModel(0, 3,
                LocalDate.now(), LocalDate.now().plusDays(2));

        assertEquals(5, listRoomViewModel.getRooms().size());
    }

    @Test(expected = InvalidDatesException.class)
    public void getReservationRoomListViewModelStartDateBeforeToday() throws InvalidDatesException{

        List<Room> roomList = new ArrayList<>();
        for (int i=0; i<5; i++) {
            Room room = testHelper.createTestRoom();
            room.setOccupancy(4);
            roomList.add(room);
        }

        when(roomService.retrieveAll(anyInt(), anyInt())).thenReturn(roomList);
        when(reservationRoomService.isBookedForDateRange(anyInt(), any(), any())).thenReturn(false);
        when(roomRateService.retrieveCurrentRate(anyLong(), any(), any())).thenReturn(testHelper.createTestRoomRate());

        ListRoomViewModel listRoomViewModel = roomWebService.getReservationRoomListViewModel(0, 3,
                LocalDate.now().minusDays(2), LocalDate.now());

    }

    @Test(expected = InvalidDatesException.class)
    public void getReservationRoomListViewModelStartDateAfterEndDate() throws InvalidDatesException{

        List<Room> roomList = new ArrayList<>();
        for (int i=0; i<5; i++) {
            Room room = testHelper.createTestRoom();
            room.setOccupancy(4);
            roomList.add(room);
        }

        when(roomService.retrieveAll(anyInt(), anyInt())).thenReturn(roomList);
        when(reservationRoomService.isBookedForDateRange(anyInt(), any(), any())).thenReturn(false);
        when(roomRateService.retrieveCurrentRate(anyLong(), any(), any())).thenReturn(testHelper.createTestRoomRate());

        ListRoomViewModel listRoomViewModel = roomWebService.getReservationRoomListViewModel(0, 3,
                LocalDate.now().plusDays(2), LocalDate.now());

    }


    @Test
    public void deleteRoom() {
    }
}