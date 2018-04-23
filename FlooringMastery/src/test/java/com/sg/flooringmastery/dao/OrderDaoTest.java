package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.junit.Assert.*;

public class OrderDaoTest {

    OrderDao dao = new OrderDaoFileImpl();

    @Before
    public void setUp() throws Exception {
        //List<Order> orderList = new ArrayList<>();
        List<LocalDate> listOfTestingDates = new ArrayList<>();
        //Copy the following line and change the date for every date used in testing.
        listOfTestingDates.add(LocalDate.parse("2018-04-17"));
        listOfTestingDates.add(LocalDate.parse("2018-01-01"));
        listOfTestingDates.add(LocalDate.parse("2018-01-04"));
        listOfTestingDates.add(LocalDate.parse("2016-10-02"));
        listOfTestingDates.add(LocalDate.parse("2017-06-14"));
        listOfTestingDates.add(LocalDate.parse("2018-04-19"));
        for(LocalDate currentDate : listOfTestingDates) {
            for (Order currentOrder : dao.retrieveOrdersByDate(currentDate)) {
                dao.removeOrder(currentOrder);
            }
        }
    }

    @Test
    public void testCreateAndRetrieveOrder() throws OrderPersistenceException, Exception {
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
        Order addedOrder = dao.createOrder(order1);
        Order retrievedOrder = dao.retrieveOrderByDateAndId(order1.getOrderDate(), order1.getOrderNumber());

        assertEquals(order1, addedOrder);
        assertEquals(order1, retrievedOrder);

    }

    @Test
    public void testCreateWithNewDate() throws OrderPersistenceException, Exception {
        Order order1 = new Order(5);
        order1.setCustomerLastName("Smith1");
        order1.setOrderDate(LocalDate.parse("2018-04-18"));
        order1.setState("NJ");
        order1.setOrderTax(new Tax("NJ", BigDecimal.valueOf(4.42)));
        order1.setOrderProduct(new Product("Pine", BigDecimal.valueOf(5.25), BigDecimal.valueOf(3.55)));
        order1.setArea(BigDecimal.valueOf(250));
        order1.setCalculatedMaterialCost(BigDecimal.valueOf(1312.5));
        order1.setCalculatedLaborCost(BigDecimal.valueOf(887.5));
        order1.setCalculatedTaxAmount(BigDecimal.valueOf(92.4));
        order1.setTotalOrderAmount(BigDecimal.valueOf(2292.4));
        Order addedOrder = dao.createOrder(order1);
        Order retrievedOrder = dao.retrieveOrderByDateAndId(order1.getOrderDate(), order1.getOrderNumber());

        assertEquals(order1, addedOrder);
        assertEquals(order1, retrievedOrder);

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

        Order retrievedOrder = dao.retrieveOrderByDateAndId(order1.getOrderDate(), order1.getOrderNumber());

        assertEquals(addedOrder, retrievedOrder);

    }

    @Test (expected = OrderPersistenceException.class)
    public void testRetrieveOrderByDateAndIdDateDoesntExist() throws OrderPersistenceException, Exception {
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

        Order retrievedOrder = dao.retrieveOrderByDateAndId(LocalDate.parse("2017-06-15"), order1.getOrderNumber());

        assertNull(retrievedOrder);

    }

    @Test (expected = OrderPersistenceException.class)
    public void testRetrieveOrderByDateAndIdIdDoesntExist() throws OrderPersistenceException, Exception {
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
        int wrongOrderNum = addedOrder.getOrderNumber() + 5;

        Order retrievedOrder = dao.retrieveOrderByDateAndId(order1.getOrderDate(), wrongOrderNum);

        assertNull(retrievedOrder);

    }

    @Test
    public void testRetrieveOrdersByDate() throws OrderPersistenceException, Exception{
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
        dao.createOrder(order1);
        List<Order> orderListForGivenDate = dao.retrieveOrdersByDate(LocalDate.parse("2018-04-17"));
        assertEquals(1, orderListForGivenDate.size());

        Order order2 = new Order(6);
        order2.setCustomerLastName("Smith2");
        order2.setOrderDate(LocalDate.parse("2018-04-17"));
        order2.setState("NJ");
        order2.setOrderTax(new Tax("NJ", BigDecimal.valueOf(4.42)));
        order2.setOrderProduct(new Product("Pine", BigDecimal.valueOf(5.25), BigDecimal.valueOf(3.55)));
        order2.setArea(BigDecimal.valueOf(250));
        order2.setCalculatedMaterialCost(BigDecimal.valueOf(1312.5));
        order2.setCalculatedLaborCost(BigDecimal.valueOf(887.5));
        order2.setCalculatedTaxAmount(BigDecimal.valueOf(92.4));
        order2.setTotalOrderAmount(BigDecimal.valueOf(2292.4));
        dao.createOrder(order2);
        orderListForGivenDate = dao.retrieveOrdersByDate(LocalDate.parse("2018-04-17"));
        assertEquals(2, orderListForGivenDate.size());

        Order order3 = new Order(7);
        order3.setCustomerLastName("Smith2");
        order3.setOrderDate(LocalDate.parse("2018-05-17"));
        order3.setState("NJ");
        order3.setOrderTax(new Tax("NJ", BigDecimal.valueOf(4.42)));
        order3.setOrderProduct(new Product("Pine", BigDecimal.valueOf(5.25), BigDecimal.valueOf(3.55)));
        order3.setArea(BigDecimal.valueOf(250));
        order3.setCalculatedMaterialCost(BigDecimal.valueOf(1312.5));
        order3.setCalculatedLaborCost(BigDecimal.valueOf(887.5));
        order3.setCalculatedTaxAmount(BigDecimal.valueOf(92.4));
        order3.setTotalOrderAmount(BigDecimal.valueOf(2292.4));
        dao.createOrder(order3);
        orderListForGivenDate = dao.retrieveOrdersByDate(LocalDate.parse("2018-04-17"));
        assertEquals(2, orderListForGivenDate.size());

    }

    @Test
    public void testRetrieveOrdersByDateDoesNotExist() throws OrderPersistenceException, Exception{
        List<Order> orderListForGivenDate = new ArrayList<>();
        orderListForGivenDate = dao.retrieveOrdersByDate(LocalDate.parse("2010-01-01"));

        for (Order currentOrder : orderListForGivenDate) {
            assertNull(currentOrder);
        }
    }

    @Test
    public void testUpdateOrderHappyPath() throws OrderPersistenceException, Exception {

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
        dao.createOrder(order1);

        Order order2 = new Order(5);
        order2.setOrderNumber(order1.getOrderNumber());
        order2.setCustomerLastName("Smith2");
        order2.setOrderDate(LocalDate.parse("2018-04-17"));
        order2.setState("WA");
        order2.setOrderTax(new Tax("WA", BigDecimal.valueOf(5.42)));
        order2.setOrderProduct(new Product("Pine", BigDecimal.valueOf(5.25), BigDecimal.valueOf(3.55)));
        order2.setArea(BigDecimal.valueOf(250));
        order2.setCalculatedMaterialCost(BigDecimal.valueOf(1312.5));
        order2.setCalculatedLaborCost(BigDecimal.valueOf(887.5));
        order2.setCalculatedTaxAmount(BigDecimal.valueOf(92.4));
        order2.setTotalOrderAmount(BigDecimal.valueOf(2292.4));
        dao.updateOrder(order2);

        Order updatedOrder = dao.retrieveOrderByDateAndId(order2.getOrderDate(), order2.getOrderNumber());

        assertEquals(order2, updatedOrder);
    }

    @Test (expected = OrderPersistenceException.class)
    public void testUpdateOrderDoesNotExist() throws OrderPersistenceException, Exception{

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
        dao.createOrder(order1);

        Order order2 = new Order(5);
        order2.setCustomerLastName("Smith2");
        order2.setOrderDate(LocalDate.parse("2018-04-17"));
        order2.setState("WA");
        order2.setOrderTax(new Tax("WA", BigDecimal.valueOf(5.42)));
        order2.setOrderProduct(new Product("Pine", BigDecimal.valueOf(5.25), BigDecimal.valueOf(3.55)));
        order2.setArea(BigDecimal.valueOf(250));
        order2.setCalculatedMaterialCost(BigDecimal.valueOf(1312.5));
        order2.setCalculatedLaborCost(BigDecimal.valueOf(887.5));
        order2.setCalculatedTaxAmount(BigDecimal.valueOf(92.4));
        order2.setTotalOrderAmount(BigDecimal.valueOf(2292.4));
        order2.setOrderNumber(order1.getOrderNumber() + 5);
        dao.updateOrder(order2);

        Order updatedOrder = dao.retrieveOrderByDateAndId(order2.getOrderDate(), order2.getOrderNumber());

        assertNull(updatedOrder);

    }

    @Test (expected = OrderPersistenceException.class)
    public void testUpdateOrderWrongDate() throws OrderPersistenceException, Exception{

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
        dao.createOrder(order1);

        Order order2 = new Order(5);
        order2.setOrderNumber(order1.getOrderNumber());
        order2.setCustomerLastName("Smith2");
        order2.setOrderDate(LocalDate.parse("2018-06-17"));
        order2.setState("WA");
        order2.setOrderTax(new Tax("WA", BigDecimal.valueOf(5.42)));
        order2.setOrderProduct(new Product("Pine", BigDecimal.valueOf(5.25), BigDecimal.valueOf(3.55)));
        order2.setArea(BigDecimal.valueOf(250));
        order2.setCalculatedMaterialCost(BigDecimal.valueOf(1312.5));
        order2.setCalculatedLaborCost(BigDecimal.valueOf(887.5));
        order2.setCalculatedTaxAmount(BigDecimal.valueOf(92.4));
        order2.setTotalOrderAmount(BigDecimal.valueOf(2292.4));
        dao.updateOrder(order2);

        Order updatedOrder = dao.retrieveOrderByDateAndId(order2.getOrderDate(), order2.getOrderNumber());

        assertNull(updatedOrder);

    }

    @Test
    public void testRemoveOrderHappyPath() throws OrderPersistenceException, Exception{

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
        dao.createOrder(order1);

        assertEquals(1, dao.retrieveOrdersByDate(order1.getOrderDate()).size());

        int orderNum = order1.getOrderNumber();
        dao.removeOrder(order1);

        assertEquals(0, dao.retrieveOrdersByDate(order1.getOrderDate()).size());
    }

    @Test (expected = OrderPersistenceException.class)
    public void testRemoveOrderDoesNotExist() throws OrderPersistenceException, Exception{

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
        dao.createOrder(order1);

        assertEquals(1, dao.retrieveOrdersByDate(order1.getOrderDate()).size());

        order1.setOrderNumber(order1.getOrderNumber() + 5); //Set the order number to a different one so it won't be found.
        dao.removeOrder(order1);

        assertEquals(1, dao.retrieveOrdersByDate(order1.getOrderDate()).size());

    }

    @Test (expected = OrderPersistenceException.class)
    public void testRemoveOrderWrongDate() throws OrderPersistenceException, Exception{

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
        dao.createOrder(order1);

        assertEquals(1, dao.retrieveOrdersByDate(order1.getOrderDate()).size());

        order1.setOrderDate(LocalDate.parse("2018-01-01")); //Change the date so the order won't be found.
        dao.removeOrder(order1);

        assertEquals(1, dao.retrieveOrdersByDate(order1.getOrderDate()).size());

    }

    @Test
    public void testSave() throws OrderPersistenceException, Exception {
        Order order1 = new Order(1);
        order1.setCustomerLastName("Smith1");
        order1.setOrderDate(LocalDate.parse("2018-01-01"));
        order1.setState("NJ");
        order1.setOrderTax(new Tax("NJ", BigDecimal.valueOf(4.42)));
        order1.setOrderProduct(new Product("Pine", BigDecimal.valueOf(5.25), BigDecimal.valueOf(3.55)));
        order1.setArea(BigDecimal.valueOf(250));
        order1.setCalculatedMaterialCost(BigDecimal.valueOf(1312.5));
        order1.setCalculatedLaborCost(BigDecimal.valueOf(887.5));
        order1.setCalculatedTaxAmount(BigDecimal.valueOf(92.4));
        order1.setTotalOrderAmount(BigDecimal.valueOf(2292.4));
        dao.createOrder(order1);
        int order1Id = order1.getOrderNumber();

        assertEquals(1, dao.retrieveOrdersByDate(LocalDate.parse("2018-01-01")).size());

        dao.save();

        Order retrievedOrder = dao.retrieveOrderByDateAndId(LocalDate.parse("2018-01-01"), order1Id);

        assertEquals(order1, retrievedOrder);

    }

    @Test
    public void testSaveMultipleOrdersDates() throws OrderPersistenceException, Exception {

        Order order1 = new Order(1);
        order1.setCustomerLastName("Smith1");
        order1.setOrderDate(LocalDate.parse("2018-01-01"));
        order1.setState("NJ");
        order1.setOrderTax(new Tax("NJ", BigDecimal.valueOf(4.42)));
        order1.setOrderProduct(new Product("Pine", BigDecimal.valueOf(5.25), BigDecimal.valueOf(3.55)));
        order1.setArea(BigDecimal.valueOf(250));
        order1.setCalculatedMaterialCost(BigDecimal.valueOf(1312.5));
        order1.setCalculatedLaborCost(BigDecimal.valueOf(887.5));
        order1.setCalculatedTaxAmount(BigDecimal.valueOf(92.4));
        order1.setTotalOrderAmount(BigDecimal.valueOf(2292.4));
        dao.createOrder(order1);
        int order1Num = order1.getOrderNumber();

        assertEquals(1, dao.retrieveOrdersByDate(LocalDate.parse("2018-01-01")).size());

        Order order2 = new Order(1);
        order2.setCustomerLastName("Smith2");
        order2.setOrderDate(LocalDate.parse("2018-01-01"));
        order2.setState("NJ");
        order2.setOrderTax(new Tax("NJ", BigDecimal.valueOf(4.42)));
        order2.setOrderProduct(new Product("Pine", BigDecimal.valueOf(5.25), BigDecimal.valueOf(3.55)));
        order2.setArea(BigDecimal.valueOf(250));
        order2.setCalculatedMaterialCost(BigDecimal.valueOf(1312.5));
        order2.setCalculatedLaborCost(BigDecimal.valueOf(887.5));
        order2.setCalculatedTaxAmount(BigDecimal.valueOf(92.4));
        order2.setTotalOrderAmount(BigDecimal.valueOf(2292.4));
        dao.createOrder(order2);
        int order2Num = order2.getOrderNumber();

        assertEquals(2, dao.retrieveOrdersByDate(LocalDate.parse("2018-01-01")).size());

        Order order3 = new Order(1);
        order3.setCustomerLastName("Smith3");
        order3.setOrderDate(LocalDate.parse("2018-01-04"));
        order3.setState("NJ");
        order3.setOrderTax(new Tax("NJ", BigDecimal.valueOf(4.42)));
        order3.setOrderProduct(new Product("Pine", BigDecimal.valueOf(5.25), BigDecimal.valueOf(3.55)));
        order3.setArea(BigDecimal.valueOf(250));
        order3.setCalculatedMaterialCost(BigDecimal.valueOf(1312.5));
        order3.setCalculatedLaborCost(BigDecimal.valueOf(887.5));
        order3.setCalculatedTaxAmount(BigDecimal.valueOf(92.4));
        order3.setTotalOrderAmount(BigDecimal.valueOf(2292.4));
        dao.createOrder(order3);
        int order3Num = order3.getOrderNumber();

        assertEquals(1, dao.retrieveOrdersByDate(LocalDate.parse("2018-01-04")).size());

        Order order4 = new Order(1);
        order4.setCustomerLastName("Smith4");
        order4.setOrderDate(LocalDate.parse("2018-01-04"));
        order4.setState("NJ");
        order4.setOrderTax(new Tax("NJ", BigDecimal.valueOf(4.42)));
        order4.setOrderProduct(new Product("Pine", BigDecimal.valueOf(5.25), BigDecimal.valueOf(3.55)));
        order4.setArea(BigDecimal.valueOf(250));
        order4.setCalculatedMaterialCost(BigDecimal.valueOf(1312.5));
        order4.setCalculatedLaborCost(BigDecimal.valueOf(887.5));
        order4.setCalculatedTaxAmount(BigDecimal.valueOf(92.4));
        order4.setTotalOrderAmount(BigDecimal.valueOf(2292.4));
        dao.createOrder(order4);
        int order4Num = order4.getOrderNumber();

        assertEquals(2, dao.retrieveOrdersByDate(LocalDate.parse("2018-01-04")).size());

        Order order5 = new Order(1);
        order5.setCustomerLastName("Smith5");
        order5.setOrderDate(LocalDate.parse("2016-10-02"));
        order5.setState("NJ");
        order5.setOrderTax(new Tax("NJ", BigDecimal.valueOf(4.42)));
        order5.setOrderProduct(new Product("Pine", BigDecimal.valueOf(5.25), BigDecimal.valueOf(3.55)));
        order5.setArea(BigDecimal.valueOf(250));
        order5.setCalculatedMaterialCost(BigDecimal.valueOf(1312.5));
        order5.setCalculatedLaborCost(BigDecimal.valueOf(887.5));
        order5.setCalculatedTaxAmount(BigDecimal.valueOf(92.4));
        order5.setTotalOrderAmount(BigDecimal.valueOf(2292.4));
        dao.createOrder(order5);
        int order5Num = order5.getOrderNumber();

        assertEquals(1, dao.retrieveOrdersByDate(LocalDate.parse("2016-10-02")).size());

        dao.save();

        Order retrievedOrder1 = dao.retrieveOrderByDateAndId(LocalDate.parse("2018-01-01"), order1Num);
        assertEquals(order1, retrievedOrder1);
        Order retrievedOrder2 = dao.retrieveOrderByDateAndId(LocalDate.parse("2018-01-01"), order2Num);
        assertEquals(order2, retrievedOrder2);
        Order retrievedOrder3 = dao.retrieveOrderByDateAndId(LocalDate.parse("2018-01-04"), order3Num);
        assertEquals(order3, retrievedOrder3);
        Order retrievedOrder4 = dao.retrieveOrderByDateAndId(LocalDate.parse("2018-01-04"), order4Num);
        assertEquals(order4, retrievedOrder4);
        Order retrievedOrder5 = dao.retrieveOrderByDateAndId(LocalDate.parse("2016-10-02"), order5Num);
        assertEquals(order5, retrievedOrder5);

    }
    //..
}