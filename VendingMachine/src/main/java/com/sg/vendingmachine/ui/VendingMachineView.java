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
        io.print("4. Admin");
        io.print("5. Exit");

        return (io.readInt("Enter your selection: "));
    }

    public int printAdminMenuAndGetSelection() {
        io.print("=========================");
        io.print("=======Admin Menu========");
        io.print("=========================");
        io.print("1. Restock an item");
        io.print("2. Add an item");
        io.print("3. Remove an item");
        io.print("4. Update price for an item");
        io.print("5. Exit to Main Menu");

        return io.readInt("Enter your selection: ");
    }

    public BigDecimal promptAddMoney() {
        return io.readBigDecimal("Please enter the amount: ");

    }

    public void displayAllItems(List<Item> itemList) {

        //Display item ID, name and price.
        for (Item currentItem : itemList) {
            io.print(currentItem.getItemID() + ". " + currentItem.getItemName() + " - $" +
            currentItem.getItemPrice().setScale(2) + " | " + currentItem.getItemQuantity() + " items in stock.");
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

    public String  promptForPassword() {
        return io.readString("Please enter password to access Admin functions:");
    }

    public int retrieveItem() {
        return io.readInt("Please enter the item number:");
    }

    public Item promptUserForNewItem() {
        Item newItem = new Item(retrieveItem());
        newItem.setItemQuantity(10);
        newItem.setItemName(io.readString("Enter the item name: "));
        newItem.setItemPrice(io.readBigDecimal("Enter the price of the item: "));
        return newItem;
    }

    //Instead of having separate methods for displaying each individual message, this is a
    //generic method to display any message passed in.
    public void displayMessage(String message) {
        io.print(message);
    }

    public Item retrieveItemAndNewPrice() {
        Item itemNo = new Item(retrieveItem());
        itemNo.setItemPrice(io.readBigDecimal("Enter the price of the item: "));
        return itemNo;

    }

    //Adding a comment for commit
}
