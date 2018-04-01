package com.sg.addressbook.dao;

import com.sg.addressbook.dto.AddressBook;
import com.sun.jndi.cosnaming.IiopUrl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class AddressBookDaoFileImpl implements AddressBookDao {

    public static final String ADDRESS_FILE = "address.txt";
    public static final String DELIMITER = "::";
    private Map<String, AddressBook> addresses = new HashMap<>();

    @Override
    public AddressBook addAddress(String lastName, AddressBook addressBook) {
        AddressBook newAddress = addresses.put(lastName, addressBook);
        return newAddress;
    }

    @Override
    public AddressBook removeAddress(String lastName) {
        AddressBook removedAddress = addresses.remove(lastName);
        return removedAddress;
    }

    @Override
    public AddressBook getAddress(String lastName) {
        return addresses.get(lastName);
    }

    @Override
    public int countAddresses() {
        return addresses.size();
    }

    @Override
    public List<AddressBook> getAllAddresses() {
        return new ArrayList<AddressBook>(addresses.values());
    }

    private void loadAddress() throws AddressBookDaoException {
        Scanner scanner;

        try {

            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ADDRESS_FILE)));
        } catch (FileNotFoundException e ) {
            throw new AddressBookDaoException(
                    "- - Could not load address data into memory.", e);
        }
        String currentLine;
        String[] currentTokens;

        while(scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
        }
    }
}
