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

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Add Money");
        io.print("2. Make a purchase");
        io.print("3. Get change");
        io.print("4. Exit");

        return (io.readInt("Enter your selection: "));
    }

    public BigDecimal promptAddMoney() {
        return new BigDecimal("0");
    }

    public int promptItemSelection() {
        return 0;
    }

    public void displayAllItems(List<Item> itemList) {
    }

    public void displayChange(Change change) {

    }

}
