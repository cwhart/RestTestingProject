package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ProductDaoTest {

    ProductDao dao = new ProductDaoFileImpl();

    @Before
    public void setUp() throws Exception {
        List<Product> productList = dao.retrieveAllProducts();
        for (Product currentProduct : productList) {
            dao.removeProduct(currentProduct);
        }
    }

    @Test
    public void createAndRetrieveProduct() throws ProductPersistenceException {
        Product product1 = new Product("Pine");
        product1.setLaborCostPerSquareFoot(new BigDecimal("1.50"));
        product1.setMaterialCostPerSquareFoot(new BigDecimal("3.25"));
        dao.createProduct(product1);

        assertEquals(1, dao.retrieveAllProducts().size());
        assertEquals(product1, dao.retrieveProduct(product1.getProductType()));
    }

    @Test
    public void retrieveAllProducts() throws ProductPersistenceException {
        Product product1 = new Product("Pine");
        product1.setLaborCostPerSquareFoot(new BigDecimal("1.50"));
        product1.setMaterialCostPerSquareFoot(new BigDecimal("3.25"));
        dao.createProduct(product1);

        Product product2 = new Product("Oak");
        product2.setLaborCostPerSquareFoot(new BigDecimal("2.17"));
        product2.setMaterialCostPerSquareFoot(new BigDecimal("4.42"));
        dao.createProduct(product2);

        Product product3 = new Product("Vinyl");
        product3.setLaborCostPerSquareFoot(new BigDecimal("1.12"));
        product3.setMaterialCostPerSquareFoot(new BigDecimal("2.82"));
        dao.createProduct(product3);

        assertEquals(3, dao.retrieveAllProducts().size());

    }

    @Test
    public void updateProduct()  throws ProductPersistenceException{
        Product product1 = new Product("Pine");
        product1.setLaborCostPerSquareFoot(new BigDecimal("1.50"));
        product1.setMaterialCostPerSquareFoot(new BigDecimal("3.25"));
        dao.createProduct(product1);

        assertEquals(1, dao.retrieveAllProducts().size());

        Product product2 = new Product("Pine");
        product2.setLaborCostPerSquareFoot(new BigDecimal("2.17"));
        product2.setMaterialCostPerSquareFoot(new BigDecimal("4.42"));
        dao.updateProduct(product2);

        assertEquals(1, dao.retrieveAllProducts().size());
        assertEquals(product2, dao.retrieveProduct("Pine"));

    }

    @Test
    public void removeProduct() throws ProductPersistenceException {
        Product product1 = new Product("Pine");
        product1.setLaborCostPerSquareFoot(new BigDecimal("1.50"));
        product1.setMaterialCostPerSquareFoot(new BigDecimal("3.25"));
        dao.createProduct(product1);

        assertEquals(1, dao.retrieveAllProducts().size());

        dao.removeProduct(product1);

        assertEquals(0, dao.retrieveAllProducts().size());

    }
}