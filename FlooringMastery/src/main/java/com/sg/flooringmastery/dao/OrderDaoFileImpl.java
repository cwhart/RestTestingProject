package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDaoFileImpl implements OrderDao {

    private String orderFile;
    private final String DELIMITER = ",";
    private Map<Integer, Product> orderMap = new HashMap<>();
    int nextOrderNum = 1;

    @Override
    public Order createOrder(Order orderToCreate) {
        return null;
    }

    @Override
    public Order retrieveOrderByOrderId(LocalDate orderDate, Map<Integer, Order> orderMap) {
        return null;
    }

    @Override
    public List retrieveOrdersByDate(LocalDate date) {
        return null;
    }

    @Override
    public List retrieveAllOrders() {
        return null;
    }

    @Override
    public Order updateOrder(Order orderToUpdate) {
        return null;
    }

    @Override
    public Order removeOrder(Order orderToRemove) {
        return null;
    }

    private void loadOrderFile() {

    }

    private void writeOrderFile() {

    }

    private int generateOrderNumber() {
            return 0;
    }
}
