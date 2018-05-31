package com.sg.addressbookspringmvc.service;

import com.sg.addressbookspringmvc.dto.Address;

import java.util.List;

public interface AddressBookServiceLayer {

    void createAddress (Address address);

    List<Address> getAllAddresses();

    Address getAddress(String lastName);

    Address removeAddress(String lastName);


}

