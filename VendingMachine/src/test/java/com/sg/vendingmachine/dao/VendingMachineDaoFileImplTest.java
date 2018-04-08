package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class VendingMachineDaoFileImplTest {

    //..

    VendingMachineDao dao = new VendingMachineDaoFileImpl();

    public VendingMachineDaoFileImplTest() {

    }

    @org.junit.Before
    public void setUp() throws Exception {
        List<Item> items = dao.retrieveAllItems();
        for (Item currentItem : items) {
            dao.removeItem(currentItem.getItemID());
        }
    }

    @org.junit.Test
    public void createAndRetrieveAllItems() throws Exception{

        Item newItem = new Item(1);
        newItem.setItemName("Ice Cream Sandwich");
        newItem.setItemPrice(BigDecimal.valueOf(.85));
        newItem.setItemQuantity(2);

        dao.createItem(newItem.getItemID(), newItem);

        newItem = new Item(2);
        newItem.setItemName("Ice Cream Sandwich");
        newItem.setItemPrice(BigDecimal.valueOf(.85));
        newItem.setItemQuantity(2);

        dao.createItem(newItem.getItemID(), newItem);

        assertEquals(2, dao.retrieveAllItems().size());

        }

    @org.junit.Test
    public void removeItem() throws Exception {

        Item newItem = new Item(1);
        newItem.setItemName("Ice Cream Sandwich");
        newItem.setItemPrice(BigDecimal.valueOf(.85));
        newItem.setItemQuantity(2);

        dao.createItem(newItem.getItemID(), newItem);

        Item fromDao = dao.removeItem(1);

        assertEquals(0, dao.retrieveAllItems().size());
        assertNull(dao.retrieveSingleItem(newItem.getItemID()));
    }

    @org.junit.Test
    public void updateItem() throws Exception{

        Item newItem = new Item(1);
        newItem.setItemName("Ice Cream Sandwich");
        newItem.setItemPrice(BigDecimal.valueOf(.85));
        newItem.setItemQuantity(2);

        dao.createItem(newItem.getItemID(), newItem);

        Item itemToUpdate = new Item(1);
        itemToUpdate.setItemName("Chocolate Ice Cream Sandwich");
        itemToUpdate.setItemPrice(BigDecimal.valueOf(1.05));
        itemToUpdate.setItemQuantity(3);

        dao.updateItem(itemToUpdate);

        Item updatedItem = dao.retrieveSingleItem(1);

        assertEquals(itemToUpdate, updatedItem);
    }

    @org.junit.Test
    public void retrieveSingleItem() throws Exception{

        Item newItem = new Item(1);
        newItem.setItemName("Ice Cream Sandwich");
        newItem.setItemPrice(BigDecimal.valueOf(.85));
        newItem.setItemQuantity(2);

        dao.createItem(newItem.getItemID(), newItem);

        Item retrievedItem = dao.retrieveSingleItem(1);

        assertEquals(newItem, retrievedItem);

    }
}