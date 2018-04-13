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
    public void testRetrieveListAll() throws VendingMachinePersistenceException,
            InsufficientItemQuantityException, InsufficientFundsException{
        assertEquals(2, service.retrieveListAllWithQuantityGTZero().size());

        testPurchaseItemHappyPath();

        assertEquals(1, service.retrieveListAllWithQuantityGTZero().size());
    }

    @Test
    public void testPurchaseItemHappyPath() throws InsufficientFundsException,InsufficientItemQuantityException,
            VendingMachinePersistenceException {
        testAddMoney();
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
    public void testPurchaseItemNoStock() throws InsufficientFundsException,InsufficientItemQuantityException,
        VendingMachinePersistenceException {
        testAddMoney();
        Item itemPurchased = service.purchaseItem(2);

        //Initial quantity was only 1, so if we buy it again, expect an exception.
        Item itemPurchasedAgain = service.purchaseItem(2);

    }

    @Test (expected = InsufficientFundsException.class)
    public void testPurchaseItemInsufficientFunds() throws InsufficientFundsException,InsufficientItemQuantityException,
            VendingMachinePersistenceException {
        testAddMoney();
        Item itemPurchased = service.purchaseItem(1);

        //Initial balance was only $2.41, so expect an exception on the 2nd purchase.
        Item itemPurchasedAgain = service.purchaseItem(1);

    }

    @Test
    public void testCalculateChange() {
        testAddMoney();
        Change change = service.calculateChange();

        assertEquals(9, change.getQuarters());
        assertEquals(1, change.getDimes());
        assertEquals(1, change.getNickels());
        assertEquals(2, change.getPennies());

    }

    @Test
    public void testAddMoney() {
        BigDecimal firstAmountToAdd = new BigDecimal(".98");
        BigDecimal newBalance = service.addMoney(firstAmountToAdd);
        assertEquals(firstAmountToAdd, newBalance);

        BigDecimal secondAmountToAdd = new BigDecimal("1.44");
        newBalance = service.addMoney(secondAmountToAdd);

        assertEquals(firstAmountToAdd.add(secondAmountToAdd), newBalance);
    }

    @Test
    public void testGetRunningTotal() {

        testAddMoney();
        BigDecimal expectedTotal = new BigDecimal("2.42");
        BigDecimal actualTotal = service.getRunningTotal();

        assertEquals(expectedTotal, actualTotal);


    }

    @Test (expected = Test.None.class)
    public void testVerifyPasswordPositive() throws IncorrectAdminPasswordException {
        service.verifyPassword("Shrubbery");

    }

    @Test(expected = IncorrectAdminPasswordException.class)
    public void testVerifyPasswordNegative() throws IncorrectAdminPasswordException {
        service.verifyPassword("43tirojer");

    }

    @Test(expected = IncorrectAdminPasswordException.class)
    public void testVerifyBlankPassword() throws IncorrectAdminPasswordException {
        service.verifyPassword("");
    }



    @Test
    public void testRestockItem() throws VendingMachinePersistenceException{
        Item myItem = service.restockItem(2);
        assertEquals(myItem.getItemQuantity(), 10);

    }

    @Test
    public void testAddItem() throws VendingMachinePersistenceException{
        Item newItem = new Item(10);
        newItem.setItemPrice(new BigDecimal("5.00"));
        newItem.setItemName("Test Item");
        newItem.setItemQuantity(55);

        assertTrue(service.addItem(newItem).equals(newItem));

    }

    @Test
    public void testRemoveItem() throws VendingMachinePersistenceException {
        assertTrue(service.removeItem(1).equals("Reese's Peanut Butter Cups"));
    }

    @Test
    public void testUpdateItemPrice() throws VendingMachinePersistenceException{
        Item item1 = new Item(1);
        item1.setItemQuantity(4);
        item1.setItemPrice(BigDecimal.valueOf(5.48));
        item1.setItemName("Reese's Peanut Butter Cups");

        Item updatedItem = service.updateItemPrice(item1);
        assertTrue(updatedItem.getItemPrice().equals(new BigDecimal("5.48")));
    }

//Adding a comment for commit
}