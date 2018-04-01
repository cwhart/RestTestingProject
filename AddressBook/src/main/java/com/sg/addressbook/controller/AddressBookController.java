package com.sg.addressbook.controller;

import com.sg.addressbook.dao.AddressBookDao;
import com.sg.addressbook.dao.AddressBookDaoFileImpl;
import com.sg.addressbook.dto.AddressBook;
import com.sg.addressbook.ui.AddressBookView;
import com.sg.addressbook.ui.UserIO;
import com.sg.addressbook.ui.UserIoConsoleImpl;

import java.util.List;

public class AddressBookController {

    AddressBookView view;
    AddressBookDao dao;
    private UserIO io = new UserIoConsoleImpl();

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        while (keepGoing) {

            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1:
                    addAddress();
                    break;
                case 2:
                    removeAddress();
                    break;
                case 3:
                    viewAddress();
                    break;
                case 4:
                    countAddresses();
                    break;
                case 5:
                    listAddresses();
                    break;
                case 6:
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
            }
        }
        exitMessage();

    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void addAddress() {
        view.displayAddAddressBanner();
        AddressBook newAddress = view.getNewAddressInfo();
        dao.addAddress(newAddress.getLastName(), newAddress);
        view.displayAddAddressSuccessBanner();
    }

    private void listAddresses() {
        view.displayDisplayAllBanner();
        List<AddressBook> addressList = dao.getAllAddresses();
        view.displayAddressList(addressList);
    }

    private void viewAddress() {
        view.displayDisplayAddressBanner();
        String lastName = view.getLastNameChoice();
        AddressBook addressBook = dao.getAddress(lastName);
        view.displayAddress(addressBook);
    }

    private void removeAddress() {
        view.displayRemoveAddressBanner();
        String lastName = view.getLastNameChoice();
        dao.removeAddress(lastName);
        view.displayRemoveSuccessBanner();
    }

    private void countAddresses() {
        view.displayCountAddressBanner();
        int addressCount = dao.countAddresses();
        view.displayAddressCount(addressCount);
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

    public AddressBookController (AddressBookDao dao, AddressBookView view) {
        this.dao = dao;
        this.view = view;
    }

}
