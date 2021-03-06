package com.sg.hotelreservations.dao.daoimpl;

import com.sg.TestHelper;
import com.sg.hotelreservations.config.UnitTestConfiguration;
import com.sg.hotelreservations.dao.daoInterface.RoomDAO;
import com.sg.hotelreservations.dao.daoInterface.RoomRateDAO;
import com.sg.hotelreservations.dto.Room;
import com.sg.hotelreservations.dto.RoomRate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UnitTestConfiguration.class})
@Rollback
@Transactional
@SpringBootTest(classes = {RoomRateDAOImpl.class, TestHelper.class})
public class RoomRateDAOImplTest {

    @Inject
    RoomRateDAO roomRateDAO;

    @Inject
    TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create() {
        //Arrange
        RoomRate roomRate = testHelper.createTestRoomRate();
        roomRateDAO.create(roomRate);

        //Act
        RoomRate createdRoomRate = roomRateDAO.create(roomRate);

        //Assert
        assert (createdRoomRate.getId() != null);
        assertEquals(createdRoomRate, roomRate);
    }

    @Test
    public void retrieve() {

        //Arrange
        RoomRate roomRate = testHelper.createTestRoomRate();
        roomRateDAO.create(roomRate);

        //Act
        RoomRate readRoomRate = roomRateDAO.retrieve(roomRate.getId());

        //Assert
        assert (readRoomRate.getId() != null);
        assertEquals(roomRate.getId(), readRoomRate.getId());
        assertEquals(roomRate.getRoom().getId(), readRoomRate.getRoom().getId());
        assertEquals(roomRate.getStartDate(), readRoomRate.getStartDate());
        assertEquals(roomRate.getEndDate(), readRoomRate.getEndDate());
        assert(roomRate.getPrice().compareTo(readRoomRate.getPrice())==0);

    }

    @Test
    public void update() {

        //Arrange
        RoomRate roomRate = testHelper.createTestRoomRate();
        roomRateDAO.create(roomRate);

        //Change some stuff
        roomRate.setPrice(new BigDecimal("215"));
        roomRate.setStartDate(LocalDate.parse("2018-07-15"));
        roomRate.setEndDate(LocalDate.parse("2018-09-15"));
        roomRate.setRoom(testHelper.createTestRoom());

        //Act
        roomRateDAO.update(roomRate);

        //Assert
        RoomRate readRoom = roomRateDAO.retrieve(roomRate.getId());
        assert(readRoom.getPrice().compareTo(roomRate.getPrice())==0);
        assertEquals(readRoom.getEndDate(), roomRate.getEndDate());
        assertEquals(readRoom.getStartDate(), roomRate.getStartDate());
        assertEquals(readRoom.getRoom().getId(), roomRate.getRoom().getId());
    }

    @Test
    public void delete() {

        //Arrange
        RoomRate roomRate = testHelper.createTestRoomRate();
        roomRateDAO.create(roomRate);

        //Act
        roomRateDAO.delete(roomRate);

        RoomRate readRoom = roomRateDAO.retrieve(roomRate.getId());

        //Assert
        assert (null == readRoom);
    }

    @Test
    public void retrieveAll() {

        //Arrange
        testHelper.createMultipleRoomRates(25);

        //Act
        List<RoomRate> roomRateList = roomRateDAO.retrieveAll(Integer.MAX_VALUE, 0);

        //Assert
        assert roomRateList.size() == 25;
    }

    @Test
    public void retrieveByRoomNumber() {

        Room room1 = testHelper.createTestRoom();
        Room room2 = testHelper.createTestRoom();
        RoomRate roomRate1 = testHelper.createTestRoomRateSpecifyRoom(room1.getId());
        RoomRate roomRate2 = testHelper.createTestRoomRateSpecifyRoom(room2.getId());
        RoomRate roomRate3 = testHelper.createTestRoomRateSpecifyRoom(room2.getId());

        List<RoomRate> roomRateList = roomRateDAO.retrieveByRoomId(room2.getId());

        assertEquals(2, roomRateList.size());


    }
}