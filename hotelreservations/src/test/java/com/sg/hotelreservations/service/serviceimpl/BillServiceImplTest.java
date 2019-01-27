package com.sg.hotelreservations.service.serviceimpl;

import com.sg.TestHelper;
import com.sg.hotelreservations.config.UnitTestConfiguration;
import com.sg.hotelreservations.dao.daoInterface.BillDAO;
import com.sg.hotelreservations.dao.daoimpl.AddOnBillDetailDAOImpl;
import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.dto.Bill;
import com.sg.hotelreservations.dto.Reservation;
import com.sg.hotelreservations.dto.RoomBillDetail;
import com.sg.hotelreservations.service.serviceinterface.BillService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UnitTestConfiguration.class})
@Rollback
@Transactional
@SpringBootTest(classes = {BillServiceImpl.class, TestHelper.class})
public class BillServiceImplTest {

    @Inject
    BillService billService;

    @Inject
    TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create() {

        //Arrange
        Bill bill = testHelper.createTestBill();
        billService.create(bill);

        //Act
        Bill createdBill = billService.create(bill);


        //Assert
        assert (createdBill.getId() != null);
        assertEquals(createdBill, bill);
    }

    @Test
    public void retrieve() {

        //Arrange
        Bill bill = testHelper.createTestBill();
        billService.create(bill);

        //Act
        Bill readBill = billService.retrieve(bill.getId());

        //Assert
        assert (readBill.getId() != null);
        //assertEquals(bill.getTotal().toString(), readBill.getTotal().toString());
        assertEquals(bill.getReservation().getId(), readBill.getReservation().getId());
    }

    @Test
    public void update() {

        //Arrange
        Bill bill = testHelper.createTestBill();
        billService.create(bill);

        //Change some stuff
        Reservation reservation = testHelper.createTestReservation();
        bill.setReservation(reservation);

        //Act
        billService.update(bill);

        //Assert
        Bill readBill = billService.retrieve(bill.getId());
        assertEquals (reservation.getId(), readBill.getReservation().getId());
    }

    @Test
    public void delete() {

        //Arrange
        //Bill bill = testHelper.createTestBill();
        Bill bill = billService.create(testHelper.createTestBill());

        //Act
        billService.delete(bill);

        Bill readBill = billService.retrieve(bill.getId());

        assertEquals(null, readBill.getId());

    }

    @Test
    public void retrieveAll() {

        //Arrange
        testHelper.createMultipleBills(25);

        //Act
        List<Bill> billList = billService.retrieveAll(Integer.MAX_VALUE, 0);

        //Assert
        assert billList.size() == 25;
    }

    @Test
    public void retrieveByReservationId() {

        Bill bill = testHelper.createTestBill();
        Long reservationId = bill.getReservation().getId();

        Bill retrievedBill = billService.retrieveByReservationId(reservationId);

        assertEquals(retrievedBill.getId(), bill.getId());


    }

    @Test
    public void updateBillTotal() {

        Bill bill = testHelper.createTestBill();
        Long billId = bill.getId();

        RoomBillDetail roomBillDetail1 = testHelper.createTestRoomBillDetailSpecifyBill(billId);
        RoomBillDetail roomBillDetail2 = testHelper.createTestRoomBillDetailSpecifyBill(billId);
        RoomBillDetail roomBillDetail3 = testHelper.createTestRoomBillDetailSpecifyBill(billId);
        RoomBillDetail roomBillDetail4 = testHelper.createTestRoomBillDetailSpecifyBill(billId);
        AddOnBillDetail addOnBillDetail1 = testHelper.createTestAddOnBillDetailSpecifyBill(billId);
        AddOnBillDetail addOnBillDetail2 = testHelper.createTestAddOnBillDetailSpecifyBill(billId);
        AddOnBillDetail addOnBillDetail3 = testHelper.createTestAddOnBillDetailSpecifyBill(billId);

        BigDecimal total = billService.updateBillTotal(bill);

        assertEquals("896.13", total.toString());

    }
}