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

        //Load the items in file to the Map, populate into an ArrayList & return the list.
        loadItemFile();
        List<Item> itemList = new ArrayList<>();
        for (Item currentItem: itemMap.values()) {
            itemList.add(currentItem);
        }

        return itemList;
    }

    @Override
    public Item createItem(int itemNum, Item item) throws VendingMachinePersistenceException {

        //This method is not called by the controller; it is used for testing.
        //Load the file into a Map, add the new item to the Map, write back to the file.
        loadItemFile();
        Item newItem = itemMap.put(itemNum, item);
        writeItemFile();
        return newItem;
    }

    @Override
    public Item removeItem(int itemNum) throws  VendingMachinePersistenceException {

        //Load the file into a Map, remove the item from the Map, write back to the file.
        loadItemFile();
        Item itemToRemove = itemMap.remove(itemNum);
        writeItemFile();

        return itemToRemove;
    }

    @Override
    public Item updateItem(Item item) throws VendingMachinePersistenceException {

        //Load the file into a Map, update the item ('put' will overwrite what's there based on the key),
        //write back to the file.
        loadItemFile();
        Item updatedItem = itemMap.put(item.getItemID(), item);
        writeItemFile();
        return itemMap.get(updatedItem.getItemID());
    }

    @Override
    public Item retrieveSingleItem(int itemNo) throws VendingMachinePersistenceException {

        //Load the file into a Map, get the item by the key.
        loadItemFile();
        return itemMap.get(itemNo);
    }


    private void loadItemFile()  throws VendingMachinePersistenceException {

        Scanner scanner;

        try {

            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(INVENTORY_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException("-_- Could not load Vending Machine inventory" +
                    " data into memory", e);
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
            throw new VendingMachinePersistenceException("Could not save inventory data." , e);
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

//Adding a comment for commit

}
