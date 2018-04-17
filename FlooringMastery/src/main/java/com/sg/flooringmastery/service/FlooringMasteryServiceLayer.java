package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dto.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface FlooringMasteryServiceLayer {

    public List retrieveAllOrdersByDate();

    public boolean validateOrdersExistForDate(LocalDate date);

    public Order addOrder(Order orderToAdd);

    public int generateOrderNumber();

    public void saveCurrentWork(Map<Integer, Order> orderMap);

    public Order updateOrder(Order orderToUpdate);

    public Order removeOrder(Order orderToRemove);

    public Order retrieveOrderById(int orderId);

    public String checkEnvironment();

    public Order processOrder(Order orderToProcess);

}
