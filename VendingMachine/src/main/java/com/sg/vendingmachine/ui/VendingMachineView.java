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
        io.print("Welcome to Snack Dispenser 23!");
        io.print("Menu of selections: ");
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Add Money");
        io.print("2. Make a purchase");
        io.print("3. Get change");
        io.print("4. Exit");

        return (io.readInt("Enter your selection: "));
    }

    public void printAddMoneyBanner() {
        io.print("===Add Money===");
    }

    public BigDecimal promptAddMoney() {
        return io.readBigDecimal("Please enter the amount: ");

    }

    public void displayCurrentBalance(BigDecimal balance) {
        io.print("Your current balance is: " + balance.toString());
    }

    public int promptItemSelection() {
        return io.readInt("Please enter the item number of your selection: ");

    }

    public void purchaseItemBanner() {
        io.print("===Purchase an Item===");
    }

    public void itemPurchasedBanner() {
        io.print("Enjoy!");
    }

    public void displayAllItems(List<Item> itemList) {
        for (Item currentItem : itemList) {
            io.print(currentItem.getItemID() + " " + currentItem.getItemName() + " " +
            currentItem.getItemPrice());
        }
    }

    public void displayChange(Change change) {

    }

    public void displayErrorMessage(String message) {
        io.print(message);
    }
}
