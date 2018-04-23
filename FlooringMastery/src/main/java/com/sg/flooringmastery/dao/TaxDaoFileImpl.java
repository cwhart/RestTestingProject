package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class TaxDaoFileImpl implements TaxDao {

    private final String TAX_FILE = "taxes.txt";
    private final String DELIMITER = ",";
    private Map<String, Tax> taxMap = new HashMap<>();

    @Override
    public Tax createTax(Tax tax) throws TaxPersistenceException {
        loadTaxFile();
        Tax taxToReturn = taxMap.put(tax.getState(), tax);
        writeTaxFile();
        return taxToReturn;
    }

    @Override
    public Tax retrieveTax(String state) throws TaxPersistenceException {
        loadTaxFile();
        return taxMap.get(state);
    }

    @Override
    public List<Tax> retrieveAllTaxes() throws TaxPersistenceException {
        loadTaxFile();
        List<Tax> listOfTaxes = new ArrayList<>();
        for (Tax currentTax : taxMap.values()) {
            listOfTaxes.add(currentTax);
        }
        return listOfTaxes;
    }

    @Override
    public Tax updateTax(Tax tax) throws TaxPersistenceException {
        loadTaxFile();
        Tax taxToReturn = taxMap.replace(tax.getState(), tax);
        writeTaxFile();
        return taxToReturn;
    }

    @Override
    public Tax removeTax(Tax tax) throws TaxPersistenceException {
        loadTaxFile();
        Tax taxToReturn = taxMap.remove(tax.getState());
        writeTaxFile();
        return taxToReturn;
    }

    private void loadTaxFile() throws TaxPersistenceException {

        Scanner scanner;

        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(TAX_FILE)));

        } catch (FileNotFoundException e) {
            throw new TaxPersistenceException("-_- Could not load Tax data into memory", e);
        }

        String currentLine;
        String[] currentTokens;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);

            Tax currentTax = new Tax(currentTokens[0]);

            currentTax.setTaxRate(new BigDecimal(currentTokens[1]));

            taxMap.put(currentTax.getState(), currentTax);
        }

        scanner.close();

    }

    private void writeTaxFile() throws TaxPersistenceException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(TAX_FILE));
        } catch (IOException e) {
            throw new TaxPersistenceException("Could not save Tax data." , e);


        }

        List<Tax> taxList = this.retrieveAllTaxes();
        for (Tax currentTax : taxList) {
            out.println(currentTax.getState() + DELIMITER + currentTax.getTaxRate().setScale(2) + DELIMITER);

            out.flush();
        }

        out.close();
    }

    //..
}
