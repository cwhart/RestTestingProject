package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.*;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class ServiceLayerImpl implements ServiceLayer {

    private TaxDao taxDao;
    private ProductDao productDao;
    private OrderDao orderDao;
    private String mode = "Production";

    public ServiceLayerImpl(TaxDao taxDao, ProductDao productDao, OrderDao orderDao) {
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.taxDao = taxDao;
    }

    @Override
    public List<Order> retrieveAllOrdersByDate(LocalDate date) throws OrderPersistenceException {
        return orderDao.retrieveOrdersByDate(date);
    }

    @Override
    public Order addOrder(Order orderToAdd) throws OrderPersistenceException{
        return orderDao.createOrder(orderToAdd);
    }

    @Override
    public void saveCurrentWork() throws OrderPersistenceException {
        if (mode.equals("Production")) {
            orderDao.save();
        }
    }

    @Override
    public Order updateOrder(Order orderToUpdate)throws OrderPersistenceException {
        if (validateOrderId(orderToUpdate.getOrderDate(), orderToUpdate.getOrderNumber())==true) {
            return orderDao.updateOrder(orderToUpdate);
        } else throw new OrderPersistenceException("ERROR: Order does not exist.") ;

    }

    @Override
    public void removeOrder(Order orderToRemove) throws OrderPersistenceException {
        if (validateOrderId(orderToRemove.getOrderDate(), orderToRemove.getOrderNumber())){
            orderDao.removeOrder(orderToRemove);
        } else throw new OrderPersistenceException("ERROR: Order does not exist.");
    }

    @Override
    public Order retrieveOrderByDateAndId(LocalDate date, int orderId) throws OrderPersistenceException{
        if(validateOrderId(date, orderId)) {
            return orderDao.retrieveOrderByDateAndId(date, orderId);
        } else throw new OrderPersistenceException("ERROR: Order does not exist.");
    }

    @Override
    public Order processOrder(Order orderToProcess) throws TaxPersistenceException, ProductPersistenceException,
            OrderPersistenceException{
        //Call the 4 calculate methods to set the 4 calculated fields.
        //Fields that will be user entered: customer lastname, state, product type, area.
        //Fields to be populated by this method: Tax rate, material cost per square foot,
        //labor cost per square foot, plus the 4 calculated fields.
        //TODO: implement negative tests to verify the exception conditions.

        if(!validateProductExists(orderToProcess)) {
            throw new ProductPersistenceException("ERROR: Product does not exist.");
        }

        if(!validateStateExists(orderToProcess)) {
            throw new TaxPersistenceException("ERROR: State does not exist.");
        }

        orderToProcess.setOrderTax(taxDao.retrieveTax(orderToProcess.getState()));
        orderToProcess.setOrderProduct(productDao.retrieveProduct(orderToProcess.getOrderProduct().getProductType()));

        orderToProcess.setCalculatedLaborCost(orderToProcess.getCalculatedLaborCost());
        orderToProcess.setCalculatedMaterialCost(orderToProcess.getCalculatedMaterialCost());
        orderToProcess.setCalculatedTaxAmount(orderToProcess.getCalculatedTaxAmount());
        orderToProcess.setTotalOrderAmount(orderToProcess.getTotalOrderAmount());

        return orderToProcess;
    }

    @Override
    public List<Tax> retrieveTaxes() throws TaxPersistenceException {
        return taxDao.retrieveAllTaxes();
    }

    @Override
    public List<Product> retrieveProducts() throws ProductPersistenceException {
        return productDao.retrieveAllProducts();
    }

    @Override
    public void setMode(boolean mode) {

        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        if (mode) {
            this.orderDao =
                    ctx.getBean("orderDao", OrderDaoFileImpl.class);
        } else {
            this.orderDao =
                    ctx.getBean("testOrderDao", OrderDaoTestFileImpl.class);
        }

    }

    private boolean validateProductExists(Order orderToValidate) throws OrderPersistenceException, ProductPersistenceException {
        String productToValidate = orderToValidate.getOrderProduct().getProductType();
        if(productDao.retrieveProduct(productToValidate) == null) {
            return false;
        } else return true;
    }

    private boolean validateStateExists(Order orderToValidate) throws TaxPersistenceException {
        String stateToValidate = orderToValidate.getState();
        if(taxDao.retrieveTax(stateToValidate) == null) {
            return false;
        } else return true;
    }

    private boolean validateOrderId(LocalDate localDate, int orderId) throws OrderPersistenceException {
        if (orderDao.retrieveOrderByDateAndId(localDate, orderId) == null) {

            return false;
        } return true;
    }

    //..
}
