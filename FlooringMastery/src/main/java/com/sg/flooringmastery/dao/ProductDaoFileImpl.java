package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDaoFileImpl implements ProductDao {

    private final String PRODUCT_FILE = "products.txt";
    private final String DELIMITER = ",";
    private Map<String, Product> productMap = new HashMap<>();

    @Override
    public Product createProduct(Product productToAdd) {
        return null;
    }

    @Override
    public Product retrieveProduct(String productToRetrieve) {
        return null;
    }

    @Override
    public List<Product> retrieveAllProducts() {
        return null;
    }

    @Override
    public Product updateProduct(Product productToUpdate) {
        return null;
    }

    @Override
    public Product removeProduct(Product productToRemove) {
        return null;
    }

    private void loadProductFile() {

    }

    private void writeProductFile() {

    }
}
