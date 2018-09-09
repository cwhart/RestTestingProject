package com.sg.hotelreservations.service.serviceimpl;

import com.sg.TestHelper;
import com.sg.hotelreservations.dao.daoInterface.GuestDAO;
import com.sg.hotelreservations.dto.Guest;
import com.sg.hotelreservations.dto.Person;
import com.sg.hotelreservations.service.serviceinterface.GuestService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback
@Transactional
public class GuestServiceImplTest {

    @Inject
    GuestService guestService;

    @Inject
    TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create() {
        //Arrange
        Guest guest = testHelper.createTestGuest();
        guestService.create(guest);

        //Act
        Guest createdGuest = guestService.create(guest);

        //Assert
        assert (createdGuest.getId() != null);
        assertEquals(createdGuest, guest);
    }

    @Test
    public void retrieve() {

        //Arrange
        Guest guest = testHelper.createTestGuest();
        guestService.create(guest);

        //Act
        Guest readGuest = guestService.retrieve(guest.getId());

        //Assert
        assert (readGuest.getId() != null);
        assertEquals(guest.getId(), readGuest.getId());
        assertEquals(guest.getPerson().getId(), readGuest.getPerson().getId());


    }

    @Test
    public void update() {

        //Arrange
        Guest guest = testHelper.createTestGuest();
        guestService.create(guest);
        Person person = testHelper.createTestPerson();

        //Change some stuff
        guest.setPerson(person);

        //Act
        guestService.update(guest);

        //Assert
        Guest readGuest = guestService.retrieve(guest.getId());
        assert (readGuest.getPerson().getId().equals(person.getId()));
    }

    @Test
    public void delete() {

        //Arrange
        Guest guest = testHelper.createTestGuest();
        guestService.create(guest);

        //Act
        guestService.delete(guest);

        Guest readGuest = guestService.retrieve(guest.getId());

        //Assert
        assert (null == readGuest);
    }

    @Test
    public void retrieveAll() {

        //Arrange
        testHelper.createMultipleGuests(25);

        //Act
        List<Guest> guestList = guestService.retrieveAll(Integer.MAX_VALUE, 0);

        //Assert
        assert guestList.size() == 25;
    }
}