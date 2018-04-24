package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.dao.OrderPersistenceException;
import com.sg.flooringmastery.dao.ProductPersistenceException;
import com.sg.flooringmastery.dao.TaxPersistenceException;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
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
            boolean mode = true; //'true' is defined as Production mode.

            while (keepGoing) {


                int userSelection = getMenuSelection(mode);

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
                        mode = switchMode(mode);
                        break;
                    case 7:
                        if (confirmBeforeExiting()) {
                            keepGoing = false;
                        }
                        break;
                    default:
                        view.displayErrorMessage("ERROR: Invalid selection");

                }
            }
        } catch (OrderPersistenceException | TaxPersistenceException | ProductPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }

    }

    private int getMenuSelection(boolean mode) {
        return view.displayMenuAndGetSelection(mode);
    }


    private void displayOrders() throws OrderPersistenceException {
        LocalDate dateToDisplay = view.promptUserForDate();
        List<Order> orderList = service.retrieveAllOrdersByDate(dateToDisplay);
        view.displayOrders(orderList, dateToDisplay);
    }

    private void addAnOrder()throws OrderPersistenceException, ProductPersistenceException, TaxPersistenceException {

        List <Tax> taxes = service.retrieveTaxes();
        List <Product> products = service.retrieveProducts();
        boolean tryAgain = true;
        Order orderToAdd = new Order(1);

        do {
            try {
                orderToAdd = view.promptUserForOrderInfo(products, taxes);
                service.processOrder(orderToAdd);
                tryAgain = false;

            } catch (OrderPersistenceException e) {
                view.displayErrorMessage(e.getMessage());
            } catch (ProductPersistenceException e) {
                view.displayErrorMessage("ERROR: Invalid product selection, please try again.");
            } catch (TaxPersistenceException e) {
                view.displayErrorMessage("ERROR: Invalid state selection, please try again.");
            }
        } while (tryAgain == true);

        boolean confirmAddOrder = view.displayProcessedOrderAndConfirm(orderToAdd);
        if (confirmAddOrder) {
            service.addOrder(orderToAdd);
            view.displayAddOrderConfirmation(orderToAdd);
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
            view.displayEditOrderConfirmation(updatedOrder);
        }

    }

    private void removeAnOrder() throws OrderPersistenceException {
        Order orderToRemove = view.promptUserForOrderNumberAndDate();
        orderToRemove = service.retrieveOrderByDateAndId(orderToRemove.getOrderDate(), orderToRemove.getOrderNumber());

        try {
            service.removeOrder(orderToRemove);
            view.displayRemoveOrderConfirmation(orderToRemove);
        } catch (OrderPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void saveCurrentWork() throws OrderPersistenceException{
        service.saveCurrentWork();
        view.displaySaveSuccessMessage();
    }

    private boolean confirmBeforeExiting() {
        return view.promptForConfirmation();

    }

    private boolean switchMode (boolean mode) {
        mode = view.displaySwitchMode(mode);
        service.setMode(mode);
        return mode;
    }


}
