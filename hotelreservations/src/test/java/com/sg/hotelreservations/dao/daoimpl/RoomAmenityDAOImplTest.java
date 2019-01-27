package com.sg.hotelreservations.dao.daoimpl;

import com.sg.TestHelper;
import com.sg.hotelreservations.config.UnitTestConfiguration;
import com.sg.hotelreservations.dao.daoInterface.AmenityDAO;
import com.sg.hotelreservations.dao.daoInterface.RoomAmenityDAO;
import com.sg.hotelreservations.dto.Room;
import com.sg.hotelreservations.dto.RoomAmenity;
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
@SpringBootTest(classes = {RoomAmenityDAOImpl.class, TestHelper.class})
public class RoomAmenityDAOImplTest {

    @Inject
    RoomAmenityDAO roomAmenityDAO;

    @Inject
    TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create() {
        //Arrange
        RoomAmenity roomAmenity = testHelper.createTestRoomAmenity();
        roomAmenityDAO.create(roomAmenity);

        //Act
        RoomAmenity createdRoomAmenity = roomAmenityDAO.create(roomAmenity);

        //Assert
        assert (createdRoomAmenity.getAmenity().getId() != null);
        assertEquals(createdRoomAmenity.getAmenity().getId(), roomAmenity.getAmenity().getId());
        assertEquals(createdRoomAmenity.getRoom().getId(), roomAmenity.getRoom().getId());
    }

    @Test
    public void retrieveByRoomId() {
        //Arrange - add 3 objects, 2 with the same roomID
        RoomAmenity roomAmenity1 = testHelper.createTestRoomAmenity();
        RoomAmenity roomAmenity2 = testHelper.createTestRoomAmenity();
        RoomAmenity roomAmenity3 = testHelper.createTestRoomAmenity(roomAmenity1.getRoom().getId());

        //Act
        List<RoomAmenity> createdRoomAmenities = roomAmenityDAO.retrieveByRoomId(roomAmenity1.getRoom().getId(), Integer.MAX_VALUE, 0);

        //Assert
        assertEquals(2, createdRoomAmenities.size());
    }

    @Test
    public void retrieveByAmenityId() {

        //Arrange - add 3 objects, 2 with the same amenityId
        RoomAmenity roomAmenity1 = testHelper.createTestRoomAmenity();
        RoomAmenity roomAmenity2 = testHelper.createTestRoomAmenity();
        RoomAmenity roomAmenity3 = testHelper.createTestRoomAmenitySpecifyAmenity(roomAmenity1.getAmenity().getId());

        //Act
        List<RoomAmenity> createdRoomAmenities = roomAmenityDAO.retrieveByAmenityId(roomAmenity1.getAmenity().getId(), Integer.MAX_VALUE, 0);

        //Assert
        assertEquals(2, createdRoomAmenities.size());

    }

    @Test
    public void delete() {

        //Arrange
        RoomAmenity roomAmenity = testHelper.createTestRoomAmenity();
        roomAmenityDAO.create(roomAmenity);

        //Act
        roomAmenityDAO.delete(roomAmenity);

        List<RoomAmenity> readAmenity = roomAmenityDAO.retrieveByRoomId(roomAmenity.getRoom().getId(), Integer.MAX_VALUE, 0);

        //Assert
        assertEquals (0, readAmenity.size());
    }

}