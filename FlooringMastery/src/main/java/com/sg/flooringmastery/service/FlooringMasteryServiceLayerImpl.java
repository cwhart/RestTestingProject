package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.*;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class FlooringMasteryServiceLayerImpl implements FlooringMasteryServiceLayer{

    private TaxDao taxDao;
    private ProductDao productDao;
    private OrderDao orderDao;
    private String mode;

    public FlooringMasteryServiceLayerImpl(TaxDao taxDao, ProductDao productDao, OrderDao orderDao) {
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.taxDao = taxDao;
    }

    @Override
    public List<Order> retrieveAllOrdersByDate(LocalDate date) throws OrderPersistenceException {
        return orderDao.retrieveOrdersByDate(date);
    }

    @Override
    public Order addOrder(Order orderToAdd) throws Exception{
        return orderDao.createOrder(orderToAdd);
    }

    @Override
    public void saveCurrentWork(Map<Integer, Order> orderMap) throws OrderPersistenceException {
        orderDao.save();
    }

    @Override
    public Order updateOrder(Order orderToUpdate)throws OrderPersistenceException {
        return orderDao.updateOrder(orderToUpdate);
    }

    @Override
    public void removeOrder(Order orderToRemove) throws OrderPersistenceException {
        orderDao.removeOrder(orderToRemove);
    }

    @Override
    public Order retrieveOrderByDateAndId(LocalDate date, int orderId) throws OrderPersistenceException{
        return orderDao.retrieveOrderByDateAndId(date, orderId);
    }

    @Override
    public Order processOrder(Order orderToProcess) throws TaxPersistenceException, ProductPersistenceException {
        //Call the 4 calculate methods to set the 4 calculated fields.
        //Fields that will be user entered: customer lastname, state, product type, area.
        //Fields to be populated by this method: Tax rate, material cost per square foot,
        //labor cost per square foot, plus the 4 calculated fields.

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
    public void setMode(OrderDao orderDao) {


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

    private boolean validateOrderExists(Order order) throws OrderPersistenceException {
        if (orderDao.retrieveOrderByDateAndId(order.getOrderDate(),
                order.getOrderNumber()) == null) {
            return false;
        } else return true;
    }
}
