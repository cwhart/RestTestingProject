package com.sg.hotelreservations.service.serviceimpl;

import com.sg.TestHelper;
import com.sg.hotelreservations.config.UnitTestConfiguration;
import com.sg.hotelreservations.dao.daoInterface.ReservationRoomDAO;
import com.sg.hotelreservations.dao.daoInterface.RoomDAO;
import com.sg.hotelreservations.dao.daoimpl.AddOnBillDetailDAOImpl;
import com.sg.hotelreservations.dto.Reservation;
import com.sg.hotelreservations.dto.ReservationRoom;
import com.sg.hotelreservations.dto.Room;
import com.sg.hotelreservations.service.serviceinterface.ReservationRoomService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UnitTestConfiguration.class})
@Rollback
@Transactional
@SpringBootTest(classes = {ReservationRoomServiceImpl.class, TestHelper.class})
public class ReservationRoomServiceImplTest {

    @Inject
    ReservationRoomService reservationRoomService;

    @Inject
    TestHelper testHelper;

    @Inject
    RoomDAO roomDAO;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create() {
        //Arrange
        ReservationRoom reservationRoom = testHelper.createTestReservationRoom();
        reservationRoomService.create(reservationRoom);

        //Act
        ReservationRoom createdReservationRoom = reservationRoomService.create(reservationRoom);

        //Assert
        assert (createdReservationRoom.getRoom().getId() != null);
        assertEquals(createdReservationRoom.getRoom().getId(), reservationRoom.getRoom().getId());
        assertEquals(createdReservationRoom.getReservation().getId(), reservationRoom.getReservation().getId());
    }

    @Test
    public void retrieveByRoomId() {
        //Arrange - add 3 objects, 2 with the same roomID
        ReservationRoom reservationRoom1 = testHelper.createTestReservationRoom();
        ReservationRoom reservationRoom2 = testHelper.createTestReservationRoom();
        ReservationRoom reservationRoom3 = testHelper.createTestReservationRoom(reservationRoom1.getRoom().getId());

        //Act
        List<ReservationRoom> createdReservationRooms = reservationRoomService.retrieveByRoomId(reservationRoom1.getRoom().getId(), Integer.MAX_VALUE, 0);

        //Assert
        assertEquals(2, createdReservationRooms.size());
    }

    @Test
    public void retrieveByReservationId() {

        //Arrange - add 3 objects, 2 with the same amenityId
        ReservationRoom reservationRoom1 = testHelper.createTestReservationRoom();
        ReservationRoom reservationRoom2 = testHelper.createTestReservationRoom();
        ReservationRoom reservationRoom3 = testHelper.createTestReservationRoomSpecifyReservation(reservationRoom1.getReservation().getId());

        //Act
        List<ReservationRoom> createdReservationRooms = reservationRoomService.retrieveByReservationId(reservationRoom1.getReservation());

        //Assert
        assertEquals(2, createdReservationRooms.size());

    }

    @Test
    public void delete() {

        //Arrange
        ReservationRoom reservationRoom = testHelper.createTestReservationRoom();
        reservationRoomService.create(reservationRoom);

        //Act
        reservationRoomService.delete(reservationRoom);

        List<ReservationRoom> readReservationRoom = reservationRoomService.retrieveByRoomId(reservationRoom.getRoom().getId(), Integer.MAX_VALUE, 0);

        //Assert
        assertEquals (0, readReservationRoom.size());
    }

    @Test
    public void isBooked() {

        Reservation reservation = testHelper.createTestReservation();
        ReservationRoom reservationRoom = testHelper.createTestReservationRoomSpecifyReservation(reservation.getId());
        Room room = reservationRoom.getRoom();

        assertEquals(true, reservationRoomService.isBooked(room.getId(), LocalDate.parse("2018-08-11")));
        assertEquals(false, reservationRoomService.isBooked(room.getId(), LocalDate.parse("2018-08-12")));


    }

    @Test
    public void isBookedForDateRangeNotBooked() {

        Room room = testHelper.createTestRoom();

        assertFalse(reservationRoomService.isBookedForDateRange(room.getRoomNumber(), LocalDate.parse("2018-01-01"),
                LocalDate.parse("2018-01-10")));

    }

    @Test
    public void isBookedForDateRangeDatesBeforeInclusiveBooked() {

        Reservation reservation = testHelper.createTestReservationSpecifyDates(LocalDate.parse("2018-01-01"),
                LocalDate.parse("2018-01-11"));
        ReservationRoom reservationRoom = testHelper.createTestReservationRoomSpecifyReservation(reservation.getId());
        Room room = reservationRoom.getRoom();

        assertTrue(reservationRoomService.isBookedForDateRange(room.getRoomNumber(), LocalDate.parse("2018-01-10"),
                LocalDate.parse("2018-01-15")));

    }

    @Test
    public void isBookedForDateRangeDatesAfterInclusiveBooked() {

        Reservation reservation = testHelper.createTestReservationSpecifyDates(LocalDate.parse("2018-01-14"),
                LocalDate.parse("2018-01-17"));
        ReservationRoom reservationRoom = testHelper.createTestReservationRoomSpecifyReservation(reservation.getId());
        Room room = reservationRoom.getRoom();

        assertTrue(reservationRoomService.isBookedForDateRange(room.getRoomNumber(), LocalDate.parse("2018-01-10"),
                LocalDate.parse("2018-01-15")));

    }

    @Test
    public void isBookedForDateRangeDatesAfterEdgeConditionNotBooked() {

        Reservation reservation = testHelper.createTestReservationSpecifyDates(LocalDate.parse("2018-01-15"),
                LocalDate.parse("2018-01-17"));
        ReservationRoom reservationRoom = testHelper.createTestReservationRoomSpecifyReservation(reservation.getId());
        Room room = reservationRoom.getRoom();

        assertFalse(reservationRoomService.isBookedForDateRange(room.getRoomNumber(), LocalDate.parse("2018-01-10"),
                LocalDate.parse("2018-01-15")));

    }

    @Test
    public void isBookedForDateRangeDifferentRoomSameDates() {

        Reservation reservation = testHelper.createTestReservationSpecifyDates(LocalDate.parse("2018-01-10"),
                LocalDate.parse("2018-01-15"));
        ReservationRoom reservationRoom = testHelper.createTestReservationRoomSpecifyReservation(reservation.getId());
        Room room = reservationRoom.getRoom();
        Room room1 = testHelper.createTestRoom();
        room1.setRoomNumber(101);
        roomDAO.update(room1);

        assertFalse(reservationRoomService.isBookedForDateRange(101, LocalDate.parse("2018-01-10"),
                LocalDate.parse("2018-01-15")));

    }

    @Test
    public void retrieveByDates() {

        Reservation res1 = testHelper.createTestReservationSpecifyDates(LocalDate.parse("2018-07-01"),
                LocalDate.parse("2018-07-10"));
        Reservation res2 = testHelper.createTestReservationSpecifyDates(LocalDate.parse("2018-06-01"),
                LocalDate.parse("2018-06-10"));
        Reservation res3 = testHelper.createTestReservationSpecifyDates(LocalDate.parse("2018-07-01"),
                LocalDate.parse("2018-07-05"));
        Reservation res4 = testHelper.createTestReservationSpecifyDates(LocalDate.parse("2018-07-02"),
                LocalDate.parse("2018-07-09"));
        Reservation res5 = testHelper.createTestReservationSpecifyDates(LocalDate.parse("2018-08-01"),
                LocalDate.parse("2018-08-10"));
        testHelper.createTestReservationRoomSpecifyReservation(res1.getId());
        testHelper.createTestReservationRoomSpecifyReservation(res2.getId());
        testHelper.createTestReservationRoomSpecifyReservation(res3.getId());
        testHelper.createTestReservationRoomSpecifyReservation(res4.getId());
        testHelper.createTestReservationRoomSpecifyReservation(res5.getId());

        List<ReservationRoom> reservationRoomList = reservationRoomService.retrieveByDates(LocalDate.parse("2018-07-02"),
                LocalDate.parse("2018-07-07"));

        assertEquals(3, reservationRoomList.size());

    }

}