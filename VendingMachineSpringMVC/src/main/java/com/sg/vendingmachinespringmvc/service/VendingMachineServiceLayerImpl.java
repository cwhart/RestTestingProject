package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dao.ChangeDao;
import com.sg.vendingmachinespringmvc.dao.ItemDao;
import com.sg.vendingmachinespringmvc.model.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    ItemDao itemDao;
    ChangeDao changeDao;
    //VendingMachineAuditDao auditDao;
    BigDecimal runningTotal = new BigDecimal("0");

    public VendingMachineServiceLayerImpl(ItemDao dao, ChangeDao changeDao ) {
        this.itemDao = dao;
        this.changeDao = changeDao;
    }
    @Override
    public List<Item> retrieveListAllWithQuantityGTZero()  {
        List<Item> items = itemDao.retrieveAllItems();
        List<Item> itemsWithQuantityMoreThanZero = new ArrayList<>();



        //Create a new ArrayList, check the quantity and populate into the new list only
        //items that have a quantity>0. Return the new list.

        for (Item currentItem : items) {
            if (currentItem.getItemQuantity() >0) {
                itemsWithQuantityMoreThanZero.add(currentItem);
            }
        }
        return itemsWithQuantityMoreThanZero.stream()
                .filter(s -> s.getItemQuantity() > 0)
                .collect(Collectors.toList());
    }

    public List<Item> retrieveListAll()  {
        return itemDao.retrieveAllItems();

    }

    @Override
    public String purchaseItem(int itemNum) throws InsufficientItemQuantityException, InsufficientFundsException {
        Item currentItem = itemDao.retrieveSingleItem(itemNum);

        //validate quantity - will throw exception if insufficient, otherwise keeps going.
        checkItemQuantityAndSelection(currentItem);

        //validate funds - will throw exception if insufficient, otherwise keeps going.
        checkSufficientFunds(currentItem);

        //subtract price from total running
        runningTotal = runningTotal.subtract(currentItem.getItemPrice());

        //decrement quantity
        currentItem.setItemQuantity(currentItem.getItemQuantity() - 1);

        //update item
        itemDao.updateItem(currentItem);

        //return item
        return "Thank you!";
    }

    /*@Override
    public Change calculateChange() {
        Change yourChange = new Change();

        //First determine how many quarters to return. Divide runningTotal by .25, convert
        //that result to an int, and set equal to the number of quarters.

        BigDecimal numQuarters = runningTotal.divideToIntegralValue(BigDecimal.valueOf(.25));
        int quarters = numQuarters.intValue();
        yourChange.setQuarters(quarters);

        //Subtract quarters returned from running total
        runningTotal = runningTotal.subtract(numQuarters.multiply(BigDecimal.valueOf(.25)));

        //Determine how many dimes. Divide runningTotal by .10, convert
        //that result to an int, and set equal to the number of dimes.
        BigDecimal numDimes = runningTotal.divideToIntegralValue(BigDecimal.valueOf(.10));
        int dimes = numDimes.intValue();
        yourChange.setDimes(dimes);

        //Subtract dimes returned from running total
        runningTotal = runningTotal.subtract(numDimes.multiply(BigDecimal.valueOf(.10)));

        //Determine how many nickels
        BigDecimal numNickels = runningTotal.divideToIntegralValue(BigDecimal.valueOf(.05));
        int nickels = numNickels.intValue();
        yourChange.setNickels(nickels);

        //Subtract nickels returned from running total
        runningTotal = runningTotal.subtract(numNickels.multiply(BigDecimal.valueOf(.05)));

        //Now pennies
        BigDecimal numPennies = runningTotal.multiply(BigDecimal.valueOf(100));
        int pennies = numPennies.intValue();
        yourChange.setPennies(pennies);

        //Subtract pennies returned from running total
        runningTotal = runningTotal.subtract(numPennies.multiply(BigDecimal.valueOf(.01)));

        return yourChange;
    }*/

    @Override
    public BigDecimal addMoney(BigDecimal amountToAdd) {

        //Add amount passed in to the runningTotal.
        runningTotal = runningTotal.add(amountToAdd);
        return runningTotal;
    }

    public BigDecimal getRunningTotal() {
        return runningTotal;
    }

    /*@Override
    public BigDecimal getRunningTotal() {
        return runningTotal;
    }*/

    private void checkSufficientFunds(Item item) throws InsufficientFundsException {

        //Check for sufficient funds for the purchase, display error message if insufficient.
        if (runningTotal.compareTo(item.getItemPrice()) < 0) {
            BigDecimal amountShort = item.getItemPrice().subtract(runningTotal);
            throw new InsufficientFundsException("Please deposit: $" + amountShort);


        }
    }

    private void checkItemQuantityAndSelection(Item item) throws InsufficientItemQuantityException
             {

        //Check for sufficient quantity, display error message if insufficient.
        if (item.getItemQuantity() < 1) {
            throw new InsufficientItemQuantityException("ERROR: there are no " + item.getItemName()
                    + "s in stock. Please make another selection.");
        }
    }

    /*public Item restockItem(int itemNo)  {
        Item currentItem = itemDao.retrieveSingleItem(itemNo);
        currentItem.setItemQuantity(10);
        itemDao.updateItem(currentItem);
        return itemDao.retrieveSingleItem(itemNo);
    }

    public Item addItem(Item newItem)  {
        return itemDao.createItem(newItem.getItemID(), newItem);
        //return dao.retrieveSingleItem(newItem.getItemID());
    }

    public String removeItem(int itemNo)  {
        Item item = itemDao.retrieveSingleItem(itemNo);
        String itemName = item.getItemName();
        itemDao.removeItem(itemNo);
        return itemName;
    }

    public Item updateItemPrice(Item itemToUpdatePrice)  {
        Item itemToUpdate = itemDao.retrieveSingleItem(itemToUpdatePrice.getItemID());
        itemToUpdate.setItemPrice(itemToUpdatePrice.getItemPrice());
        return itemDao.updateItem(itemToUpdate);

    }*/

    //Adding a comment for commit

}
