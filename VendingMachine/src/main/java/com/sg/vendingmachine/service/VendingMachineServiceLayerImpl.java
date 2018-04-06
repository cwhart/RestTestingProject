package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    VendingMachineDao dao;


    public VendingMachineServiceLayerImpl(VendingMachineDao dao) {
        this.dao = dao;
    }
    @Override
    public List<Item> retrieveListAll() {
        return null;
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
        return null;
    }

    @Override
    public BigDecimal getRunningTotal() {
        return null;
    }

    private void checkSufficientFunds(Item item) {

    }

    private void checkItemQuantity(Item item) {

    }
}
