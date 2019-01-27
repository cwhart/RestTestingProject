package com.sg.hotelreservations.service.serviceimpl;

import com.sg.TestHelper;
import com.sg.hotelreservations.config.UnitTestConfiguration;
import com.sg.hotelreservations.dao.daoInterface.RoomRateDAO;
import com.sg.hotelreservations.dao.daoimpl.AddOnBillDetailDAOImpl;
import com.sg.hotelreservations.dto.Room;
import com.sg.hotelreservations.dto.RoomRate;
import com.sg.hotelreservations.service.serviceinterface.RoomRateService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.core.Local;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UnitTestConfiguration.class})
@Rollback
@Transactional
@SpringBootTest(classes = {RoomRateServiceImpl.class, TestHelper.class})
public class RoomRateServiceImplTest {

    @Inject
    RoomRateService roomRateService;

    @Inject
    TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create() {
        //Arrange
        RoomRate roomRate = testHelper.createTestRoomRate();
        roomRateService.create(roomRate);

        //Act
        RoomRate createdRoomRate = roomRateService.create(roomRate);

        //Assert
        assert (createdRoomRate.getId() != null);
        assertEquals(createdRoomRate, roomRate);
    }

    @Test
    public void retrieve() {

        //Arrange
        RoomRate roomRate = testHelper.createTestRoomRate();
        roomRateService.create(roomRate);

        //Act
        RoomRate readRoomRate = roomRateService.retrieve(roomRate.getId());

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
        roomRateService.create(roomRate);

        //Change some stuff
        roomRate.setPrice(new BigDecimal("215"));
        roomRate.setStartDate(LocalDate.parse("2018-07-15"));
        roomRate.setEndDate(LocalDate.parse("2018-09-15"));
        roomRate.setRoom(testHelper.createTestRoom());

        //Act
        roomRateService.update(roomRate);

        //Assert
        RoomRate readRoom = roomRateService.retrieve(roomRate.getId());
        assert(readRoom.getPrice().compareTo(roomRate.getPrice())==0);
        assertEquals(readRoom.getEndDate(), roomRate.getEndDate());
        assertEquals(readRoom.getStartDate(), roomRate.getStartDate());
        assertEquals(readRoom.getRoom().getId(), roomRate.getRoom().getId());
    }

    @Test
    public void delete() {

        //Arrange
        RoomRate roomRate = testHelper.createTestRoomRate();
        roomRateService.create(roomRate);

        //Act
        roomRateService.delete(roomRate);

        RoomRate readRoom = roomRateService.retrieve(roomRate.getId());

        //Assert
        assert (null == readRoom);
    }

    @Test
    public void retrieveAll() {

        //Arrange
        testHelper.createMultipleRoomRates(25);

        //Act
        List<RoomRate> roomRateList = roomRateService.retrieveAll(Integer.MAX_VALUE, 0);

        //Assert
        assert roomRateList.size() == 25;
    }

    @Test
    public void retrieveDefaultRate() {

        Room room = testHelper.createTestRoom();
        Long roomId = room.getId();
        RoomRate roomRate1 = testHelper.createTestRoomRateSpecifyDefaultFlagRoomAndDates("D", roomId, null, null);
        RoomRate roomRate2 = testHelper.createTestRoomRateSpecifyDefaultFlagRoomAndDates("S", roomId, null, null);
        RoomRate roomRate3 = testHelper.createTestRoomRateSpecifyDefaultFlagRoomAndDates("S", roomId, null, null);
        RoomRate roomRate4 = testHelper.createTestRoomRateSpecifyDefaultFlagRoomAndDates("S", roomId, null, null);
        Long defaultRateId = roomRate1.getId();

        RoomRate defaultRate = roomRateService.retrieveDefaultRate(roomId);

        assertEquals(roomRate1.getId(), defaultRate.getId());

    }

    @Test
    public void retrieveCurrentRate() {

        Room room = testHelper.createTestRoom();
        Long roomId = room.getId();
        RoomRate roomRate1 = testHelper.createTestRoomRateSpecifyDefaultFlagRoomAndDates("D", roomId
                , LocalDate.parse("2018-01-01"), LocalDate.parse("2018-12-31"));
        RoomRate roomRate2 = testHelper.createTestRoomRateSpecifyDefaultFlagRoomAndDates("S", roomId
                , LocalDate.parse("2018-09-01"), LocalDate.parse("2018-09-30"));
        RoomRate roomRate3 = testHelper.createTestRoomRateSpecifyDefaultFlagRoomAndDates("S", roomId
                ,LocalDate.parse("2018-10-01"), LocalDate.parse("2018-10-31"));
        RoomRate roomRate4 = testHelper.createTestRoomRateSpecifyDefaultFlagRoomAndDates("S", roomId
                , LocalDate.parse("2018-12-01"), LocalDate.parse("2018-12-31"));
        Long defaultRateId = roomRate1.getId();

        RoomRate currentRate = roomRateService.retrieveCurrentRate(roomId, LocalDate.parse("2018-12-02"),
                LocalDate.parse("2018-12-11"));

        assertEquals(roomRate4.getId(), currentRate.getId());

    }
}