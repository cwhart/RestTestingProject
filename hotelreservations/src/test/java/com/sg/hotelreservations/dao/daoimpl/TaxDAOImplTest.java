package com.sg.hotelreservations.dao.daoimpl;

import com.sg.TestHelper;
import com.sg.hotelreservations.config.UnitTestConfiguration;
import com.sg.hotelreservations.dao.daoInterface.AddOnDAO;
import com.sg.hotelreservations.dao.daoInterface.TaxDAO;
import com.sg.hotelreservations.dto.AddOn;
import com.sg.hotelreservations.dto.Tax;
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
@SpringBootTest(classes = {TaxDAOImpl.class, TestHelper.class})
public class TaxDAOImplTest {

    @Inject
    TaxDAO taxDAO;

    @Inject
    TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create() {

        //Arrange
        Tax tax = testHelper.createTestTax();
        taxDAO.create(tax);

        //Act
        Tax createdTax = taxDAO.create(tax);


        //Assert
        assert (createdTax.getId() != null);
        assertEquals(createdTax, tax);
    }

    @Test
    public void retrieve() {

        //Arrange
        Tax tax = testHelper.createTestTax();
        taxDAO.create(tax);

        //Act
        Tax readTax = taxDAO.retrieve(tax.getId());

        //Assert
        assert (readTax.getId() != null);
        assertEquals(tax.getType(), readTax.getType());
    }

    @Test
    public void update() {

        //Arrange
        Tax tax = testHelper.createTestTax();
        taxDAO.create(tax);

        //Change some stuff
        tax.setType("NH Property Tax");
        tax.setEndDate(LocalDate.parse("2030-12-31"));

        //Act
        taxDAO.update(tax);

        //Assert
        Tax readTax = taxDAO.retrieve(tax.getId());
        assert "NH Property Tax".equals(readTax.getType());
        assert(tax.getEndDate().toString().equals(readTax.getEndDate().toString()));
    }

    @Test
    public void delete() {

        //Arrange
        Tax tax = testHelper.createTestTax();
        taxDAO.create(tax);

        //Act
        taxDAO.delete(tax);

        Tax readTax = taxDAO.retrieve(tax.getId());

        //Assert
        assert (null == readTax);
    }

    @Test
    public void retrieveAll() {

        //Arrange
        testHelper.createMultipleTaxes(25);

        //Act
        List<Tax> taxList = taxDAO.retrieveAll(Integer.MAX_VALUE, 0);

        //Assert
        assert taxList.size() == 25;
    }

    @Test
    public void retrieveByState() {
        //Arrange
        testHelper.createMultipleTaxes(5);
        testHelper.createTestTaxSpecifyState("MA");
        testHelper.createTestTaxSpecifyState("MA");
        testHelper.createTestTaxSpecifyState("VT");

        //Act
        List<Tax> taxList = taxDAO.retrieveByState("NH");
        //List<Tax> taxList = taxDAO.retrieveAll(Integer.MAX_VALUE, 0);

        //Assert
        assert (taxList.size() == 5);
    }

    @Test
    public void retrieveByType() {
        //Arrange
        testHelper.createMultipleTaxes(5);
        testHelper.createTestTaxSpecifyType("Meals");
        testHelper.createTestTaxSpecifyType("Rooms");
        testHelper.createTestTaxSpecifyType("Meals");

        //Act
        List<Tax> taxList = taxDAO.retrieveByType("Meals");
        //List<Tax> taxList = taxDAO.retrieveAll(Integer.MAX_VALUE, 0);

        //Assert
        assert (taxList.size() == 2);
    }
}