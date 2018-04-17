package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;

import java.util.List;

public interface ProductDao {

    public Product createProduct(Product productToAdd);

    public Product retrieveProduct(String productToRetrieve);

    public List<Product> retrieveAllProducts();

    public Product updateProduct(Product productToUpdate);

    public Product removeProduct(Product productToRemove);
}
