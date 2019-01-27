package com.sg.hotelreservations.service.serviceimpl;

import com.sg.TestHelper;
import com.sg.hotelreservations.config.UnitTestConfiguration;
import com.sg.hotelreservations.dao.daoInterface.RoomAmenityDAO;
import com.sg.hotelreservations.dao.daoimpl.AddOnBillDetailDAOImpl;
import com.sg.hotelreservations.dto.RoomAmenity;
import com.sg.hotelreservations.service.serviceinterface.RoomAmenityService;
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

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UnitTestConfiguration.class})
@Rollback
@Transactional
@SpringBootTest(classes = {RoomAmenityServiceImpl.class, TestHelper.class})
public class RoomAmenityServiceImplTest {

    @Inject
    RoomAmenityService roomAmenityService;

    @Inject
    TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create() {
        //Arrange
        RoomAmenity roomAmenity = testHelper.createTestRoomAmenity();
        roomAmenityService.create(roomAmenity);

        //Act
        RoomAmenity createdRoomAmenity = roomAmenityService.create(roomAmenity);

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
        List<RoomAmenity> createdRoomAmenities = roomAmenityService.retrieveByRoomId(roomAmenity1.getRoom().getId(), Integer.MAX_VALUE, 0);

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
        List<RoomAmenity> createdRoomAmenities = roomAmenityService.retrieveByAmenityId(roomAmenity1.getAmenity().getId(), Integer.MAX_VALUE, 0);

        //Assert
        assertEquals(2, createdRoomAmenities.size());

    }

    @Test
    public void delete() {

        //Arrange
        RoomAmenity roomAmenity = testHelper.createTestRoomAmenity();
        roomAmenityService.create(roomAmenity);

        //Act
        roomAmenityService.delete(roomAmenity);

        List<RoomAmenity> readAmenity = roomAmenityService.retrieveByRoomId(roomAmenity.getRoom().getId(), Integer.MAX_VALUE, 0);

        //Assert
        assertEquals (0, readAmenity.size());
    }

}