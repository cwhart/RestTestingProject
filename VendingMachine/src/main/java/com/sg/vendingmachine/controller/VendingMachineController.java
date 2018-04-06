package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import com.sg.vendingmachine.ui.VendingMachineView;

import javax.swing.text.View;
import javax.xml.ws.Service;

public class VendingMachineController {

    VendingMachineServiceLayer vendingMachineService;
    VendingMachineView vendingMachineView;

    public void run() {

        boolean keepGoing = true;

        do {

            int selection = vendingMachineView.printMenuAndGetSelection();

            switch (selection) {

                case 1:
                    System.out.println("Add Money");
                    break;
                case 2:
                    System.out.println("Make a purchase");
                    break;
                case 3:
                    System.out.println("Get change");
                    break;
                case 4:
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
                    break;
            }

        } while (keepGoing);


    }

    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view){
        this.vendingMachineService = service;
        this.vendingMachineView = view;
    }

    private int getMenuSelection() {
        return 0;
    }

    private void listAllItems() {

    }

    private void purchaseItem() {

    }

    private void unknownCommand() {

    }

    private void addMoney() {

    }

    private void returnChange() {

    }

    private void exit() {

    }
}
