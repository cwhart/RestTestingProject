package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;

import java.util.List;

public interface ProductDao {

    public Product createProduct(Product productToAdd) throws ProductPersistenceException;

    public Product retrieveProduct(String productToRetrieve) throws ProductPersistenceException;

    public List<Product> retrieveAllProducts() throws ProductPersistenceException;

    public Product updateProduct(Product productToUpdate) throws ProductPersistenceException;

    public Product removeProduct(Product productToRemove) throws ProductPersistenceException;

    //..
}
