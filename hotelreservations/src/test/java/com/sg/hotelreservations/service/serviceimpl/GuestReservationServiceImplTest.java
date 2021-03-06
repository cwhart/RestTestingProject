package com.sg.hotelreservations.service.serviceimpl;

import com.sg.TestHelper;
import com.sg.hotelreservations.config.UnitTestConfiguration;
import com.sg.hotelreservations.dao.daoInterface.GuestReservationDAO;
import com.sg.hotelreservations.dao.daoimpl.AddOnBillDetailDAOImpl;
import com.sg.hotelreservations.dto.GuestReservation;
import com.sg.hotelreservations.service.serviceinterface.GuestReservationService;
import com.sg.hotelreservations.service.serviceinterface.GuestService;
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
@SpringBootTest(classes = {GuestReservationServiceImpl.class, TestHelper.class})
public class GuestReservationServiceImplTest {

    @Inject
    GuestReservationService guestReservationService;

    @Inject
    TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create() {
        //Arrange
        GuestReservation guestReservation = testHelper.createTestGuestReservation();
        guestReservationService.create(guestReservation);

        //Act
        GuestReservation createdGuestReservation = guestReservationService.create(guestReservation);

        //Assert
        assert (createdGuestReservation.getGuest().getId() != null);
        assertEquals(createdGuestReservation.getGuest().getId(), guestReservation.getGuest().getId());
        assertEquals(createdGuestReservation.getReservation().getId(), guestReservation.getReservation().getId());
    }

    @Test
    public void retrieveByGuestId() {
        //Arrange - add 3 objects, 2 with the same roomID
        GuestReservation guestReservation1 = testHelper.createTestGuestReservation();
        GuestReservation guestReservation2 = testHelper.createTestGuestReservation();
        GuestReservation guestReservation3 = testHelper.createTestGuestReservation(guestReservation1.getGuest().getId());

        //Act
        List<GuestReservation> createdGuestReservations = guestReservationService.retrieveByGuestId(guestReservation1.getGuest().getId(), Integer.MAX_VALUE, 0);

        //Assert
        assertEquals(2, createdGuestReservations.size());
    }

    @Test
    public void retrieveByAmenityId() {

        //Arrange - add 3 objects, 2 with the same amenityId
        GuestReservation guestReservation1 = testHelper.createTestGuestReservation();
        GuestReservation guestReservation2 = testHelper.createTestGuestReservation();
        GuestReservation guestReservation3 = testHelper.createTestGuestReservationSpecifyReservation(guestReservation1.getReservation().getId());

        //Act
        List<GuestReservation> createdGuestReservations = guestReservationService.retrieveByReservationId(guestReservation1.getReservation().getId());

        //Assert
        assertEquals(2, createdGuestReservations.size());

    }

    @Test
    public void delete() {

        //Arrange
        GuestReservation guestReservation = testHelper.createTestGuestReservation();
        guestReservationService.create(guestReservation);

        //Act
        guestReservationService.delete(guestReservation);

        List<GuestReservation> readGuestReservation = guestReservationService.retrieveByGuestId(guestReservation.getGuest().getId(), Integer.MAX_VALUE, 0);

        //Assert
        assertEquals (0, readGuestReservation.size());
    }

}