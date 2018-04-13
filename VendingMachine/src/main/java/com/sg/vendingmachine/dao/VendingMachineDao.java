package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;

import java.util.List;

public interface VendingMachineDao {

    //..

    public List<Item> retrieveAllItems() throws VendingMachinePersistenceException ;

    public Item createItem(int itemNum, Item item) throws VendingMachinePersistenceException ;

    public Item removeItem(int itemNum) throws VendingMachinePersistenceException ;

    public Item updateItem(Item item) throws VendingMachinePersistenceException ;

    public Item retrieveSingleItem(int itemNo) throws VendingMachinePersistenceException ;

    //Adding a comment for commit

}
