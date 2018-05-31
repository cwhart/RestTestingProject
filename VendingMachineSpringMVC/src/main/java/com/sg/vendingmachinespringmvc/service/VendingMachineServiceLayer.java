package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.Item;

import java.math.BigDecimal;
import java.util.List;

public interface VendingMachineServiceLayer {

    public List<Item> retrieveListAllWithQuantityGTZero();

    public String purchaseItem(int itemNum) throws InsufficientItemQuantityException, InsufficientFundsException;

    //public Change calculateChange();

    public BigDecimal addMoney(BigDecimal amountToAdd);

    public BigDecimal getRunningTotal();

    //public Item restockItem(int itemNo) ;

    //public Item addItem(Item newItem) ;

    //public String removeItem(int itemNo) ;

    //public Item updateItemPrice(Item itemToUpdatePrice) ;

    public List<Item> retrieveListAll() ;
}
