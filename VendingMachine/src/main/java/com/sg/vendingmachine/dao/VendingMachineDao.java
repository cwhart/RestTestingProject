package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;

import java.util.List;

public interface VendingMachineDao {

    public List<Item> retrieveAllItems();

    public Item createItem(int itemNum, Item item);

    public Item removeItem(int itemNum);

    public Item updateItem(Item item);

    public Item retrieveSingleItem(int itemNo);

}
