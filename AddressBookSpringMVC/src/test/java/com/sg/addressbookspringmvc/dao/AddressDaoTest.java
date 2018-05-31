package com.sg.addressbookspringmvc.dao;

import com.sg.addressbookspringmvc.dto.Address;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AddressDaoTest {

    AddressBookDao dao;

    public AddressDaoTest() {

    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {

    }

    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        dao = ctx.getBean("addressBookDao", AddressBookDao.class);

        List<Address> addresses = dao.getAllAddresses();
        for (Address currentAddress : addresses) {
            dao.removeAddress(currentAddress.getAddressId());
        }
    }

    @After
    public void tearDown() {

    }

    @Test
    public void addGetAddress() {
        Address address = new Address();
        address.setFirstName("Joe");
        address.setLastName("Schmoe");
        address.setStreetAddress("555 Nowhere Blvd.");
        address.setCity("Springfield");
        address.setState("OO");
        address.setZipCode("12345");

        dao.addAddress(address);

        Address fromDao = dao.getAddress(address.getAddressId());
        assertEquals(fromDao, address);
    }

    @Test
    public void deleteAddress() {
        Address address = new Address();
        address.setFirstName("Joe");
        address.setLastName("Schmoe");
        address.setStreetAddress("555 Nowhere Blvd.");
        address.setCity("Springfield");
        address.setState("OO");
        address.setZipCode("12345");

        dao.addAddress(address);

        Address fromDao = dao.getAddress(address.getAddressId());
        assertEquals(fromDao, address);
        dao.removeAddress(address.getAddressId());
        assertNull(dao.getAddress(address.getAddressId()));

    }
}
