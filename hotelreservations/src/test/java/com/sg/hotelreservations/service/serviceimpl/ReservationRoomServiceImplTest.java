package com.sg.hotelreservations.service.serviceimpl;

import com.sg.TestHelper;
import com.sg.hotelreservations.dao.daoInterface.ReservationRoomDAO;
import com.sg.hotelreservations.dto.ReservationRoom;
import com.sg.hotelreservations.service.serviceinterface.ReservationRoomService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback
@Transactional
public class ReservationRoomServiceImplTest {

    @Inject
    ReservationRoomService reservationRoomService;

    @Inject
    TestHelper testHelper;

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
        List<ReservationRoom> createdReservationRooms = reservationRoomService.retrieveByReservationId(reservationRoom1.getReservation().getId(), Integer.MAX_VALUE, 0);

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

}