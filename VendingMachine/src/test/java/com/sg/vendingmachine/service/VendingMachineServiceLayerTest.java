package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoStubImpl;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class VendingMachineServiceLayerTest {

    private VendingMachineServiceLayer service;

    public VendingMachineServiceLayerTest() {
        VendingMachineDao dao = new VendingMachineDaoStubImpl();
        service = new VendingMachineServiceLayerImpl(dao);
    }

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void TestRetrieveListAll() throws VendingMachinePersistenceException{
        assertEquals(2, service.retrieveListAll().size());
    }

    @Test
    public void TestPurchaseItem() {

    }

    @Test
    public void TestCalculateChange() {
    }

    @Test
    public void TestAddMoney() {
        BigDecimal firstAmountToAdd = new BigDecimal(".97");
        BigDecimal newBalance = service.addMoney(firstAmountToAdd);
        assertEquals(firstAmountToAdd, newBalance);

        BigDecimal secondAmountToAdd = new BigDecimal("1.44");
        newBalance = service.addMoney(secondAmountToAdd);

        assertEquals(firstAmountToAdd.add(secondAmountToAdd), newBalance);
    }

    @Test
    public void TestGetRunningTotal() {
    }
}