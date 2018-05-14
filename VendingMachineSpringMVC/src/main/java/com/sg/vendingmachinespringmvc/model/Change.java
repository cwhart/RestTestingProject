package com.sg.vendingmachinespringmvc.model;

import java.math.BigDecimal;

public class Change {

    private int quarters;
    private int dimes;
    private int nickels;
    private int pennies;
    private int dollars;

    public Change() {

    }

    public Change(BigDecimal changeTotal) {
        int totalBalance = (changeTotal.multiply(BigDecimal.valueOf(100))).intValue();
        quarters = totalBalance / 25;
        totalBalance = totalBalance % 25;
        dimes = totalBalance / 10;
        totalBalance = totalBalance % 10;
        nickels = totalBalance / 5;
        pennies = totalBalance % 5;
    }

    public int getDollars() {
        return dollars;
    }

    public void setDollars(int dollars) {
        this.dollars = dollars;
    }

    public int getQuarters() {
        return quarters;
    }

    public void setQuarters(int quarters) {
        this.quarters = quarters;
    }

    public int getDimes() {
        return dimes;
    }

    public void setDimes(int dimes) {
        this.dimes = dimes;
    }

    public int getNickels() {
        return nickels;
    }

    public void setNickels(int nickels) {
        this.nickels = nickels;
    }

    public int getPennies() {
        return pennies;
    }

    public void setPennies(int pennies) {
        this.pennies = pennies;
    }

    //

}
