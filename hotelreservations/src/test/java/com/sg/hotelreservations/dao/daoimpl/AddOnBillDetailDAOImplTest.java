package com.sg.hotelreservations.dao.daoimpl;

import com.sg.TestHelper;
import com.sg.hotelreservations.config.UnitTestConfiguration;
import com.sg.hotelreservations.dao.daoInterface.AddOnBillDetailDAO;
import com.sg.hotelreservations.dao.daoInterface.RoomRateDAO;
import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.dto.RoomRate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;


import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UnitTestConfiguration.class}) //(locations = {"/test-applicationContext.xml"})
@Rollback
@Transactional
@SpringBootTest(classes = {AddOnBillDetailDAOImpl.class, TestHelper.class})
public class AddOnBillDetailDAOImplTest {

    @Autowired
    AddOnBillDetailDAO addOnBillDetailDAO;

    @Autowired
    TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create() {
        //Arrange
        AddOnBillDetail addOnBillDetail = testHelper.createTestAddOnBillDetail();
        addOnBillDetailDAO.create(addOnBillDetail);

        //Act
        AddOnBillDetail createdAddOnBillDetail = addOnBillDetailDAO.create(addOnBillDetail);

        //Assert
        assert (createdAddOnBillDetail.getId() != null);
        assertEquals("77.24", addOnBillDetail.getTaxAmount().toString());
        assertEquals("NH Meals Tax", addOnBillDetail.getTax().getType());
        assertEquals("921.11", addOnBillDetail.getPrice().toString());
        assertEquals("2018-08-15", addOnBillDetail.getTransactionDate().toString());
        assertEquals("25", addOnBillDetail.getAddOnRate().getPrice().toString());
        assert(addOnBillDetail.getBill().getId() != null);
        assert (addOnBillDetail.getPromo().getId() != null);

    }

    @Test
    public void retrieve() {

        //Arrange
        AddOnBillDetail addOnBillDetail = testHelper.createTestAddOnBillDetail();
        addOnBillDetailDAO.create(addOnBillDetail);

        //Act
        AddOnBillDetail readAddOnBillDetail = addOnBillDetailDAO.retrieve(addOnBillDetail.getId());

        //Assert
        assert (readAddOnBillDetail.getId() != null);
        assertEquals(addOnBillDetail.getId(), readAddOnBillDetail.getId());
        assertEquals(addOnBillDetail.getTax().getId(), readAddOnBillDetail.getTax().getId());
        //assertEquals(addOnBillDetail.getPromo().getId(), readAddOnBillDetail.getPromo().getId());
        assertEquals(addOnBillDetail.getAddOnRate().getId(), readAddOnBillDetail.getAddOnRate().getId());
        assertEquals(addOnBillDetail.getBill().getId(), readAddOnBillDetail.getBill().getId());
        assert(addOnBillDetail.getTaxAmount().compareTo(readAddOnBillDetail.getTaxAmount())==0);
        assert(addOnBillDetail.getPrice().compareTo(readAddOnBillDetail.getPrice())==0);
        assertEquals(addOnBillDetail.getTransactionDate(), readAddOnBillDetail.getTransactionDate());

    }

    @Test
    public void update() {

        //Arrange
        AddOnBillDetail addOnBillDetail = testHelper.createTestAddOnBillDetail();
        addOnBillDetailDAO.create(addOnBillDetail);

        //Change some stuff
        addOnBillDetail.setPrice(new BigDecimal("515.02"));
        addOnBillDetail.setTaxAmount(new BigDecimal("77.23"));
        addOnBillDetail.setTransactionDate(LocalDate.parse("2018-09-16"));

        //Act
        addOnBillDetailDAO.update(addOnBillDetail);

        //Assert
        AddOnBillDetail readAddOnBillDetail = addOnBillDetailDAO.retrieve(addOnBillDetail.getId());
        assert(readAddOnBillDetail.getPrice().compareTo(addOnBillDetail.getPrice())==0);
        assert(readAddOnBillDetail.getTaxAmount().compareTo(addOnBillDetail.getTaxAmount())==0);
        assertEquals(readAddOnBillDetail.getTransactionDate(), addOnBillDetail.getTransactionDate());
    }

    @Test
    public void delete() {

        //Arrange
        AddOnBillDetail addOnBillDetail = testHelper.createTestAddOnBillDetail();
        addOnBillDetailDAO.create(addOnBillDetail);

        //Act
        addOnBillDetailDAO.delete(addOnBillDetail);

        AddOnBillDetail readAddOnBillDetail = addOnBillDetailDAO.retrieve(addOnBillDetail.getId());

        //Assert
        assert (null == readAddOnBillDetail);
    }

    @Test
    public void retrieveAll() {

        //Arrange
        testHelper.createMultipleAddOnBillDetails(25);

        //Act
        List<AddOnBillDetail> addOnBillDetailList = addOnBillDetailDAO.retrieveAll(Integer.MAX_VALUE, 0);

        //Assert
        assertEquals(25,  addOnBillDetailList.size());
    }

    @Test
    public void retrieveByBillId() {

        AddOnBillDetail addOnBillDetail = testHelper.createTestAddOnBillDetail();
        Long billId = addOnBillDetail.getBill().getId();

        List<AddOnBillDetail> retrievedAddOnBillDetail = addOnBillDetailDAO.retrieveByBillId(billId);

        assertEquals(addOnBillDetail.getId(), retrievedAddOnBillDetail.get(0).getId());

    }

    @org.springframework.context.annotation.Configuration
    public static class ContextConfiguration {
    }
}