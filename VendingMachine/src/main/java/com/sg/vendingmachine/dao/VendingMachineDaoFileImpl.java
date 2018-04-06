package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class VendingMachineDaoFileImpl implements VendingMachineDao {

    private Map<Integer, Item> itemMap = new HashMap<>();
    private final String INVENTORY_FILE = "inventory.txt";
    private final String DELIMITER = "::";


    @Override
    public List<Item> retrieveAllItems() throws VendingMachinePersistenceException {
        loadItemFile();
        List<Item> itemList = new ArrayList<>();
        for (Item currentItem: itemMap.values()) {
            if(currentItem.getItemQuantity()>0) {
                itemList.add(currentItem);
            }
        }

        return itemList;
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
        return itemMap.get(itemNo);
    }


    private void loadItemFile()  throws VendingMachinePersistenceException {

        Scanner scanner;

        try {

            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(INVENTORY_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException("-_- Could not load DVD data into memory", e);
        }

        String currentLine;
        String[] currentTokens;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);

            Item currentItem = new Item(Integer.parseInt(currentTokens[0]));

            currentItem.setItemName(currentTokens[1]);
            currentItem.setItemQuantity(Integer.parseInt(currentTokens[2]));
            currentItem.setItemPrice(new BigDecimal(currentTokens[3]));

            itemMap.put(currentItem.getItemID(), currentItem);

        }

        scanner.close();


    }


    private void writeItemFile() throws VendingMachinePersistenceException  {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(INVENTORY_FILE));

        } catch (IOException e) {
            throw new VendingMachinePersistenceException("Could not save DVD data." , e);
        }

        List<Item> itemList = this.retrieveAllItems();
        for (Item currentItem : itemList) {
            out.println(currentItem.getItemID() + DELIMITER
                    + currentItem.getItemName() + DELIMITER
                    + currentItem.getItemQuantity() + DELIMITER
                    + currentItem.getItemPrice() + DELIMITER);

            out.flush();
        }

        out.close();

    }
}
