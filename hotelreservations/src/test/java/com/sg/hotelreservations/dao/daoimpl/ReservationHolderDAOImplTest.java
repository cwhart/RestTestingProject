package com.sg.hotelreservations.dao.daoimpl;

import com.sg.TestHelper;
import com.sg.hotelreservations.config.UnitTestConfiguration;
import com.sg.hotelreservations.dao.daoInterface.GuestDAO;
import com.sg.hotelreservations.dao.daoInterface.ReservationHolderDAO;
import com.sg.hotelreservations.dto.Guest;
import com.sg.hotelreservations.dto.Person;
import com.sg.hotelreservations.dto.ReservationHolder;
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
@SpringBootTest(classes = {ReservationHolderDAOImpl.class, TestHelper.class})
public class ReservationHolderDAOImplTest {

    @Inject
    ReservationHolderDAO reservationHolderDAO;

    @Inject
    TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create() {
        //Arrange
        ReservationHolder reservationHolder = testHelper.createTestReservationHolder();
        reservationHolderDAO.create(reservationHolder);

        //Act
        ReservationHolder createdReservationHolder = reservationHolderDAO.create(reservationHolder);

        //Assert
        assert (createdReservationHolder.getId() != null);
        assertEquals(createdReservationHolder, reservationHolder);
    }

    @Test
    public void retrieve() {

        //Arrange
        ReservationHolder reservationHolder = testHelper.createTestReservationHolder();
        reservationHolderDAO.create(reservationHolder);

        //Act
        ReservationHolder readReservationHolder = reservationHolderDAO.retrieve(reservationHolder.getId());

        //Assert
        assert (readReservationHolder.getId() != null);
        assertEquals(reservationHolder.getId(), readReservationHolder.getId());
        assertEquals(reservationHolder.getPerson().getId(), readReservationHolder.getPerson().getId());


    }

    @Test
    public void update() {

        //Arrange
        ReservationHolder reservationHolder = testHelper.createTestReservationHolder();
        reservationHolderDAO.create(reservationHolder);
        Person person = testHelper.createTestPerson();

        //Change some stuff
        reservationHolder.setPerson(person);

        //Act
        reservationHolderDAO.update(reservationHolder);

        //Assert
        ReservationHolder readReservationHolder = reservationHolderDAO.retrieve(reservationHolder.getId());
        assert (readReservationHolder.getPerson().getId().equals(person.getId()));
    }

    @Test
    public void delete() {

        //Arrange
        ReservationHolder reservationHolder = testHelper.createTestReservationHolder();
        reservationHolderDAO.create(reservationHolder);

        //Act
        reservationHolderDAO.delete(reservationHolder);

        ReservationHolder readReservationHolder = reservationHolderDAO.retrieve(reservationHolder.getId());

        //Assert
        assert (null == readReservationHolder);
    }

    @Test
    public void retrieveAll() {

        //Arrange
        testHelper.createMultipleReservationHolders(25);

        //Act
        List<ReservationHolder> reservationHolderList = reservationHolderDAO.retrieveAll(Integer.MAX_VALUE, 0);

        //Assert
        assert reservationHolderList.size() == 25;
    }
}