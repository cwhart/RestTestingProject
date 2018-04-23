package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.dao.OrderPersistenceException;
import com.sg.flooringmastery.dao.ProductPersistenceException;
import com.sg.flooringmastery.dao.TaxPersistenceException;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.service.ServiceLayerImpl;
import com.sg.flooringmastery.ui.FlooringMasteryView;

import java.time.LocalDate;
import java.util.List;

public class FlooringMasteryController {

    ServiceLayerImpl service;
    FlooringMasteryView view;

    public FlooringMasteryController(FlooringMasteryView view, ServiceLayerImpl service) {
        this.service = service;
        this.view = view;
    }

    public void run()  {

        try {

            boolean keepGoing = true;

            while (keepGoing) {

                int userSelection = getMenuSelection();

                switch (userSelection) {

                    case 1:
                        displayOrders();
                        break;
                    case 2:
                        addAnOrder();
                        break;
                    case 3:
                        editAnOrder();
                        break;
                    case 4:
                        removeAnOrder();
                        break;
                    case 5:
                        saveCurrentWork();
                        break;
                    case 6:
                        keepGoing = false;

                }
            }
        } catch (OrderPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }

    }

    private int getMenuSelection() {
        return view.displayMenuAndGetSelection();
    }


    private void displayOrders() throws OrderPersistenceException {
        LocalDate dateToDisplay = view.promptUserForDate();
        List<Order> orderList = service.retrieveAllOrdersByDate(dateToDisplay);
        view.displayOrders(orderList);
    }

    private void addAnOrder()throws OrderPersistenceException {
        Order orderToAdd = view.promptUserForOrderInfo();

        try {
            service.processOrder(orderToAdd);

        } catch (TaxPersistenceException | ProductPersistenceException | OrderPersistenceException e) {
          view.displayErrorMessage(e.getMessage());
        }
        boolean confirmAddOrder = view.displayProcessedOrderAndConfirm(orderToAdd);
        if (confirmAddOrder) {
            service.addOrder(orderToAdd);
        }
    }

    private void editAnOrder() throws OrderPersistenceException{
        Order orderToEdit = view.promptUserForOrderNumberAndDate();
        orderToEdit = service.retrieveOrderByDateAndId(orderToEdit.getOrderDate(), orderToEdit.getOrderNumber());
        Order updatedOrder = view.promptUserForUpdatedOrderInfo(orderToEdit);

        try {
            service.processOrder(updatedOrder);

        } catch (TaxPersistenceException | ProductPersistenceException | OrderPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
        boolean confirmAddOrder = view.displayProcessedOrderAndConfirm(updatedOrder);
        if (confirmAddOrder) {
            service.updateOrder(updatedOrder);
        }

    }

    private void removeAnOrder() throws OrderPersistenceException {
        Order orderToRemove = view.promptUserForOrderNumberAndDate();
        orderToRemove = service.retrieveOrderByDateAndId(orderToRemove.getOrderDate(), orderToRemove.getOrderNumber());

        try {
            service.removeOrder(orderToRemove);
        } catch (OrderPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void saveCurrentWork() throws OrderPersistenceException{
        service.saveCurrentWork();
    }
}
