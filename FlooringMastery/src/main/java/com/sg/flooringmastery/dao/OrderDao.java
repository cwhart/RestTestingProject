package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import org.aspectj.weaver.ast.Or;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface OrderDao {

    public Order createOrder(Order orderToCreate) throws OrderPersistenceException, Exception;

    public Order retrieveOrderByDateAndId(LocalDate orderDate, Integer orderId) throws OrderPersistenceException;

    public List<Order> retrieveOrdersByDate(LocalDate date) throws OrderPersistenceException;

    public Order updateOrder(Order orderToUpdate);

    public void removeOrder(Order orderToRemove) throws OrderPersistenceException;

    public void save() throws OrderPersistenceException;
}
