package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface OrderDao {

    public Order createOrder(Order orderToCreate);

    public Order retrieveOrderByOrderId(LocalDate orderDate, Map<Integer, Order> orderMap);

    public List retrieveOrdersByDate(LocalDate date);

    public List retrieveAllOrders();

    public Order updateOrder(Order orderToUpdate);

    public Order removeOrder(Order orderToRemove);
}
