package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.*;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class VendingMachineServiceLayerTest {

    //..

    private VendingMachineServiceLayer service;

    public VendingMachineServiceLayerTest() {
        /*VendingMachineDao dao = new VendingMachineDaoStubImpl();
        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoImpl();
        service = new VendingMachineServiceLayerImpl(dao, auditDao);*/

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("serviceLayer", VendingMachineServiceLayer.class);
    }

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void TestRetrieveListAll() throws VendingMachinePersistenceException,
            InsufficientItemQuantityException, InsufficientFundsException{
        assertEquals(2, service.retrieveListAllWithQuantityGTZero().size());

        TestPurchaseItemHappyPath();

        assertEquals(1, service.retrieveListAllWithQuantityGTZero().size());
    }

    @Test
    public void TestPurchaseItemHappyPath() throws InsufficientFundsException,InsufficientItemQuantityException,
            VendingMachinePersistenceException {
        TestAddMoney();
        Item itemPurchased = service.purchaseItem(2);
        Item expectedItem = new Item(2);
        expectedItem.setItemQuantity(1);
        expectedItem.setItemPrice(BigDecimal.valueOf(.98));
        expectedItem.setItemName("Hershey's Chocolate Bar");

        assertEquals(2, itemPurchased.getItemID());
        assertEquals(0, itemPurchased.getItemQuantity());
        assertEquals(BigDecimal.valueOf(.98), itemPurchased.getItemPrice());
        assertEquals("Hershey's Chocolate Bar", itemPurchased.getItemName());

    }

    @Test (expected = InsufficientItemQuantityException.class)
    public void TestPurchaseItemNoStock() throws InsufficientFundsException,InsufficientItemQuantityException,
        VendingMachinePersistenceException {
        TestAddMoney();
        Item itemPurchased = service.purchaseItem(2);

        //Initial quantity was only 1, so if we buy it again, expect an exception.
        Item itemPurchasedAgain = service.purchaseItem(2);

    }

    @Test (expected = InsufficientFundsException.class)
    public void TestPurchaseItemInsufficientFunds() throws InsufficientFundsException,InsufficientItemQuantityException,
            VendingMachinePersistenceException {
        TestAddMoney();
        Item itemPurchased = service.purchaseItem(1);

        //Initial balance was only $2.41, so expect an exception on the 2nd purchase.
        Item itemPurchasedAgain = service.purchaseItem(1);

    }

    @Test
    public void TestCalculateChange() {
        TestAddMoney();
        Change change = service.calculateChange();

        assertEquals(9, change.getQuarters());
        assertEquals(1, change.getDimes());
        assertEquals(1, change.getNickels());
        assertEquals(2, change.getPennies());

    }

    @Test
    public void TestAddMoney() {
        BigDecimal firstAmountToAdd = new BigDecimal(".98");
        BigDecimal newBalance = service.addMoney(firstAmountToAdd);
        assertEquals(firstAmountToAdd, newBalance);

        BigDecimal secondAmountToAdd = new BigDecimal("1.44");
        newBalance = service.addMoney(secondAmountToAdd);

        assertEquals(firstAmountToAdd.add(secondAmountToAdd), newBalance);
    }

    @Test
    public void TestGetRunningTotal() {

        TestAddMoney();
        BigDecimal expectedTotal = new BigDecimal("2.42");
        BigDecimal actualTotal = service.getRunningTotal();

        assertEquals(expectedTotal, actualTotal);


    }
}