package com.sg.flooringmastery.ui;

import java.time.LocalDate;

public class FlooringMasteryView {

    UserIO userIO;

    public FlooringMasteryView(UserIO userIO) {
        this.userIO = userIO;
    }

    public LocalDate getInputDate() {
        return userIO.readLocalDate("Enter the date: ");
    }
}
