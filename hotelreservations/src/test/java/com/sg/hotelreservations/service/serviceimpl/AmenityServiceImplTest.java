package com.sg.hotelreservations.service.serviceimpl;

import com.sg.TestHelper;
import com.sg.hotelreservations.config.UnitTestConfiguration;
import com.sg.hotelreservations.dao.daoInterface.AmenityDAO;
import com.sg.hotelreservations.dao.daoimpl.AddOnBillDetailDAOImpl;
import com.sg.hotelreservations.dto.Amenity;
import com.sg.hotelreservations.dto.RoomAmenity;
import com.sg.hotelreservations.service.serviceinterface.AmenityService;
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
@SpringBootTest(classes = {AmenityServiceImpl.class, TestHelper.class})
public class AmenityServiceImplTest {

    @Inject
    AmenityService amenityService;

    @Inject
    TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create() {
        //Arrange
        Amenity amenity = testHelper.createTestAmenity();
        amenityService.create(amenity);

        //Act
        Amenity createdAmenity = amenityService.create(amenity);

        //Assert
        assert (createdAmenity.getId() != null);
        assertEquals(createdAmenity, amenity);
    }

    @Test
    public void retrieve() {

        //Arrange
        Amenity amenity = testHelper.createTestAmenity();
        amenityService.create(amenity);

        //Act
        Amenity readAmenity = amenityService.retrieve(amenity.getId());

        //Assert
        assert (readAmenity.getId() != null);
        assertEquals(amenity.getId(), readAmenity.getId());
        assertEquals(amenity.getType(), readAmenity.getType());

    }

    @Test
    public void update() {

        //Arrange
        Amenity amenity = testHelper.createTestAmenity();
        amenityService.create(amenity);

        //Change some stuff
        amenity.setType("Pull-out Couch");

        //Act
        amenityService.update(amenity);

        //Assert
        Amenity readAmenity = amenityService.retrieve(amenity.getId());
        assert "Pull-out Couch".equals(readAmenity.getType());
    }

    @Test
    public void delete() {

        //Arrange
        Amenity amenity = testHelper.createTestAmenity();
        amenityService.create(amenity);

        //Act
        amenityService.delete(amenity);

        Amenity readAmenity = amenityService.retrieve(amenity.getId());

        //Assert
        assert (null == readAmenity);
    }

    @Test
    public void retrieveAll() {

        //Arrange
        testHelper.createMultipleAmenities(25);

        //Act
        List<Amenity> amenityList = amenityService.retrieveAll(Integer.MAX_VALUE, 0);

        //Assert
        assert amenityList.size() == 25;
    }

    @Test
    public void retrieveAmenitiesByRoom() {

        //Arrange
        RoomAmenity roomAmenity = testHelper.createTestRoomAmenity();
        testHelper.createTestRoomAmenity(roomAmenity.getRoom().getId());
        testHelper.createTestRoomAmenity(roomAmenity.getRoom().getId());

        //Act
        List<Amenity> amenityList = amenityService.retrieveAmenityByRoom(roomAmenity.getRoom().getId());

        //Assert
        assert amenityList.size() == 3;


    }
}