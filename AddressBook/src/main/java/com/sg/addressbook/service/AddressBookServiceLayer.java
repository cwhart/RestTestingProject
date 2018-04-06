package com.sg.addressbook.service;

import com.sg.addressbook.dto.AddressBook;

import java.util.List;

public interface AddressBookServiceLayer {

    void createAddress (AddressBook address);

    List<AddressBook> getAllAddresses();

    AddressBook getAddress(String lastName);

    AddressBook removeAddress(String lastName);


}
