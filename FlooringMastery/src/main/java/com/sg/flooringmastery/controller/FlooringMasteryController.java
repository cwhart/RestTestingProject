package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.dao.OrderPersistenceException;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.service.FlooringMasteryServiceLayer;
import com.sg.flooringmastery.service.FlooringMasteryServiceLayerImpl;
import com.sg.flooringmastery.ui.FlooringMasteryView;

import java.time.LocalDate;
import java.util.List;

public class FlooringMasteryController {

    FlooringMasteryServiceLayerImpl service;
    FlooringMasteryView view;

    public FlooringMasteryController(FlooringMasteryView view, FlooringMasteryServiceLayerImpl service) {
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

    private void addAnOrder() {
        System.out.println("Add an order");
    }

    private void editAnOrder() {
        System.out.println("Edit an order");
    }

    private void removeAnOrder() {
        System.out.println("Remove an order");
    }

    private void saveCurrentWork() {
        System.out.println("Save work");
    }
}
