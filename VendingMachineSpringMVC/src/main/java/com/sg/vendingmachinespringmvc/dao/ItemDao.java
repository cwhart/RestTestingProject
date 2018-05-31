package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Item;

import java.util.List;

public interface ItemDao {

    //..

    public List<Item> retrieveAllItems()  ;

    public Item createItem(int itemNum, Item item)  ;

    public void removeItem(int itemNum)  ;

    public void updateItem(Item item)  ;

    public Item retrieveSingleItem(int itemNo)  ;

    //

}
