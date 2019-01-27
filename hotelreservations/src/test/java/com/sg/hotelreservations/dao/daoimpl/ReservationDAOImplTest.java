package com.sg.hotelreservations.dao.daoimpl;

import com.sg.TestHelper;
import com.sg.hotelreservations.config.UnitTestConfiguration;
import com.sg.hotelreservations.dao.daoInterface.PersonDAO;
import com.sg.hotelreservations.dao.daoInterface.ReservationDAO;
import com.sg.hotelreservations.dto.Person;
import com.sg.hotelreservations.dto.Reservation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UnitTestConfiguration.class})
@Rollback
@Transactional
@SpringBootTest(classes = {ReservationDAOImpl.class, TestHelper.class})
public class ReservationDAOImplTest {

    @Inject
    ReservationDAO reservationDAO;

    @Inject
    TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create() {
        //Arrange
        Reservation reservation = testHelper.createTestReservation();
        reservationDAO.create(reservation);

        //Act
        Reservation createdReservation = reservationDAO.create(reservation);

        //Assert
        assert (createdReservation.getId() != null);
        assertEquals(createdReservation, reservation);
    }

    @Test
    public void retrieve() {

        //Arrange
        Reservation reservation = testHelper.createTestReservation();
        reservationDAO.create(reservation);

        //Act
        Reservation readReservation = reservationDAO.retrieve(reservation.getId());

        //Assert
        assert (readReservation.getId() != null);
        assertEquals(reservation.getId(), readReservation.getId());
        assertEquals(reservation.getPromo().getId(), readReservation.getPromo().getId());
        assertEquals(reservation.getReservationHolder().getId(), readReservation.getReservationHolder().getId());
        assertEquals(reservation.getStartDate(), readReservation.getStartDate());
        assertEquals(reservation.getEndDate(), readReservation.getEndDate());

    }

    @Test
    public void update() {

        //Arrange
        Reservation reservation = testHelper.createTestReservation();
        reservationDAO.create(reservation);

        //Change some stuff
        reservation.setStartDate(LocalDate.parse("2018-09-27"));
        reservation.setEndDate(LocalDate.parse("2018-10-02"));

        //Act
        reservationDAO.update(reservation);

        //Assert
        Reservation readReservation = reservationDAO.retrieve(reservation.getId());
        assert "2018-09-27".equals(readReservation.getStartDate().toString());
        assert "2018-10-02".equals(readReservation.getEndDate().toString());

    }

    @Test
    public void delete() {

        //Arrange
        Reservation reservation = testHelper.createTestReservation();
        reservationDAO.create(reservation);

        //Act
        reservationDAO.delete(reservation);

        Reservation readReservation = reservationDAO.retrieve(reservation.getId());

        //Assert
        assert (null == readReservation);
    }

    @Test
    public void retrieveAll() {

        //Arrange
        testHelper.createMultipleReservations(25);

        //Act
        List<Reservation> reservationList = reservationDAO.retrieveAll(Integer.MAX_VALUE, 0);

        //Assert
        assert reservationList.size() == 25;
    }
}