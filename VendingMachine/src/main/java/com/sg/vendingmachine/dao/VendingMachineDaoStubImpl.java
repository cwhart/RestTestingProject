package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VendingMachineDaoStubImpl implements VendingMachineDao{

    List<Item> itemsInList = new ArrayList<>();
    Item item;

    public VendingMachineDaoStubImpl() {
        Item currentItem = new Item(1);
        currentItem.setItemQuantity(4);
        currentItem.setItemPrice(BigDecimal.valueOf(.85));
        currentItem.setItemName("Reese's Peanut Butter Cups");

        itemsInList.add(currentItem);

        currentItem = new Item(2);
        currentItem.setItemQuantity(2);
        currentItem.setItemPrice(BigDecimal.valueOf(.98));
        currentItem.setItemName("Hershey's Chocolate Bar");

        itemsInList.add(currentItem);
    }

    @Override
    public List<Item> retrieveAllItems() throws VendingMachinePersistenceException {
        return itemsInList;
    }

    @Override
    public Item createItem(int itemNum, Item item) throws VendingMachinePersistenceException {
        return item;
    }

    @Override
    public Item removeItem(int itemNum) throws VendingMachinePersistenceException {
        return itemsInList.get(itemNum);
    }

    @Override
    public Item updateItem(Item item) throws VendingMachinePersistenceException {
        return item;
    }

    @Override
    public Item retrieveSingleItem(int itemNo) throws VendingMachinePersistenceException {
        if(itemNo == item.getItemID()) {
            return item;
        } else {
            return null;
        }
    }
}
