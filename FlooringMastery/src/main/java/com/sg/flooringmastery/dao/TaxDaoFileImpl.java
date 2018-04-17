package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaxDaoFileImpl implements TaxDao {

    private final String TAX_FILE = "taxes.txt";
    private final String DELIMITER = ",";
    private Map<String, Tax> taxMap = new HashMap<>();

    @Override
    public Tax createTax(Tax tax) {
        return null;
    }

    @Override
    public Tax retrieveTax(String state) {
        return null;
    }

    @Override
    public List<Tax> retrieveAllTaxes() {
        return null;
    }

    @Override
    public Tax updateTax(Tax tax) {
        return null;
    }

    @Override
    public Tax removeTax(Tax tax) {
        return null;
    }

    private void loadTaxFile() {

    }

    private void writeTaxFile() {

    }
}
