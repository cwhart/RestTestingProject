package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.*;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ServiceLayer {

    public List<Order> retrieveAllOrdersByDate(LocalDate date) throws OrderPersistenceException;

    public Order addOrder(Order orderToAdd) throws Exception;

    public void saveCurrentWork() throws OrderPersistenceException;

    public Order updateOrder(Order orderToUpdate) throws OrderPersistenceException;

    public void removeOrder(Order orderToRemove) throws OrderPersistenceException;

    public Order retrieveOrderByDateAndId(LocalDate date, int orderId) throws OrderPersistenceException;

    public Order processOrder(Order orderToProcess) throws TaxPersistenceException, ProductPersistenceException, OrderPersistenceException;

    public List<Tax> retrieveTaxes() throws TaxPersistenceException;

    public List<Product> retrieveProducts() throws ProductPersistenceException;

    public void setMode(String mode);

    //..

}
