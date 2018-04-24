package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Tax;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TaxDaoStubImpl implements TaxDao {

    private Tax testTax;
    private List<Tax> testingTaxes = new ArrayList<>();

    public TaxDaoStubImpl () {
        testTax = new Tax("NH");
        testTax.setTaxRate(BigDecimal.valueOf(3.2));

        testingTaxes.add(testTax);
    }



    @Override
    public Tax createTax(Tax tax) throws TaxPersistenceException {
        if (tax.getState().equals("NH")) {
            return testTax;
        }
        else return null;
    }

    @Override
    public Tax retrieveTax(String state) throws TaxPersistenceException {
        if (state.equals("NH")) {
            return testTax;
        } else return null;
    }

    @Override
    public List<Tax> retrieveAllTaxes() throws TaxPersistenceException {
        return testingTaxes;
    }

    @Override
    public Tax updateTax(Tax tax) throws TaxPersistenceException {
        if (tax.getState().equals("NH")) {
            return testTax;
        } else return null;
    }

    @Override
    public Tax removeTax(Tax tax) throws TaxPersistenceException {
        if (tax.getState().equals("NH")) {
            return testTax;
        } else return null;
    }

    //...
}
