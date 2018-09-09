package com.sg.hotelreservations.service.serviceimpl;

import com.sg.TestHelper;
import com.sg.hotelreservations.dao.daoInterface.ReservationDAO;
import com.sg.hotelreservations.dto.Reservation;
import com.sg.hotelreservations.service.serviceinterface.ReservationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback
@Transactional
public class ReservationServiceImplTest {

    @Inject
    ReservationService reservationService;

    @Inject
    TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create() {
        //Arrange
        Reservation reservation = testHelper.createTestReservation();
        reservationService.create(reservation);

        //Act
        Reservation createdReservation = reservationService.create(reservation);

        //Assert
        assert (createdReservation.getId() != null);
        assertEquals(createdReservation, reservation);
    }

    @Test
    public void retrieve() {

        //Arrange
        Reservation reservation = testHelper.createTestReservation();
        reservationService.create(reservation);

        //Act
        Reservation readReservation = reservationService.retrieve(reservation.getId());

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
        reservationService.create(reservation);

        //Change some stuff
        reservation.setStartDate(LocalDate.parse("2018-09-27"));
        reservation.setEndDate(LocalDate.parse("2018-10-02"));

        //Act
        reservationService.update(reservation);

        //Assert
        Reservation readReservation = reservationService.retrieve(reservation.getId());
        assert "2018-09-27".equals(readReservation.getStartDate().toString());
        assert "2018-10-02".equals(readReservation.getEndDate().toString());

    }

    @Test
    public void delete() {

        //Arrange
        Reservation reservation = testHelper.createTestReservation();
        reservationService.create(reservation);

        //Act
        reservationService.delete(reservation);

        Reservation readReservation = reservationService.retrieve(reservation.getId());

        //Assert
        assert (null == readReservation);
    }

    @Test
    public void retrieveAll() {

        //Arrange
        testHelper.createMultipleReservations(25);

        //Act
        List<Reservation> reservationList = reservationService.retrieveAll(Integer.MAX_VALUE, 0);

        //Assert
        assert reservationList.size() == 25;
    }
}