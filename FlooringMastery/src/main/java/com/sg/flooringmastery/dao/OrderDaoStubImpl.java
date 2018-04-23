package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDaoStubImpl implements OrderDao{

    private List<Order> day1Orders = new ArrayList<>();
    private Order testOrder = new Order(1);
    private LocalDate testDate = LocalDate.parse("2018-04-17");


    public OrderDaoStubImpl() {
        //Order testOrder = new Order(1);
        testOrder.setCustomerLastName("Smith1");
        testOrder.setOrderDate(testDate);
        testOrder.setState("NJ");
        testOrder.setOrderTax(new Tax("NJ", BigDecimal.valueOf(4.42)));
        testOrder.setOrderProduct(new Product("Pine", BigDecimal.valueOf(5.25), BigDecimal.valueOf(3.55)));
        testOrder.setArea(BigDecimal.valueOf(250));
        testOrder.setCalculatedMaterialCost(testOrder.getCalculatedMaterialCost());
        testOrder.setCalculatedLaborCost(testOrder.getCalculatedLaborCost());
        testOrder.setCalculatedTaxAmount(testOrder.getCalculatedTaxAmount());
        testOrder.setTotalOrderAmount(testOrder.getTotalOrderAmount());

        day1Orders.add(testOrder);

    }

    @Override
    public Order createOrder(Order orderToCreate) throws OrderPersistenceException {
        return orderToCreate;
    }

    @Override
    public Order retrieveOrderByDateAndId(LocalDate orderDate, Integer orderId) throws OrderPersistenceException {
        if (orderId == 1) {
            return testOrder;
        } else return null;
    }

    @Override
    public List<Order> retrieveOrdersByDate(LocalDate date) throws OrderPersistenceException {
        if(date.equals(testDate)) {
            return day1Orders;
        } else return null;
    }

    @Override
    public Order updateOrder(Order orderToUpdate) throws OrderPersistenceException {
        if (orderToUpdate.getOrderNumber() == 1) {
            return orderToUpdate;
        } else return null;
    }

    @Override
    public void removeOrder(Order orderToRemove) throws OrderPersistenceException {
        if(orderToRemove.getOrderNumber()==1) {
            day1Orders.remove(orderToRemove);
        }
    }

    @Override
    public void save() throws OrderPersistenceException {

    }

    //..
}
