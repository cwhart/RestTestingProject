package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Tax;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class TaxDaoTest {

    public TaxDao dao = new TaxDaoFileImpl();

    @Before
    public void setUp() throws Exception {
        List<Tax> taxList = dao.retrieveAllTaxes();
        for (Tax currentTax : taxList) {
            dao.removeTax(currentTax);
        }

    }

    @Test
    public void createAndRetrieveTax() {
        Tax newTax = new Tax("NJ");
        newTax.setTaxRate(new BigDecimal("6.2"));
        dao.createTax(newTax);

        assertEquals(newTax, dao.retrieveTax("NJ"));
    }

    @Test
    public void retrieveAllTaxes() {
        Tax newTax1 = new Tax("NJ");
        newTax1.setTaxRate(new BigDecimal("6.2"));
        dao.createTax(newTax1);

        Tax newTax2 = new Tax("NY");
        newTax2.setTaxRate(new BigDecimal("5.7"));
        dao.createTax(newTax2);

        Tax newTax3 = new Tax("WA");
        newTax3.setTaxRate(new BigDecimal("7.1"));
        dao.createTax(newTax3);

        List<Tax> taxList = dao.retrieveAllTaxes();
        assertEquals(3, taxList.size());
        assertEquals(newTax2, dao.retrieveTax("NY"));

    }

    @Test
    public void updateTax() {
        Tax newTax1 = new Tax("NJ");
        newTax1.setTaxRate(new BigDecimal("6.2"));
        dao.createTax(newTax1);

        Tax newTax2 = new Tax("NJ");
        newTax2.setTaxRate(new BigDecimal("3.7"));
        dao.createTax(newTax2);

        assertEquals(1, dao.retrieveAllTaxes().size());
        assertEquals(newTax2.getTaxRate(), dao.retrieveTax("NJ").getTaxRate());

    }

    @Test
    public void removeTax() {
        Tax newTax2 = new Tax("NJ");
        newTax2.setTaxRate(new BigDecimal("3.7"));
        dao.createTax(newTax2);

        assertEquals(1, dao.retrieveAllTaxes().size());

        dao.removeTax(newTax2);
        assertNull(dao.retrieveTax("NJ"));
        assertEquals(0, dao.retrieveAllTaxes().size());
    }
}