package com.sg.hotelreservations.service.serviceimpl;

import com.sg.TestHelper;
import com.sg.hotelreservations.dao.daoInterface.TaxDAO;
import com.sg.hotelreservations.dto.Tax;
import com.sg.hotelreservations.service.serviceinterface.TaxService;
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
public class TaxServiceImplTest {

    @Inject
    TaxService taxService;

    @Inject
    TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create() {

        //Arrange
        Tax tax = testHelper.createTestTax();
        taxService.create(tax);

        //Act
        Tax createdTax = taxService.create(tax);


        //Assert
        assert (createdTax.getId() != null);
        assertEquals(createdTax, tax);
    }

    @Test
    public void retrieve() {

        //Arrange
        Tax tax = testHelper.createTestTax();
        taxService.create(tax);

        //Act
        Tax readTax = taxService.retrieve(tax.getId());

        //Assert
        assert (readTax.getId() != null);
        assertEquals(tax.getType(), readTax.getType());
    }

    @Test
    public void update() {

        //Arrange
        Tax tax = testHelper.createTestTax();
        taxService.create(tax);

        //Change some stuff
        tax.setType("NH Property Tax");
        tax.setEndDate(LocalDate.parse("2030-12-31"));

        //Act
        taxService.update(tax);

        //Assert
        Tax readTax = taxService.retrieve(tax.getId());
        assert "NH Property Tax".equals(readTax.getType());
        assert(tax.getEndDate().toString().equals(readTax.getEndDate().toString()));
    }

    @Test
    public void delete() {

        //Arrange
        Tax tax = testHelper.createTestTax();
        taxService.create(tax);

        //Act
        taxService.delete(tax);

        Tax readTax = taxService.retrieve(tax.getId());

        //Assert
        assert (null == readTax);
    }

    @Test
    public void retrieveAll() {

        //Arrange
        testHelper.createMultipleTaxes(25);

        //Act
        List<Tax> taxList = taxService.retrieveAll(Integer.MAX_VALUE, 0);

        //Assert
        assert taxList.size() == 25;
    }
}