package com.sg.hotelreservations.dao.daoimpl;

import com.sg.TestHelper;
import com.sg.hotelreservations.dao.daoInterface.AddOnDAO;
import com.sg.hotelreservations.dao.daoInterface.BillDAO;
import com.sg.hotelreservations.dto.AddOn;
import com.sg.hotelreservations.dto.Bill;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback
@Transactional
public class BillDAOImplTest {

    @Inject
    BillDAO billDAO;

    @Inject
    TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create() {

        //Arrange
        Bill bill = testHelper.createTestBill();
        billDAO.create(bill);

        //Act
        Bill createdBill = billDAO.create(bill);


        //Assert
        assert (createdBill.getId() != null);
        assertEquals(createdBill, bill);
    }

    @Test
    public void retrieve() {

        //Arrange
        Bill bill = testHelper.createTestBill();
        billDAO.create(bill);

        //Act
        Bill readBill = billDAO.retrieve(bill.getId());

        //Assert
        assert (readBill.getId() != null);
        assertEquals(bill.getTotal().toString(), readBill.getTotal().toString());
        assertEquals(bill.getReservation().getId(), readBill.getReservation().getId());
    }

    @Test
    public void update() {

        //Arrange
        Bill bill = testHelper.createTestBill();
        billDAO.create(bill);

        //Change some stuff
        bill.setTotal(new BigDecimal("1025.25"));

        //Act
        billDAO.update(bill);

        //Assert
        Bill readBill = billDAO.retrieve(bill.getId());
        assert "1025.25".equals(readBill.getTotal().toString());
    }

    @Test
    public void delete() {

        //Arrange
        Bill bill = testHelper.createTestBill();
        billDAO.create(bill);

        //Act
        billDAO.delete(bill);

        Bill readBill = billDAO.retrieve(bill.getId());

        //Assert
        assert (null == readBill);
    }

    @Test
    public void retrieveAll() {

        //Arrange
        testHelper.createMultipleBills(25);

        //Act
        List<Bill> billList = billDAO.retrieveAll(Integer.MAX_VALUE, 0);

        //Assert
        assert billList.size() == 25;
    }
}