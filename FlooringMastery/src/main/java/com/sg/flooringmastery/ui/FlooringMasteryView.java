package com.sg.flooringmastery.ui;

import com.sg.flooringmastery.dto.Order;

import java.time.LocalDate;
import java.util.List;

public class FlooringMasteryView {

    UserIO userIO;

    public FlooringMasteryView(UserIO userIO) {
        this.userIO = userIO;
    }

    public int displayMenuAndGetSelection() {

        userIO.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        userIO.print("* 1. Display Orders");
        userIO.print("* 2. Add an Order");
        userIO.print("* 3. Edit an Order");
        userIO.print("* 4. Remove an Order");
        userIO.print("* 5. Save Current Work");
        userIO.print("* 6. Quit");
        userIO.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");

        return userIO.readInt("Please enter your selection: ");

    }

    public LocalDate promptUserForDate() {
        return userIO.readLocalDate("Please enter the date you wish to search on in YYYY-MM-DD format: ");
    }

    public void displayOrders(List<Order> orderList) {
        for (Order currentOrder : orderList) {
            userIO.print(currentOrder.toString());
        }
    }

    public void displayErrorMessage(String message) {
        userIO.print(message);
    }
}
