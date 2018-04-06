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
    public Item purchaseItem(int itemNum) {
        return null;
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

    private void checkSufficientFunds(Item item) {

    }

    private void checkItemQuantity(Item item) {

    }
}
