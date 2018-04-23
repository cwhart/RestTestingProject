package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class ProductDaoFileImpl implements ProductDao {

    private final String PRODUCT_FILE = "products.txt";
    private final String DELIMITER = ",";
    private Map<String, Product> productMap = new HashMap<>();

    @Override
    public Product createProduct(Product productToAdd) throws ProductPersistenceException {
        loadProductFile();
        Product productToReturn = productMap.put(productToAdd.getProductType(), productToAdd);
        writeProductFile();
        return productToReturn;
    }

    @Override
    public Product retrieveProduct(String productToRetrieve) throws ProductPersistenceException{
        loadProductFile();
        return productMap.get(productToRetrieve);
    }

    @Override
    public List<Product> retrieveAllProducts() throws ProductPersistenceException {
        loadProductFile();
        List<Product> productList = new ArrayList<>();
        for (Product currentProduct : productMap.values()) {
            productList.add(currentProduct);
        }
        return productList;
    }

    @Override
    public Product updateProduct(Product productToUpdate) throws ProductPersistenceException {
        loadProductFile();
        Product productToReturn = productMap.replace(productToUpdate.getProductType(), productToUpdate);
        writeProductFile();
        return productToReturn;
    }

    @Override
    public Product removeProduct(Product productToRemove) throws ProductPersistenceException {
        loadProductFile();
        Product productToReturn = productMap.remove(productToRemove.getProductType());
        writeProductFile();
        return productToReturn;
    }

    private void loadProductFile() throws ProductPersistenceException {

        Scanner scanner;

        try {

            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(PRODUCT_FILE)));
        } catch (FileNotFoundException e) {
            throw new ProductPersistenceException("-_- Could not load Product data into memory", e);
        }

        String currentLine;
        String[] currentTokens;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);

            Product currentProduct = new Product(currentTokens[0]);

            currentProduct.setMaterialCostPerSquareFoot(new BigDecimal(currentTokens[1]));
            currentProduct.setLaborCostPerSquareFoot(new BigDecimal(currentTokens[2]));


            productMap.put(currentProduct.getProductType(), currentProduct);

        }

        scanner.close();

    }

    private void writeProductFile() throws ProductPersistenceException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(PRODUCT_FILE));

        } catch (IOException e) {
            throw new ProductPersistenceException("Could not save Product data.", e);
        }

        List<Product> productList = this.retrieveAllProducts();
        for (Product currentProduct : productList) {
            out.println(currentProduct.getProductType() + DELIMITER
                    + currentProduct.getMaterialCostPerSquareFoot().setScale(2) + DELIMITER
                    + currentProduct.getLaborCostPerSquareFoot().setScale(2) + DELIMITER);

            out.flush();
        }

        out.close();

    }

    //..
}
