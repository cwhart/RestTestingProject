package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoStubImpl implements ProductDao {

    private Product testProduct;
    private List<Product> testingProducts = new ArrayList<>();

    public ProductDaoStubImpl () {
        testProduct = new Product("Oak");
        testProduct.setLaborCostPerSquareFoot(BigDecimal.valueOf(3.8));
        testProduct.setMaterialCostPerSquareFoot(BigDecimal.valueOf(2.72));

        testingProducts.add(testProduct);
    }

    @Override
    public Product createProduct(Product product) throws ProductPersistenceException {
        if (product.getProductType().equals("Oak")) {
            return testProduct;
        }
        else return null;
    }

    @Override
    public Product retrieveProduct(String product) throws ProductPersistenceException {
        if (product.equals("Oak")) {
            return testProduct;
        } else return null;
    }

    @Override
    public List<Product> retrieveAllProducts() throws ProductPersistenceException {
        return testingProducts;
    }

    @Override
    public Product updateProduct(Product product) throws ProductPersistenceException {
        if (product.getProductType().equals("Oak")) {
            return testProduct;
        } else return null;
    }

    @Override
    public Product removeProduct(Product product) throws ProductPersistenceException {
        if (product.getProductType().equals("Oak")) {
            return testProduct;
        } else return null;
    }

    //..
}
