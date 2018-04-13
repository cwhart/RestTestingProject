package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public interface VendingMachineServiceLayer {

    //..

    public List<Item> retrieveListAllWithQuantityGTZero() throws VendingMachinePersistenceException;

    public Item purchaseItem(int itemNum) throws InsufficientFundsException, InsufficientItemQuantityException,
            VendingMachinePersistenceException;

    public Change calculateChange();

    public BigDecimal addMoney(BigDecimal amountToAdd);

    public BigDecimal getRunningTotal();

    public void verifyPassword(String userInputPassword) throws IncorrectAdminPasswordException;

    public Item restockItem(int itemNo) throws VendingMachinePersistenceException;

    public Item addItem(Item newItem) throws VendingMachinePersistenceException;

    public String removeItem(int itemNo) throws VendingMachinePersistenceException;

    public Item updateItemPrice(Item itemToUpdatePrice) throws VendingMachinePersistenceException;

    public List<Item> retrieveListAll() throws VendingMachinePersistenceException;

    //Adding a comment for commit

}
