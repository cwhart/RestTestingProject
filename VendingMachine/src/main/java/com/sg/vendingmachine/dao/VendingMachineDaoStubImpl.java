package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VendingMachineDaoStubImpl implements VendingMachineDao{

    List<Item> itemsInList = new ArrayList<>();
    Item item1;
    Item item2;

    public VendingMachineDaoStubImpl() {
        item1 = new Item(1);
        item1.setItemQuantity(4);
        item1.setItemPrice(BigDecimal.valueOf(1.50));
        item1.setItemName("Reese's Peanut Butter Cups");

        itemsInList.add(item1);

        item2 = new Item(2);
        item2.setItemQuantity(1);
        item2.setItemPrice(BigDecimal.valueOf(.98));
        item2.setItemName("Hershey's Chocolate Bar");

        itemsInList.add(item2);
    }

    @Override
    public List<Item> retrieveAllItems() throws VendingMachinePersistenceException {
        return itemsInList;
    }

    @Override
    public Item createItem(int itemNum, Item item) throws VendingMachinePersistenceException {

        //The create method simply returns the item that was just added.
        return item;
    }

    @Override
    public Item removeItem(int itemNum) throws VendingMachinePersistenceException {

        //Since the removeItem method just returns the Item that's been removed, all we need to
        //do here is return the item for the ID that was passed in.
        return itemsInList.get(itemNum);
    }

    @Override
    public Item updateItem(Item item) throws VendingMachinePersistenceException {
        return item;
    }

    @Override
    public Item retrieveSingleItem(int itemNo) throws VendingMachinePersistenceException {

        //Since we have two items and both are being used for different test cases, the retrieveSingleItem
        //method needs to be able to distinguish between them so it returns the correct one.

        if(itemNo == item2.getItemID() ) {
            return item2;
        } else if (itemNo == item1.getItemID()) {
            return item1;
        }

        else {
            return null;
        }
    }
}
