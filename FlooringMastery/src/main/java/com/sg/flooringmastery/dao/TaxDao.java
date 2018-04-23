package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Tax;

import java.util.List;

public interface TaxDao {

    public Tax createTax(Tax tax) throws TaxPersistenceException;

    public Tax retrieveTax(String state) throws TaxPersistenceException;

    public List<Tax> retrieveAllTaxes() throws TaxPersistenceException;

    public Tax updateTax(Tax tax) throws TaxPersistenceException;

    public Tax removeTax(Tax tax) throws TaxPersistenceException;

    //..

}
