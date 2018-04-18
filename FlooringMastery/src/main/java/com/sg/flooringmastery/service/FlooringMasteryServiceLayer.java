package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.OrderDao;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface FlooringMasteryServiceLayer {

    public List<Order> retrieveAllOrdersByDate(LocalDate date);

    public boolean validateOrdersExistForDate(LocalDate date);

    public Order addOrder(Order orderToAdd);

    public void saveCurrentWork(Map<Integer, Order> orderMap);

    public Order updateOrder(Order orderToUpdate);

    public void removeOrder(Order orderToRemove);

    public Order retrieveOrderByDateAndId(LocalDate date, int orderId);

    public String checkEnvironment();

    public Order processOrder(Order orderToProcess);

    public List<Tax> retrieveTaxes();

    public List<Product> retrieveProducts();

    public void setMode(OrderDao orderDao);

}
