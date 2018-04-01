package com.sg.addressbook.ui;

import com.sg.addressbook.dto.AddressBook;
import com.sun.jndi.cosnaming.IiopUrl;

import java.util.List;

public class AddressBookView {

    private UserIO io;

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Add an Address");
        io.print("2. Remove an Address ");
        io.print("3. Find an Address by Last name ");
        io.print("4. Display a count of all Addresses ");
        io.print("5. Display a list of all Addresses ");
        io.print("6. Exit ");

        return io.readInt("Please select from the above choices.", 1, 6);
    }

    public AddressBookView(UserIO io) {
        this.io = io;
    }

    public AddressBook getNewAddressInfo() {
        String firstName = io.readString("Please enter first name");
        String lastName = io.readString("Please enter last name");
        String streetAddress = io.readString("Please enter street address");
        String city = io.readString("Please enter City");
        String state = io.readString("Please enter State");
        String zipCode = io.readString("Please enter Zip code");
        AddressBook currentAddress = new AddressBook(lastName);
        currentAddress.setFirstName(firstName);
        currentAddress.setStreetAddress(streetAddress);
        currentAddress.setCity(city);
        currentAddress.setState(state);
        currentAddress.setZipCode(zipCode);

        return currentAddress;
    }

    public void displayAddAddressBanner() {
        io.print("=== Add Address ===");
    }

    public void displayAddAddressSuccessBanner() {
        io.readString("Address added successfully. Please hit enter to continue");
    }

    public void displayAddressList(List<AddressBook> addressList) {
        for (AddressBook currentAddress : addressList) {
            io.print(currentAddress.getLastName() + ": " + currentAddress.getFirstName() + " "
            + currentAddress.getLastName() + "\n" + currentAddress.getStreetAddress() + "\n"
            + currentAddress.getCity() + ", " + currentAddress.getState() + " " + currentAddress.getZipCode());
        }
        io.readString("Please hit enter to continue");
    }

    public void displayDisplayAllBanner() {
        io.print("=== Display All Addresses ===");
    }

    public void displayDisplayAddressBanner() {
        io.print("=== Display Address +++");
    }

    public String getLastNameChoice() {
        return io.readString("Please enter the last name of the person you want to look up");
    }

    public void displayAddress(AddressBook addressBook) {
        if(addressBook != null) {
            io.print(addressBook.getFirstName() + " " + addressBook.getLastName());
            io.print(addressBook.getStreetAddress());
            io.print(addressBook.getCity() + " " +  addressBook.getState() + " " + addressBook.getZipCode());
            io.print(" ");
        } else {
            io.print("No such person");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayRemoveAddressBanner() {
        io.print("=== Remove Address ===");
    }

    public void displayRemoveSuccessBanner() {
        io.readString("Address successfully removed. Please hit enter to continue");
    }

    public void displayCountAddressBanner() {
        io.print("=== Count all Addresses ===");
    }

    public void displayAddressCount(int addressCount) {
        io.print("Number of addresses in your address book:");
        io.print(Integer.toString(addressCount));

        io.readString("Please hit enter to continue");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }


}
