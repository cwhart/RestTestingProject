package com.sg.hotelreservations.service.serviceimpl;

import com.sg.TestHelper;
import com.sg.hotelreservations.dao.daoInterface.RoomDAO;
import com.sg.hotelreservations.dto.Room;
import com.sg.hotelreservations.service.serviceinterface.RoomService;
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
public class RoomServiceImplTest {

    @Inject
    RoomService roomService;

    @Inject
    TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create() {
        //Arrange
        Room room = testHelper.createTestRoom();
        roomService.create(room);

        //Act
        Room createdRoom = roomService.create(room);

        //Assert
        assert (createdRoom.getId() != null);
        assertEquals(createdRoom, room);
    }

    @Test
    public void retrieve() {

        //Arrange
        Room room = testHelper.createTestRoom();
        roomService.create(room);

        //Act
        Room readRoom = roomService.retrieve(room.getId());

        //Assert
        assert (readRoom.getId() != null);
        assertEquals(room.getId(), readRoom.getId());
        assertEquals(room.getType(), readRoom.getType());
        assertEquals(room.getFloorNumber(), readRoom.getFloorNumber());
        assertEquals(room.getOccupancy(), readRoom.getOccupancy());
        assertEquals(room.getRoomNumber(), readRoom.getRoomNumber());

    }

    @Test
    public void update() {

        //Arrange
        Room room = testHelper.createTestRoom();
        roomService.create(room);

        //Change some stuff
        room.setType("Double Queen");

        //Act
        roomService.update(room);

        //Assert
        Room readRoom = roomService.retrieve(room.getId());
        assert "Double Queen".equals(readRoom.getType());
    }

    @Test
    public void delete() {

        //Arrange
        Room room = testHelper.createTestRoom();
        roomService.create(room);

        //Act
        roomService.delete(room);

        Room readRoom = roomService.retrieve(room.getId());

        //Assert
        assert (null == readRoom);
    }

    @Test
    public void retrieveAll() {

        //Arrange
        testHelper.createMultipleRooms(25);

        //Act
        List<Room> roomList = roomService.retrieveAll(Integer.MAX_VALUE, 0);

        //Assert
        assert roomList.size() == 25;
    }
}