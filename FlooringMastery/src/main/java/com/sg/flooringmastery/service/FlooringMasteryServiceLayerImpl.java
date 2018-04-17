package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.OrderDao;
import com.sg.flooringmastery.dao.ProductDao;
import com.sg.flooringmastery.dao.TaxDao;
import com.sg.flooringmastery.dto.Order;

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
    public List retrieveAllOrdersByDate() {
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
    public int generateOrderNumber() {
        return 0;
    }

    @Override
    public void saveCurrentWork(Map<Integer, Order> orderMap) {

    }

    @Override
    public Order updateOrder(Order orderToUpdate) {
        return null;
    }

    @Override
    public Order removeOrder(Order orderToRemove) {
        return null;
    }

    @Override
    public Order retrieveOrderById(int orderId) {
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
