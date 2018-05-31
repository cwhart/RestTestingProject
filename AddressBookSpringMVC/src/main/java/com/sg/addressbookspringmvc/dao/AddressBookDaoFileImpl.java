package com.sg.addressbookspringmvc.dao;

import com.sg.addressbookspringmvc.dto.Address;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/*public class AddressBookDaoFileImpl implements AddressBookDao {

        public static final String ADDRESS_FILE = "address.txt";
        public static final String DELIMITER = "::";
        private Map<String, Address> addresses = new HashMap<>();

        @Override
        public Address addAddress(String lastName, Address address) {
            Address newAddress = addresses.put(lastName, address);
            return newAddress;
        }

        @Override
        public Address removeAddress(String lastName) {
            Address removedAddress = addresses.remove(lastName);
            return removedAddress;
        }

        @Override
        public Address getAddress(String lastName) {
            return addresses.get(lastName);
        }

        @Override
        public int countAddresses() {
            return addresses.size();
        }

        @Override
        public List<Address> getAllAddresses() {
            return new ArrayList<Address>(addresses.values());
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
    }*/