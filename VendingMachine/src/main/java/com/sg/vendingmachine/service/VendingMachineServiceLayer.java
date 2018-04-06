package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public interface VendingMachineServiceLayer {

    public List<Item> retrieveListAll() throws VendingMachinePersistenceException;

    public Item purchaseItem(int itemNum);

    public Change calculateChange();

    public BigDecimal addMoney(BigDecimal amountToAdd);

    public BigDecimal getRunningTotal();
}
