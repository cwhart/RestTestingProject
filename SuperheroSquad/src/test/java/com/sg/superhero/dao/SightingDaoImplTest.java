package com.sg.superhero.dao;

import com.sg.superhero.TestHelper;
import com.sg.superhero.dao.interfaces.OrganizationDao;
import com.sg.superhero.dao.interfaces.SightingDao;
import com.sg.superhero.dto.*;
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
public class SightingDaoImplTest {

    @Inject
    SightingDao sightingDao;

    @Inject
    TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create() {

        //Arrange
        Sighting sighting = testHelper.createTestSighting();
        sightingDao.create(sighting);

        //Act
        Sighting createdSighting = sightingDao.create(sighting);


        //Assert
        assert (createdSighting.getId() != null);
        assertSightingsEqual(createdSighting, sighting);
    }

    @Test
    public void retrieve() {

        //Arrange
        Sighting sighting = testHelper.createTestSighting();
        sightingDao.create(sighting);

        //Act
        Sighting readSighting = sightingDao.retrieve(sighting.getId());

        //Assert
        assert (readSighting.getId() != null);
        assertSightingsEqual(sighting, readSighting);
    }

    @Test
    public void update() {

        Sighting sighting = testHelper.createTestSighting();
        sightingDao.create(sighting);

        //Change some stuff
        sighting.setDate(LocalDate.parse("2016-01-01"));

        //Act
        sightingDao.update(sighting);

        //Assert
        Sighting readOrganization = sightingDao.retrieve(sighting.getId());
        assert "2016-01-01".equals(readOrganization.getDate().toString());

    }

    @Test
    public void delete() {

        //Arrange
        Sighting sighting = testHelper.createTestSighting();
        sightingDao.create(sighting);

        //Act
        assert (sighting.getId() != null);
        sightingDao.delete(sighting);

        Sighting readSighting = sightingDao.retrieve(sighting.getId());

        //Assert
        assert (null == readSighting);
    }



    @Test
    public void retrieveAll() {

        //Arrange
        List<Sighting> sightingList = testHelper.createMultipleSightings(25);

        //Act
        List<Sighting> readSightingList = sightingDao.retrieveAll(Integer.MAX_VALUE, 0);

        //Assert
        assert sightingList.size() == 25;
        assertSightingListsEqual(readSightingList, sightingList);
    }

    @Test
    public void getSightingsBySuper() {

        //Arrange
        List<Sighting> sightingList = testHelper.createMultipleSightings(15);

        Super superPerson = testHelper.createTestSuper();
        for (int i = 0; i < 10; i++) {
            Sighting sighting = testHelper.createTestSighting();
            testHelper.createTestSuperSighting(superPerson, sighting);
        }

        //Act
        List<Sighting> sightingList1 = sightingDao.retrieveSightingsBySuper(superPerson, Integer.MAX_VALUE, 0);

        assert sightingList1.size() == 10;

//        for (Power power : powerList) {
//            List<Super> superList = powerDao.retrieveSupersByPower(power, Integer.MAX_VALUE, 0);
//
//            boolean exists = false;
//
//            for (Super sup : superList) {
//                if (sup.getId().equals(superPerson.getId()) && sup.getId().equals(superPerson2.getId())) {
//                    exists = true;
//                }
//            }
//            assert exists;
//
//        }


    }

    @Test
    public void getSightingsByLocationAndSuperAndDate() {

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
        List<Sighting> sightingList = sightingDao.retrieveSightingsByLocationAndSuperAndDate(locationList.get(1),
                superList.get(0), LocalDate.now().plusDays(1), Integer.MAX_VALUE, 0);

        //Assert
        assert sightingList.size() == 1;//
    }

    public void assertSightingsEqual(Sighting sighting1, Sighting sighting2) {
        assert (sighting1.getLocation().getId().equals(sighting2.getLocation().getId()));
        assert (sighting1.getDate().equals(sighting2.getDate()));

    }

    private void assertSightingListsEqual(List<Sighting> sighting1, List<Sighting> sighting2) {
        assert sighting1.size() == sighting2.size();
        for (Sighting sighting : sighting1) {
            boolean exists = false;
            for (Sighting originalSighting : sighting2) {
                if (sighting.getId().equals(originalSighting.getId())) {
                    exists = true;
                }
            }
            assert exists == true;
        }
    }
}