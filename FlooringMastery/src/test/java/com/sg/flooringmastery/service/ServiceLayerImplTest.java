package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.*;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class ServiceLayerImplTest {

    private ServiceLayer service;

    public ServiceLayerImplTest() {
        TaxDao taxDao = new TaxDaoStubImpl();
        ProductDao productDao = new ProductDaoStubImpl();
        OrderDao orderDao = new OrderDaoStubImpl();
        service = new ServiceLayerImpl(taxDao, productDao, orderDao);
    }

    @Test
    public void retrieveAllOrdersByDate() throws OrderPersistenceException {
        List<Order> listOfOrders = service.retrieveAllOrdersByDate(LocalDate.parse("2018-04-17"));

        assertEquals(1, listOfOrders.size());
    }

    @Test
    public void addOrder() throws Exception{
        Order order1 = new Order(1);
        order1.setCustomerLastName("Smith1");
        order1.setOrderDate(LocalDate.parse("2018-04-17"));
        order1.setState("NJ");
        order1.setOrderTax(new Tax("NJ", BigDecimal.valueOf(4.42)));
        order1.setOrderProduct(new Product("Pine", BigDecimal.valueOf(5.25), BigDecimal.valueOf(3.55)));
        order1.setArea(BigDecimal.valueOf(250));
        order1.setCalculatedMaterialCost(order1.getCalculatedMaterialCost());
        order1.setCalculatedLaborCost(order1.getCalculatedLaborCost());
        order1.setCalculatedTaxAmount(order1.getCalculatedTaxAmount());
        order1.setTotalOrderAmount(order1.getTotalOrderAmount());
        Order addedOrder = service.addOrder(order1);
        //Order retrievedOrder = service.retrieveOrderByDateAndId(order1.getOrderDate(), order1.getOrderNumber());

        assertEquals(addedOrder, order1);
    }

    @Test
    public void saveCurrentWork() {
    }

    @Test
    public void updateOrder() throws OrderPersistenceException{
        Order testOrder = service.retrieveAllOrdersByDate(LocalDate.parse("2018-04-17")).get(0);
        testOrder.setState("CA");
        Order updatedOrder = service.updateOrder(testOrder);

        assertEquals(testOrder, updatedOrder);
    }

    @Test
    public void removeOrder() throws OrderPersistenceException{
        Order testOrder = service.retrieveAllOrdersByDate(LocalDate.parse("2018-04-17")).get(0);
        service.removeOrder(testOrder);
        assertEquals(0,service.retrieveAllOrdersByDate(LocalDate.parse("2018-04-17")).size() );
    }

    @Test
    public void retrieveOrderByDateAndId() {
    }

    @Test
    public void processOrder() throws ProductPersistenceException, TaxPersistenceException, OrderPersistenceException {

        Order order1 = new Order(1);
        order1.setCustomerLastName("Smith1");
        order1.setOrderDate(LocalDate.parse("2018-04-17"));
        order1.setState("NH");
        //Commenting out setting of the fields that we want the processOrder method to set.
        //order1.setOrderTax(new Tax("NJ", BigDecimal.valueOf(4.42)));
        order1.setOrderProduct(new Product("Oak"));
        order1.setOrderTax(new Tax("NH"));
        order1.setArea(BigDecimal.valueOf(250));
        //order1.setCalculatedMaterialCost(order1.getCalculatedMaterialCost());
        //order1.setCalculatedLaborCost(order1.getCalculatedLaborCost());
        //order1.setCalculatedTaxAmount(order1.getCalculatedTaxAmount());
        //order1.setTotalOrderAmount(order1.getTotalOrderAmount());

        Order processedOrder = service.processOrder(order1);

        assertEquals(BigDecimal.valueOf(3.2), processedOrder.getOrderTax().getTaxRate());
        assertEquals(BigDecimal.valueOf(3.8), processedOrder.getOrderProduct().getLaborCostPerSquareFoot());
        assertEquals(BigDecimal.valueOf(2.72), processedOrder.getOrderProduct().getMaterialCostPerSquareFoot());
        assertEquals(BigDecimal.valueOf(950.00).setScale(2), processedOrder.getCalculatedLaborCost().setScale(2));
        assertEquals(BigDecimal.valueOf(680.00).setScale(2), processedOrder.getCalculatedMaterialCost().setScale(2));
        assertEquals(BigDecimal.valueOf(52.16).setScale(2), processedOrder.getCalculatedTaxAmount().setScale(2));
        assertEquals(BigDecimal.valueOf(1682.16).setScale(2), processedOrder.getTotalOrderAmount().setScale(2));

    }

    @Test
    public void retrieveTaxes() {
    }

    @Test
    public void retrieveProducts() {
    }

    @Test
    public void setMode() {
    }
    //..
}