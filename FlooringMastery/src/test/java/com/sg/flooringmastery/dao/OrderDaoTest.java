package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class OrderDaoTest {

    OrderDao dao = new OrderDaoFileImpl();

    @Before
    public void setUp() throws Exception {
        List<Order> orderList = new ArrayList<>();
        for(Order currentOrder : dao.retrieveOrdersByDate(LocalDate.parse("2018-04-17"))) {
            dao.removeOrder(currentOrder);
        }
    }

    @Test
    public void testCreateAndRetrieveOrder() throws OrderPersistenceException, Exception {
        Order order1 = new Order(5);
        order1.setCustomerLastName("Smith1");
        order1.setOrderDate(LocalDate.parse("2018-04-17"));
        order1.setState("NJ");
        order1.setOrderTax(new Tax("NJ", BigDecimal.valueOf(4.42)));
        order1.setOrderProduct(new Product("Pine", BigDecimal.valueOf(5.25), BigDecimal.valueOf(3.55)));
        order1.setArea(BigDecimal.valueOf(250));
        order1.setCalculatedMaterialCost(BigDecimal.valueOf(1312.5));
        order1.setCalculatedLaborCost(BigDecimal.valueOf(887.5));
        order1.setCalculatedTaxAmount(BigDecimal.valueOf(92.4));
        order1.setTotalOrderAmount(BigDecimal.valueOf(2292.4));
        Order addedOrder = dao.createOrder(order1);

        assertEquals(order1, addedOrder);

    }

    @Test
    public void testRetrieveOrderByDateAndId() throws OrderPersistenceException, Exception{
        Order order1 = new Order(5);
        order1.setCustomerLastName("Smith1");
        order1.setOrderDate(LocalDate.parse("2018-04-17"));
        order1.setState("NJ");
        order1.setOrderTax(new Tax("NJ", BigDecimal.valueOf(4.42)));
        order1.setOrderProduct(new Product("Pine", BigDecimal.valueOf(5.25), BigDecimal.valueOf(3.55)));
        order1.setArea(BigDecimal.valueOf(250));
        order1.setCalculatedMaterialCost(BigDecimal.valueOf(1312.5));
        order1.setCalculatedLaborCost(BigDecimal.valueOf(887.5));
        order1.setCalculatedTaxAmount(BigDecimal.valueOf(92.4));
        order1.setTotalOrderAmount(BigDecimal.valueOf(2292.4));
        Order addedOrder = dao.createOrder(order1);
        dao.save();
        Order retrievedOrder = dao.retrieveOrderByDateAndId(order1.getOrderDate(), order1.getOrderNumber());

        assertEquals(addedOrder, retrievedOrder);

    }

    @Test
    public void retrieveOrdersByDate() throws OrderPersistenceException{
        List<Order> orderListForGivenDate = dao.retrieveOrdersByDate(LocalDate.parse("2018-04-17"));
        assertEquals(2, orderListForGivenDate.size());

    }

    @Test
    public void testUpdateOrder() {
    }

    @Test
    public void testRemoveOrder() {
    }

    @Test
    public void testSave() throws OrderPersistenceException, Exception {
        Order order1 = new Order(1);
        order1.setCustomerLastName("Smith1");
        order1.setOrderDate(LocalDate.parse("2018-04-17"));
        order1.setState("NJ");
        order1.setOrderTax(new Tax("NJ", BigDecimal.valueOf(4.42)));
        order1.setOrderProduct(new Product("Pine", BigDecimal.valueOf(5.25), BigDecimal.valueOf(3.55)));
        order1.setArea(BigDecimal.valueOf(250));
        order1.setCalculatedMaterialCost(BigDecimal.valueOf(1312.5));
        order1.setCalculatedLaborCost(BigDecimal.valueOf(887.5));
        order1.setCalculatedTaxAmount(BigDecimal.valueOf(92.4));
        order1.setTotalOrderAmount(BigDecimal.valueOf(2292.4));
        dao.createOrder(order1);






    }
}