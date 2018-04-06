package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineDaoFileImpl implements VendingMachineDao {

    private Map<Integer, Item> itemMap;
    private final String INVENTORY_FILE = "inventory.txt";
    private final String DELIMITER = "::";


    @Override
    public List<Item> retrieveAllItems() {
        return null;
    }

    @Override
    public Item createItem(int itemNum, Item item) {
        return null;
    }

    @Override
    public Item removeItem(int itemNum) {
        return null;
    }

    @Override
    public Item updateItem(Item item) {
        return null;
    }

    @Override
    public Item retrieveSingleItem(int itemNo) {
        return null;
    }


    private void loadItemFile() {



    }


    private void writeItemFile() {

    }
}
