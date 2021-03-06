package com.sg.superhero.service;

import com.sg.superhero.TestHelper;
import com.sg.superhero.dao.interfaces.SuperDao;
import com.sg.superhero.dto.*;
import com.sg.superhero.service.interfaces.SuperService;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback
@Transactional
public class SuperServiceImplTest {

    @Inject
    SuperService superService;

    @Inject
    TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create() {

        //Arrange
        Super superPerson = testHelper.createTestSuper();
        superService.create(superPerson);

        //Act
        Super createdSuper = superService.create(superPerson);


        //Assert
        assert (createdSuper.getId() != null);
        assertSuperEquals(createdSuper, superPerson);
    }

    @Test
    public void retrieve() {

        //Arrange
        Super superPerson = testHelper.createTestSuper();
        superService.create(superPerson);

        //Act
        Super readSuper = superService.retrieve(superPerson.getId());

        //Assert
        assert (readSuper.getId() != null);
        assertSuperEquals(superPerson, readSuper);
    }

    @Test
    public void update() {

        //Arrange
        Super superPerson = testHelper.createTestSuper();
        superService.create(superPerson);

        //Change some stuff
        superPerson.setName("Supergirl");
        superPerson.setDescription("The Woman of Iron");

        //Act
        superService.update(superPerson);

        //Assert
        Super readSuper = superService.retrieve(superPerson.getId());
        assert "Supergirl".equals(readSuper.getName());
        assert "The Woman of Iron".equals(readSuper.getDescription());
    }

    @Test
    public void delete() {

        //Arrange
        Super superPerson = testHelper.createTestSuper();
        superService.create(superPerson);

        //Act
        assert superService.retrieve(superPerson.getId()) != null;
        superService.delete(superPerson);

        Super readSuper = superService.retrieve(superPerson.getId());

        //Assert
        assert (null == readSuper);//
    }

    @Test
    public void retrieveAll() {

        //Arrange
        testHelper.createMultipleSupers(25);

        //Act
        List<Super> superList = superService.retrieveAll(Integer.MAX_VALUE, 0);

        //Assert
        assert superList.size() == 25;
    }

    @Test
    public void getSupersByOrganization() {

        //Arrange
        List<Super> superList = testHelper.createMultipleSupers(15);

        Organization organization = testHelper.createTestOrganization();
        for (int i = 0; i < 10; i++) {
            Super superPerson = testHelper.createTestSuper();
            testHelper.createTestSuperOrganization(superPerson, organization);
        }

        //Act
        List<Super> retrievedSuperList = superService.retrieveSupersByOrganization(organization, Integer.MAX_VALUE, 0);

        assert retrievedSuperList.size() == 10;
    }

    @Test
    public void getSupersByLocation() {

        //Arrange

        List<Location> locationList = testHelper.createMultipleLocations(3);

        List <Super> superList = testHelper.createMultipleSupers(15);

        List <SuperSighting> superSightingList = new ArrayList<>();


        for (int i=0; i<10; i++) {
            Sighting sighting = testHelper.createTestSighting(locationList.get(1),
                    LocalDate.now().plusDays((long) i));

            SuperSighting currentSuperSighting = testHelper.createTestSuperSighting(superList.get(i), sighting);
            superSightingList.add(currentSuperSighting);
        }
        for (int i=0; i<2; i++) {
            Sighting sighting = testHelper.createTestSighting(locationList.get(2),
                    LocalDate.now().plusDays((long) i));
            SuperSighting currentSuperSighting = testHelper.createTestSuperSighting(superList.get(i), sighting);
            superSightingList.add(currentSuperSighting);
        }

        //Act
        List<Super> returnedSuperList1 = superService.retrieveSupersByLocation(locationList.get(1), Integer.MAX_VALUE, 0);
        List<Super> returnedSuperList2 = superService.retrieveSupersByLocation(locationList.get(2), Integer.MAX_VALUE, 0);


        //Assert
        assert returnedSuperList1.size() == 10;
        assert returnedSuperList2.size() == 2;
    }

    @Test
    public void getSupersBySighting() {

        //Arrange
        List<Super> superList = testHelper.createMultipleSupers(15);

        Sighting sighting = testHelper.createTestSighting();
        for (int i = 0; i < 10; i++) {
            Super superPerson = testHelper.createTestSuper();
            testHelper.createTestSuperSighting(superPerson, sighting);
        }

        //Act
        List<Super> retrievedSuperList = superService.retrieveSupersBySighting(sighting, Integer.MAX_VALUE, 0);

        assert retrievedSuperList.size() == 10;
    }

    public void assertSuperEquals(Super super1, Super super2) {
        assert (super1.getName().equals(super2.getName()));
        assert (super1.getDescription().equals(super2.getDescription()));

    }

    @Test
    public void retrieveSupersByPower() {
        //Arrange
        List<Super> superList = testHelper.createMultipleSupers(15);

        Power power = testHelper.createTestPower();
        for (int i = 0; i < 10; i++) {
            Super superPerson = testHelper.createTestSuper();
            testHelper.createTestSuperPower(superPerson, power);
        }

        //Act
        List<Super> retrievedSuperList = superService.retrieveSupersByPower(power, Integer.MAX_VALUE, 0);

        assert retrievedSuperList.size() == 10;
    }
}