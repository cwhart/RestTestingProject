package com.sg.addressbookspringmvc.controller;

import com.sg.addressbookspringmvc.dao.AddressBookDao;
import com.sg.addressbookspringmvc.dto.Address;
import com.sg.addressbookspringmvc.ui.AddressBookView;
import com.sg.addressbookspringmvc.ui.UserIO;
import com.sg.addressbookspringmvc.ui.UserIoConsoleImpl;

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
                    //countAddresses();
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
        Address newAddress = view.getNewAddressInfo();
        dao.addAddress(newAddress);
        view.displayAddAddressSuccessBanner();
    }

    private void listAddresses() {
        view.displayDisplayAllBanner();
        List<Address> addressList = dao.getAllAddresses();
        view.displayAddressList(addressList);
    }

    private void viewAddress() {
        view.displayDisplayAddressBanner();
        int addressId = view.getAddressChoice();
        Address address = dao.getAddress(addressId);
        view.displayAddress(address);
    }

    private void removeAddress() {
        view.displayRemoveAddressBanner();
        int addressId = view.getAddressChoice();
        dao.removeAddress(addressId);
        view.displayRemoveSuccessBanner();
    }

    /*private void countAddresses() {
        view.displayCountAddressBanner();
        int addressCount = dao.countAddresses();
        view.displayAddressCount(addressCount);
    }*/

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
