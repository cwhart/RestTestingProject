package com.sg.hotelreservations.dao.daoimpl;

import com.sg.TestHelper;
import com.sg.hotelreservations.config.UnitTestConfiguration;
import com.sg.hotelreservations.dao.daoInterface.AddOnDAO;
import com.sg.hotelreservations.dao.daoInterface.RoomDAO;
import com.sg.hotelreservations.dto.AddOn;
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

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UnitTestConfiguration.class})
@Rollback
@Transactional
@SpringBootTest(classes = {RoomDAOImpl.class, TestHelper.class})
public class RoomDAOImplTest {

    @Inject
    RoomDAO roomDAO;

    @Inject
    TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create() {
        //Arrange
        Room room = testHelper.createTestRoom();
        roomDAO.create(room);

        //Act
        Room createdRoom = roomDAO.create(room);

        //Assert
        assert (createdRoom.getId() != null);
        assertEquals(createdRoom, room);
    }

    @Test
    public void retrieve() {

        //Arrange
        Room room = testHelper.createTestRoom();
        roomDAO.create(room);

        //Act
        Room readRoom = roomDAO.retrieve(room.getId());

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
        roomDAO.create(room);

        //Change some stuff
        room.setType("Double Queen");

        //Act
        roomDAO.update(room);

        //Assert
        Room readRoom = roomDAO.retrieve(room.getId());
        assert "Double Queen".equals(readRoom.getType());
    }

    @Test
    public void delete() {

        //Arrange
        Room room = testHelper.createTestRoom();
        roomDAO.create(room);

        //Act
        roomDAO.delete(room);

        Room readRoom = roomDAO.retrieve(room.getId());

        //Assert
        assert (null == readRoom);
    }

    @Test
    public void retrieveAll() {

        //Arrange
        testHelper.createMultipleRooms(25);

        //Act
        List<Room> roomList = roomDAO.retrieveAll(Integer.MAX_VALUE, 0);

        //Assert
        assert roomList.size() == 25;
    }

    @Test
    public void retrieveByRoomNum() {

        //Arrange
        Room room = testHelper.createTestRoom();

        //Act
        Room readRoom = roomDAO.retrieveByRoomNum(room.getRoomNumber());

        //Assert
        assert (readRoom.getId() != null);
        assertEquals(room.getId(), readRoom.getId());
        assertEquals(room.getType(), readRoom.getType());
        assertEquals(room.getFloorNumber(), readRoom.getFloorNumber());
        assertEquals(room.getOccupancy(), readRoom.getOccupancy());
        assertEquals(room.getRoomNumber(), readRoom.getRoomNumber());

    }
}