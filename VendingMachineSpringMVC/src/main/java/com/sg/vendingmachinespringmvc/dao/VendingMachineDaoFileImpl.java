package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Item;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class VendingMachineDaoFileImpl implements VendingMachineDao {

    private Map<Integer, Item> itemMap = new HashMap<>();
    //private final String INVENTORY_FILE = "C:\\Users\\N0148464\\Documents\\bitbucket\\cynthia-hartnett-individual-work\\VendingMachineSpringMVC\\inventory.txt";
    //private final String DELIMITER = "::";

    //Create inventory in memory.
    public VendingMachineDaoFileImpl() {
        Item item1 = new Item(1);
        item1.setItemQuantity(0);
        item1.setItemName("Ice Cream Sandwich");
        item1.setItemPrice(BigDecimal.valueOf(0.85).setScale(2));

        itemMap.put(item1.getItemID(), item1);

        Item item2 = new Item(2);
        item2.setItemQuantity(10);
        item2.setItemName("Pop-Tarts");
        item2.setItemPrice(BigDecimal.valueOf(1.50).setScale(2));

        itemMap.put(item2.getItemID(), item2);

        Item item3 = new Item(3);
        item3.setItemQuantity(10);
        item3.setItemName("Snickers");
        item3.setItemPrice(BigDecimal.valueOf(0.95).setScale(2));

        itemMap.put(item3.getItemID(), item3);

        Item item4 = new Item(4);
        item4.setItemQuantity(10);
        item4.setItemName("M&Ms");
        item4.setItemPrice(BigDecimal.valueOf(1.10).setScale(2));

        itemMap.put(item4.getItemID(), item4);

        Item item5 = new Item(5);
        item5.setItemQuantity(10);
        item5.setItemName("Coke");
        item5.setItemPrice(BigDecimal.valueOf(1.50).setScale(2));

        itemMap.put(item5.getItemID(), item5);

        Item item6 = new Item(6);
        item6.setItemQuantity(10);
        item6.setItemName("Doritos");
        item6.setItemPrice(BigDecimal.valueOf(1.25).setScale(2));

        itemMap.put(item6.getItemID(), item6);

        Item item7 = new Item(7);
        item7.setItemQuantity(10);
        item7.setItemName("Sour Patch Kids");
        item7.setItemPrice(BigDecimal.valueOf(1.75).setScale(2));

        itemMap.put(item7.getItemID(), item7);

        Item item8 = new Item(8);
        item8.setItemQuantity(10);
        item8.setItemName("Milky-Way");
        item8.setItemPrice(BigDecimal.valueOf(1.25).setScale(2));

        itemMap.put(item8.getItemID(), item8);

        Item item9 = new Item(9);
        item9.setItemQuantity(10);
        item9.setItemName("Fritos");
        item9.setItemPrice(BigDecimal.valueOf(1.10).setScale(2));

        itemMap.put(item9.getItemID(), item9);
    }


    @Override
    public List<Item> retrieveAllItems() throws VendingMachinePersistenceException {

        //loadItemFile();
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
        //loadItemFile();
        Item newItem = itemMap.put(itemNum, item);
        //writeItemFile();
        return newItem;
    }

    @Override
    public Item removeItem(int itemNum) throws VendingMachinePersistenceException {

        //Load the file into a Map, remove the item from the Map, write back to the file.
        //loadItemFile();
        Item itemToRemove = itemMap.remove(itemNum);
        //writeItemFile();

        return itemToRemove;
    }

    @Override
    public Item updateItem(Item item) throws VendingMachinePersistenceException  {

        //Load the file into a Map, update the item ('put' will overwrite what's there based on the key),
        //write back to the file.
        //loadItemFile();
        Item updatedItem = itemMap.put(item.getItemID(), item);
        //writeItemFile();
        return itemMap.get(updatedItem.getItemID());
    }

    @Override
    public Item retrieveSingleItem(int itemNo) throws VendingMachinePersistenceException {

        //Load the file into a Map, get the item by the key.
        //loadItemFile();
        return itemMap.get(itemNo);
    }


    /*private void loadItemFile()  throws VendingMachinePersistenceException {

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

    }*/


}
