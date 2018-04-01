package com.sg.addressbook.dao;

import com.sg.addressbook.dto.AddressBook;

import java.util.List;

public interface AddressBookDao {

    AddressBook addAddress(String lastName, AddressBook addressBook);

    AddressBook removeAddress(String lastName);

    AddressBook getAddress(String lastName);

    int countAddresses();

    List<AddressBook> getAllAddresses();

}