package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.service.IncorrectAdminPasswordException;
import com.sg.vendingmachine.service.InsufficientFundsException;
import com.sg.vendingmachine.service.InsufficientItemQuantityException;
import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import com.sg.vendingmachine.ui.VendingMachineView;

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
            ListAllAvailableItems();


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
                        runAdmin();
                        break;
                    case 5:
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
        vendingMachineView.displayMessage("Your current balance is: " + balance.setScale(2));
        return vendingMachineView.printMenuAndGetSelection();
    }

    private void ListAllAvailableItems() throws VendingMachinePersistenceException{
        //List all available items.
        List<Item> itemList = vendingMachineService.retrieveListAllWithQuantityGTZero();
        vendingMachineView.displayAllItems(itemList);

    }

    private void listAllItems() throws VendingMachinePersistenceException {
        List<Item> itemList = vendingMachineService.retrieveListAll();
        vendingMachineView.displayAllItems(itemList);
    }

    private void purchaseItem() throws VendingMachinePersistenceException{

        vendingMachineView.displayMessage("\n===PURCHASE AN ITEM===");

        boolean tryAgain = false;

        do {

            //List available items
            ListAllAvailableItems();
            //Get user's selection
            int selectionId = vendingMachineView.retrieveItem();

            try {
                //Need to set tryAgain back to false here, otherwise if user has insufficient funds, it will
                //keep looping on the item menu instead of going back to the main menu.
                tryAgain = false;
                //Purchase the item, then display the success banner.
                //handle exceptions thrown due to insufficient funds or quantity.
                vendingMachineService.purchaseItem(selectionId);
                vendingMachineView.displayMessage("\nEnjoy!\n");
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
        vendingMachineView.displayMessage("ERROR: Unknown command.");
    }

    private void addMoney() {
        vendingMachineView.displayMessage("===ADD MONEY===");

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

    private void verifyPassword() throws IncorrectAdminPasswordException{
        String userInputPassword = vendingMachineView.promptForPassword();

        //try {
            vendingMachineService.verifyPassword(userInputPassword);
        //} catch (IncorrectAdminPasswordException e) {
        //    vendingMachineView.displayErrorMessage(e.getMessage());
        //}
    }

    private int getAdminMenuSelection() {
        return vendingMachineView.printAdminMenuAndGetSelection();
    }

    private void runAdmin() throws VendingMachinePersistenceException {
        try {
            verifyPassword();
            boolean moreAdminFunctions = true;

            while (moreAdminFunctions) {
                int adminSelection = getAdminMenuSelection();
                switch (adminSelection) {
                    case 1:
                        restockItem();
                        break;
                    case 2:
                        addNewItem();
                        break;
                    case 3:
                        removeItemFromMachine();
                        break;
                    case 4:
                        updatePriceOfItem();
                        break;
                    case 5:
                        moreAdminFunctions = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
        } catch (IncorrectAdminPasswordException e) {
            vendingMachineView.displayErrorMessage(e.getMessage());
        }

    }

    private void exit() {

    }

    private void restockItem() throws VendingMachinePersistenceException {
        listAllItems();
        int itemToRestock = vendingMachineView.retrieveItem();
        vendingMachineService.restockItem(itemToRestock);
        vendingMachineView.displayMessage("Item quantity has been updated to 10.");
    }

    private void addNewItem() throws VendingMachinePersistenceException {
        listAllItems();
        Item newItem = vendingMachineView.promptUserForNewItem();
        vendingMachineService.addItem(newItem);
        vendingMachineView.displayMessage(newItem.getItemName() + " added Successfully!");
    }

    private void removeItemFromMachine() throws VendingMachinePersistenceException{
        listAllItems();
        int itemNoToRemove = vendingMachineView.retrieveItem();
        String itemName = vendingMachineService.removeItem(itemNoToRemove);
        vendingMachineView.displayMessage(itemName + " has been removed from the machine.");
    }

    private void updatePriceOfItem() throws VendingMachinePersistenceException {
        listAllItems();
        Item itemToUpdatePrice = vendingMachineView.retrieveItemAndNewPrice();
        vendingMachineService.updateItemPrice(itemToUpdatePrice);
        vendingMachineView.displayMessage("The price of your item has been updated to: " + itemToUpdatePrice.getItemPrice()
        .setScale(2));
    }
}
