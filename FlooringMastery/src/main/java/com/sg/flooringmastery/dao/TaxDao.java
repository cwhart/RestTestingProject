package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Tax;

import java.util.List;

public interface TaxDao {

    public Tax createTax(Tax tax);

    public Tax retrieveTax(String state);

    public List<Tax> retrieveAllTaxes();

    public Tax updateTax(Tax tax);

    public Tax removeTax(Tax tax);

}
