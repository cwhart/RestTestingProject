package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    VendingMachineDao dao;
    BigDecimal runningTotal = new BigDecimal("0");



    public VendingMachineServiceLayerImpl(VendingMachineDao dao) {
        this.dao = dao;
    }
    @Override
    public List<Item> retrieveListAll() throws VendingMachinePersistenceException {
        return dao.retrieveAllItems();
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

        //return item
        return currentItem;
    }

    @Override
    public Change calculateChange() {
        return null;
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
            + "s in stock");
        }

    }
}

