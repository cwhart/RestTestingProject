package com.sg.addressbookspringmvc.dao;

import com.sg.addressbookspringmvc.dto.Address;

import java.util.List;

public interface AddressBookDao {

    void addAddress(Address address);

    void removeAddress(int addressId);

    public void updateAddress(Address address);

    Address getAddress(int addressId);

    List<Address> getAllAddresses();

}