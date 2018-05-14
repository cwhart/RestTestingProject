package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Item;

import java.util.List;

public interface VendingMachineDao {

    //..

    public List<Item> retrieveAllItems() throws VendingMachinePersistenceException ;

    public Item createItem(int itemNum, Item item) throws VendingMachinePersistenceException ;

    public Item removeItem(int itemNum) throws VendingMachinePersistenceException ;

    public Item updateItem(Item item) throws VendingMachinePersistenceException ;

    public Item retrieveSingleItem(int itemNo) throws VendingMachinePersistenceException ;


}
