package com.sg.hotelreservations.dao.daoimpl;

import com.sg.TestHelper;
import com.sg.hotelreservations.dao.daoInterface.AmenityDAO;
import com.sg.hotelreservations.dao.daoInterface.RoomDAO;
import com.sg.hotelreservations.dto.Amenity;
import com.sg.hotelreservations.dto.RoomAmenity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback
@Transactional
public class AmenityDAOImplTest {

    @Inject
    AmenityDAO amenityDAO;

    @Inject
    TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create() {
        //Arrange
        Amenity amenity = testHelper.createTestAmenity();
        amenityDAO.create(amenity);

        //Act
        Amenity createdAmenity = amenityDAO.create(amenity);

        //Assert
        assert (createdAmenity.getId() != null);
        assertEquals(createdAmenity, amenity);
    }

    @Test
    public void retrieve() {

        //Arrange
        Amenity amenity = testHelper.createTestAmenity();
        amenityDAO.create(amenity);

        //Act
        Amenity readAmenity = amenityDAO.retrieve(amenity.getId());

        //Assert
        assert (readAmenity.getId() != null);
        assertEquals(amenity.getId(), readAmenity.getId());
        assertEquals(amenity.getType(), readAmenity.getType());

    }

    @Test
    public void update() {

        //Arrange
        Amenity amenity = testHelper.createTestAmenity();
        amenityDAO.create(amenity);

        //Change some stuff
        amenity.setType("Pull-out Couch");

        //Act
        amenityDAO.update(amenity);

        //Assert
        Amenity readAmenity = amenityDAO.retrieve(amenity.getId());
        assert "Pull-out Couch".equals(readAmenity.getType());
    }

    @Test
    public void delete() {

        //Arrange
        Amenity amenity = testHelper.createTestAmenity();
        amenityDAO.create(amenity);

        //Act
        amenityDAO.delete(amenity);

        Amenity readAmenity = amenityDAO.retrieve(amenity.getId());

        //Assert
        assert (null == readAmenity);
    }

    @Test
    public void retrieveAll() {

        //Arrange
        testHelper.createMultipleAmenities(25);

        //Act
        List<Amenity> amenityList = amenityDAO.retrieveAll(Integer.MAX_VALUE, 0);

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
        List<Amenity> amenityList = amenityDAO.retrieveAmenityByRoom(roomAmenity.getRoom().getId());

        //Assert
        assert amenityList.size() == 3;


    }
}