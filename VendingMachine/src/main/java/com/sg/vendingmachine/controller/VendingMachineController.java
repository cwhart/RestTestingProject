package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.service.InsufficientFundsException;
import com.sg.vendingmachine.service.InsufficientItemQuantityException;
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

        boolean keepGoing = true;
        listAllItems();


            while (keepGoing) {
                int selection = getMenuSelection();

                switch (selection) {

                    case 1:
                        addMoney();
                        break;
                    case 2:
                        purchaseItem();
                        break;
                    case 3:
                        returnChange();
                        break;
                    case 4:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }

        } catch (VendingMachinePersistenceException e) {
            vendingMachineView.displayErrorMessage(e.getMessage());
        }


    }

    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view){
        this.vendingMachineService = service;
        this.vendingMachineView = view;
    }

    private int getMenuSelection() {
        BigDecimal balance = vendingMachineService.getRunningTotal();
        vendingMachineView.displayCurrentBalance(balance);
        return vendingMachineView.printMenuAndGetSelection();
    }

    private void listAllItems() throws VendingMachinePersistenceException{
        List<Item> itemList = vendingMachineService.retrieveListAll();
        vendingMachineView.displayAllItems(itemList);

    }

    private void purchaseItem() throws VendingMachinePersistenceException{
        vendingMachineView.purchaseItemBanner();

        boolean tryAgain = false;

        do {

        listAllItems();
        //retrieve item by ID
        int selectionId = vendingMachineView.promptItemSelection();

        try {
            vendingMachineService.purchaseItem(selectionId);
            vendingMachineView.itemPurchasedBanner();
        } catch (InsufficientFundsException e) {
            vendingMachineView.displayErrorMessage(e.getMessage());

            //Need to set tryAgain back to false here, otherwise if user has insufficient funds, it will
            //keep looping on the item menu instead of going back to the main menu.

            tryAgain = false;
        } catch (InsufficientItemQuantityException e) {
            vendingMachineView.displayErrorMessage(e.getMessage());
            tryAgain = true;
            }
        } while (tryAgain == true);
    }




    private void unknownCommand() {
        vendingMachineView.displayUnknownCommandBanner();
    }

    private void addMoney() {
        vendingMachineView.printAddMoneyBanner();
        BigDecimal amountToAdd = vendingMachineView.promptAddMoney();
        vendingMachineService.addMoney(amountToAdd);

    }

    private void returnChange() {
        Change changeToReturn = vendingMachineService.calculateChange();
        vendingMachineView.displayChange(changeToReturn);

    }

    private void exit() {

    }
}
