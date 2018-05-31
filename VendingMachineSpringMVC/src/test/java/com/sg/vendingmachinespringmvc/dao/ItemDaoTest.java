package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Item;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ItemDaoTest {

    ItemDao dao;

    public ItemDaoTest() {

    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {

    }

    @Before
    public void setUp() throws VendingMachinePersistenceException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        dao = ctx.getBean("vendingMachineDao", ItemDao.class);

        List<Item> items = dao.retrieveAllItems();
        for (Item currentItem : items) {
            dao.removeItem(currentItem.getItemID());
        }


    }

    @After
    public void tearDown() {

    }

    @Test
    public void addGetItem() throws VendingMachinePersistenceException {
        Item item = new Item();
        item.setItemName("Snickers");
        item.setItemQuantity(5);
        item.setItemPrice(BigDecimal.valueOf(.95));

        dao.createItem(item.getItemID(), item);

        Item fromDao = dao.retrieveSingleItem(item.getItemID());
        assertEquals(item.getItemName(), fromDao.getItemName());
        assertEquals(item.getItemPrice(), fromDao.getItemPrice());
        assertEquals(item.getItemQuantity(), fromDao.getItemQuantity());
    }
}
