package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.OrderDao;
import com.sg.flooringmastery.dao.ProductDao;
import com.sg.flooringmastery.dao.TaxDao;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class FlooringMasteryServiceLayerImpl implements FlooringMasteryServiceLayer{

    private TaxDao taxDao;
    private ProductDao productDao;
    private OrderDao orderDao;

    public FlooringMasteryServiceLayerImpl(TaxDao taxDao, ProductDao productDao, OrderDao orderDao) {
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.taxDao = taxDao;
    }

    @Override
    public List<Order> retrieveAllOrdersByDate(LocalDate date) {
        return null;
    }

    @Override
    public boolean validateOrdersExistForDate(LocalDate date) {
        return false;
    }

    @Override
    public Order addOrder(Order orderToAdd) {
        return null;
    }

    @Override
    public void saveCurrentWork(Map<Integer, Order> orderMap) {

    }

    @Override
    public Order updateOrder(Order orderToUpdate) {
        return null;
    }

    @Override
    public void removeOrder(Order orderToRemove) {

    }

    @Override
    public Order retrieveOrderByDateAndId(LocalDate date, int orderId) {
        return null;
    }

    @Override
    public String checkEnvironment() {
        return null;
    }

    @Override
    public Order processOrder(Order orderToProcess) {
        return null;
    }

    @Override
    public List<Tax> retrieveTaxes() {
        return null;
    }

    @Override
    public List<Product> retrieveProducts() {
        return null;
    }

    @Override
    public void setMode(OrderDao orderDao) {

    }

    private boolean validateProductExists(Order orderToValidate) {
        return true;
    }

    private boolean validateStateExists(Order orderToValidate) {
        return true;
    }

    private boolean validateOrderExists(Order order) {
        return true;
    }
}
