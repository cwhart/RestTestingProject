package com.sg.hotelreservations.dao.daoimpl;

import com.sg.TestHelper;
import com.sg.hotelreservations.config.UnitTestConfiguration;
import com.sg.hotelreservations.dao.daoInterface.GuestReservationDAO;
import com.sg.hotelreservations.dao.daoInterface.ReservationRoomDAO;
import com.sg.hotelreservations.dto.GuestReservation;
import com.sg.hotelreservations.dto.Reservation;
import com.sg.hotelreservations.dto.ReservationRoom;
import com.sg.hotelreservations.dto.Room;
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

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UnitTestConfiguration.class})
@Rollback
@Transactional
@SpringBootTest(classes = {ReservationRoomDAOImpl.class, TestHelper.class})
public class ReservationRoomDAOImplTest {

    @Inject
    ReservationRoomDAO reservationRoomDAO;

    @Inject
    TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create() {
        //Arrange
        ReservationRoom reservationRoom = testHelper.createTestReservationRoom();
        reservationRoomDAO.create(reservationRoom);

        //Act
        ReservationRoom createdReservationRoom = reservationRoomDAO.create(reservationRoom);

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
        List<ReservationRoom> createdReservationRooms = reservationRoomDAO.retrieveByRoomId(reservationRoom1.getRoom().getId(), Integer.MAX_VALUE, 0);

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
        List<ReservationRoom> createdReservationRooms = reservationRoomDAO.retrieveByReservationId(reservationRoom1.getReservation().getId());

        //Assert
        assertEquals(2, createdReservationRooms.size());

    }

//    @Test
//    public void retrieveByDates() {
//
//        //Arrange
//        RoomApi room = testHelper.createTestRoom();
//        Reservation res1 = testHelper.createTestReservationSpecifyDates(LocalDate.parse("2018-09-01"),
//                LocalDate.parse("2018-09-10"));
//        Reservation res2 = testHelper.createTestReservationSpecifyDates(LocalDate.parse("2018-09-04"),
//                LocalDate.parse("2018-09-05"));
//        Reservation res3 = testHelper.createTestReservationSpecifyDates(LocalDate.parse("2018-09-08"),
//                LocalDate.parse("2018-09-20"));
//        Reservation res4 = testHelper.createTestReservationSpecifyDates(LocalDate.parse("2018-10-01"),
//                LocalDate.parse("2018-10-10"));
//        ReservationRoom resRoom1 = testHelper.createTestResRoomSpecifyResAndRoom(res1, room);
//        ReservationRoom resRoom2 = testHelper.createTestResRoomSpecifyResAndRoom(res2, room);
//        ReservationRoom resRoom3 = testHelper.createTestResRoomSpecifyResAndRoom(res3, room);
//        ReservationRoom resRoom4 = testHelper.createTestResRoomSpecifyResAndRoom(res4, room);
//
//        //Act
//        List<ReservationRoom> retrievedResRooms =
//                reservationRoomDAO.retrieveByDates(LocalDate.parse("2018-09-03"), LocalDate.parse("2018-09-07"));
//
//        //Assert
//        assertEquals(3, retrievedResRooms.size());
//
//    }

    @Test
    public void delete() {

        //Arrange
        ReservationRoom reservationRoom = testHelper.createTestReservationRoom();
        reservationRoomDAO.create(reservationRoom);

        //Act
        reservationRoomDAO.delete(reservationRoom);

        List<ReservationRoom> readReservationRoom = reservationRoomDAO.retrieveByRoomId(reservationRoom.getRoom().getId(), Integer.MAX_VALUE, 0);

        //Assert
        assertEquals (0, readReservationRoom.size());
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

        List<ReservationRoom> reservationRoomList = reservationRoomDAO.retrieveByDates(LocalDate.parse("2018-07-02"),
                LocalDate.parse("2018-07-07"));

        assertEquals(3, reservationRoomList.size());

    }

    @Test
    public void isBooked() {

        Reservation reservation = testHelper.createTestReservationSpecifyDates(LocalDate.parse("2018-10-01"),
                LocalDate.parse("2018-10-15"));
        ReservationRoom reservationRoom = testHelper.createTestReservationRoomSpecifyReservation(reservation.getId());
        Room room = reservationRoom.getRoom();

        Boolean booked = reservationRoomDAO.isBooked(room.getId(), LocalDate.parse("2018-10-02"));
        Boolean notBooked = reservationRoomDAO.isBooked(room.getId(), LocalDate.parse("2018-11-02"));

        assertEquals(true, booked);
        assertEquals(false, notBooked);

    }

}