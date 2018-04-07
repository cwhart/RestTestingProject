package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachineView {

    private UserIo io;

    public VendingMachineView(UserIo io) {
        this.io = io;
    }

    public void printWelcomeMessage() {
        io.print("\n===WELCOME TO SNACK DISPENSER 23!===\n");
        io.print("======Menu of selections=======");
    }

    public int printMenuAndGetSelection() {
        io.print("=========================");
        io.print("========Main Menu========");
        io.print("=========================");
        io.print("1. Add Money");
        io.print("2. Make a purchase");
        io.print("3. Get change");
        io.print("4. Exit");

        return (io.readInt("Enter your selection: "));
    }

    public void printAddMoneyBanner() {
        io.print("===ADD MONEY===");
    }

    public BigDecimal promptAddMoney() {
        return io.readBigDecimal("Please enter the amount: ");

    }

    public void displayCurrentBalance(BigDecimal balance) {
        io.print("\nYour current balance is: $" + balance.setScale(2) + "\n");
    }

    public int promptItemSelection() {

        return io.readInt("Please enter the item number of your selection: ");

    }

    public void purchaseItemBanner() {
        io.print("\n===PURCHASE AN ITEM===");
    }

    public void itemPurchasedBanner() {
        io.print("\nEnjoy!\n");
    }

    public void displayAllItems(List<Item> itemList) {
        for (Item currentItem : itemList) {
            io.print(currentItem.getItemID() + ". " + currentItem.getItemName() + " - $" +
            currentItem.getItemPrice().setScale(2));
        }
    }

    public void displayChange(Change change) {
        io.print("\nYour change: ");
        io.print(change.getQuarters() + " Quarters");
        io.print(change.getDimes() + " Dimes");
        io.print(change.getNickels() + " Nickels");
        io.print(change.getPennies() + " Pennies\n");

    }

    public void displayErrorMessage(String message) {
        io.print(message);
    }

    public void displayUnknownCommandBanner() {
        io.print("===ERROR: UNKNOWN COMMAND===");
    }
}
