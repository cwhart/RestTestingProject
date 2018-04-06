package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import com.sg.vendingmachine.ui.VendingMachineView;

import javax.swing.text.View;
import javax.xml.ws.Service;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.util.List;

public class VendingMachineController {

    VendingMachineServiceLayer vendingMachineService;
    VendingMachineView vendingMachineView;

    public void run() {

        vendingMachineView.printWelcomeMessage();

        try {
            listAllItems();
        } catch (VendingMachinePersistenceException e) {
            vendingMachineView.displayErrorMessage(e.getMessage());
        }

        boolean keepGoing = true;

            do {

                int selection = vendingMachineView.printMenuAndGetSelection();

                switch (selection) {

                    case 1:
                        addMoney();
                        break;
                    case 2:
                        System.out.println("Make a purchase");
                        break;
                    case 3:
                        System.out.println("Get change");
                        break;
                    case 4:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            } while (keepGoing);


    }

    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view){
        this.vendingMachineService = service;
        this.vendingMachineView = view;
    }

    private int getMenuSelection() {
        return 0;
    }

    private void listAllItems() throws VendingMachinePersistenceException{
        List<Item> itemList = vendingMachineService.retrieveListAll();
        vendingMachineView.displayAllItems(itemList);

    }

    private void purchaseItem() {

    }

    private void unknownCommand() {

    }

    private void addMoney() {
        vendingMachineView.printAddMoneyBanner();
        BigDecimal amountToAdd = vendingMachineView.promptAddMoney();
        vendingMachineService.addMoney(amountToAdd);
        BigDecimal balance = vendingMachineService.getRunningTotal();
        vendingMachineView.displayCurrentBalance(balance);

    }

    private void returnChange() {

    }

    private void exit() {

    }
}
