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

        //List all available items every time the menu is displayed.
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
                        //Before exiting, return any extra change the user may have.
                        returnChange();
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
        //Call the service to get the current balance.
        //Display balance to the user
        //Then get the user's selection.
        BigDecimal balance = vendingMachineService.getRunningTotal();
        vendingMachineView.displayCurrentBalance(balance);
        return vendingMachineView.printMenuAndGetSelection();
    }

    private void listAllItems() throws VendingMachinePersistenceException{
        //List all available items.
        List<Item> itemList = vendingMachineService.retrieveListAll();
        vendingMachineView.displayAllItems(itemList);

    }

    private void purchaseItem() throws VendingMachinePersistenceException{

        vendingMachineView.purchaseItemBanner();

        boolean tryAgain = false;

        do {

            //List available items
            listAllItems();
            //Get user's selection
            int selectionId = vendingMachineView.promptItemSelection();

            try {
                //Need to set tryAgain back to false here, otherwise if user has insufficient funds, it will
                //keep looping on the item menu instead of going back to the main menu.
                tryAgain = false;
                //Purchase the item, then display the success banner.
                //handle exceptions thrown due to insufficient funds or quantity.
                vendingMachineService.purchaseItem(selectionId);
                vendingMachineView.itemPurchasedBanner();
            } catch (InsufficientFundsException e) {
                vendingMachineView.displayErrorMessage(e.getMessage());

            } catch (InsufficientItemQuantityException e) {
                vendingMachineView.displayErrorMessage(e.getMessage());
                //If there is insufficient quantity for a selected item, prompt the user to select
                //another item.
                tryAgain = true;
            }
        } while (tryAgain == true);
    }

    private void unknownCommand() {
        vendingMachineView.displayUnknownCommandBanner();
    }

    private void addMoney() {
        vendingMachineView.printAddMoneyBanner();

        //Call the view to prompt user for the amount.
        BigDecimal amountToAdd = vendingMachineView.promptAddMoney();
        //Pass the amount to the service to add to the current balance.
        vendingMachineService.addMoney(amountToAdd);

    }

    private void returnChange() {
        //Call the service to calculate change, then pass to the view to display.
        Change changeToReturn = vendingMachineService.calculateChange();
        vendingMachineView.displayChange(changeToReturn);

    }

    private void exit() {

    }
}
