package com.sg.superhero.dao;

import com.sg.superhero.TestHelper;
import com.sg.superhero.dao.interfaces.LocationDao;
import com.sg.superhero.dto.Location;
import com.sg.superhero.dto.Sighting;
import com.sg.superhero.dto.Super;
import com.sg.superhero.dto.SuperSighting;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback
@Transactional
public class LocationDaoImplTest {

    @Inject
    LocationDao locationDao;

    @Inject
    TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create() {

        //Arrange
        Location location = testHelper.createTestLocation();
        locationDao.create(location);

        //Act
        Location createdLocation = locationDao.create(location);


        //Assert
        assert (createdLocation.getId() != null);
        assertLocationEquals(createdLocation, location);
    }

    @Test
    public void retrieve() {

        //Arrange
        Location location = testHelper.createTestLocation();
        locationDao.create(location);

        //Act
        Location readLocation = locationDao.retrieve(location.getId());

        //Assert
        assert (readLocation.getId() != null);
        assertLocationEquals(location, readLocation);

    }

    @Test
    public void update() {

        //Arrange
        Location location = testHelper.createTestLocation();
        locationDao.create(location);

        //Change some stuff
        location.setCity("Gotham City");

        //Act
        locationDao.update(location);

        //Assert
        Location readLocation = locationDao.retrieve(location.getId());
        assert "Gotham City".equals(readLocation.getCity());
    }

    @Test
    public void delete() {

        //Arrange
        Location location = testHelper.createTestLocation();
        locationDao.create(location);

        //Act
        locationDao.delete(location);

        Location readLocation = locationDao.retrieve(location.getId());

        //Assert
        assert (null == readLocation);
    }

    @Test
    public void retrieveAll() {

        //Arrange
        testHelper.createMultipleLocations(25);

        //Act
        List<Location> locationList = locationDao.retrieveAll(Integer.MAX_VALUE, 0);

        //Assert
        assert locationList.size() == 25;
    }

    @Test
    public void getLocationbySuper() {

        //Arrange

            List<Location> locationList = testHelper.createMultipleLocations(15);

            List <Super> superList = testHelper.createMultipleSupers(2);

            List <SuperSighting> superSightingList = new ArrayList<>();


        for (int i=0; i<10; i++) {
            Sighting sighting = testHelper.createTestSighting(locationList.get(i),
                    LocalDate.now().plusDays((long) i));

            SuperSighting currentSuperSighting = testHelper.createTestSuperSighting(superList.get(0), sighting);
            superSightingList.add(currentSuperSighting);
        }
        for (int i=0; i<2; i++) {
            Sighting sighting = testHelper.createTestSighting(locationList.get(i),
                    LocalDate.now().plusDays((long) i));
            SuperSighting currentSuperSighting = testHelper.createTestSuperSighting(superList.get(1), sighting);
            superSightingList.add(currentSuperSighting);
        }

        //Act
        List<Location> returnedLocationList1 = locationDao.retrieveLocationBySuper(superList.get(0), Integer.MAX_VALUE, 0);
        List<Location> returnedLocationList2 = locationDao.retrieveLocationBySuper(superList.get(1), Integer.MAX_VALUE, 0);


        //Assert
        assert returnedLocationList1.size() == 10;
        assert returnedLocationList2.size() == 2;
    }

    public void assertLocationEquals(Location loc1, Location loc2) {
        assert (loc1.getName().equals(loc2.getName()));
        assert (loc1.getCity().equals(loc2.getCity()));
        assert (loc1.getDescription().equals(loc2.getDescription()));
        assert (loc1.getLatitude().equals(loc2.getLatitude()));
        assert (loc1.getLongitude().equals(loc2.getLongitude()));
        assert (loc1.getState().equals(loc2.getState()));
        assert (loc1.getStreet().equals(loc2.getStreet()));
        assert (loc1.getZip().equals(loc2.getZip()));


    }
}