package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    VendingMachineDao dao;
    BigDecimal runningTotal = new BigDecimal("0");

    public VendingMachineServiceLayerImpl(VendingMachineDao dao) {
        this.dao = dao;
    }
    @Override
    public List<Item> retrieveListAll() throws VendingMachinePersistenceException {
        List<Item> items = dao.retrieveAllItems();
        List<Item> itemsWithQuantityMoreThanZero = new ArrayList<>();

        //Originally had logic to check for quantity>0 in the dao, but had to move it here
        //because it was messing up the writeItemFile method.

        for (Item currentItem : items) {
            if (currentItem.getItemQuantity() >0) {
                itemsWithQuantityMoreThanZero.add(currentItem);
            }
        }
        return itemsWithQuantityMoreThanZero;
    }

    @Override
    public Item purchaseItem(int itemNum) throws InsufficientFundsException,InsufficientItemQuantityException,
    VendingMachinePersistenceException{
        Item currentItem = dao.retrieveSingleItem(itemNum);

        //validate quantity
        checkItemQuantity(currentItem);

        //validate funds
        checkSufficientFunds(currentItem);

        //subtract price from total running
        runningTotal = runningTotal.subtract(currentItem.getItemPrice());

        //decrement quantity
        currentItem.setItemQuantity(currentItem.getItemQuantity() - 1);

        //update item
        dao.updateItem(currentItem);

        //return item
        return currentItem;
    }

    @Override
    public Change calculateChange() {
        //First determine how many quarters to return
        Change yourChange = new Change();
        BigDecimal numQuarters = runningTotal.divideToIntegralValue(BigDecimal.valueOf(.25));
        int quarters = numQuarters.intValue();
        yourChange.setQuarters(quarters);

        //Subtract quarters returned from running total
        runningTotal = runningTotal.subtract(numQuarters.multiply(BigDecimal.valueOf(.25)));

        //Determine how many dimes
        BigDecimal numDimes = runningTotal.divideToIntegralValue(BigDecimal.valueOf(.1));
        int dimes = numDimes.intValue();
        yourChange.setDimes(dimes);

        //Subtract dimes returned from running total
        runningTotal = runningTotal.subtract(numDimes.multiply(BigDecimal.valueOf(.1)));

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
    }

    @Override
    public BigDecimal addMoney(BigDecimal amountToAdd) {
        runningTotal = runningTotal.add(amountToAdd);
        return runningTotal;
    }

    @Override
    public BigDecimal getRunningTotal() {
        return runningTotal;
    }

    private void checkSufficientFunds(Item item) throws InsufficientFundsException {
        if (runningTotal.compareTo(item.getItemPrice()) < 0) {
            throw new InsufficientFundsException("You do not have enough money to purchase that item. " +
                    "Please add more money or choose a different item.");


        }
    }

    private void checkItemQuantity(Item item) throws InsufficientItemQuantityException {
        if(item.getItemQuantity() < 1) {
            throw new InsufficientItemQuantityException("ERROR: there are no " + item.getItemName()
            + "s in stock. Please make another selection.");
        }

    }
}

