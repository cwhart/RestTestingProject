package com.sg.flooringmastery.dto;

import java.math.BigDecimal;

public class Product {

    private String productType;
    private BigDecimal materialCostPerSquareFoot;
    private BigDecimal laborCostPerSquareFoot;

    public Product(String productType) {
        this.productType = productType;
    }

    public Product(String productType, BigDecimal materialCostPerSquareFoot, BigDecimal laborCostPerSquareFoot) {
        this.productType = productType;
        this.materialCostPerSquareFoot = materialCostPerSquareFoot;
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getMaterialCostPerSquareFoot() {
        return materialCostPerSquareFoot;
    }

    public void setMaterialCostPerSquareFoot(BigDecimal materialCostPerSquareFoot) {
        this.materialCostPerSquareFoot = materialCostPerSquareFoot;
    }

    public BigDecimal getLaborCostPerSquareFoot() {
        return laborCostPerSquareFoot;
    }

    public void setLaborCostPerSquareFoot(BigDecimal laborCostPerSquareFoot) {
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (productType != null ? !productType.equals(product.productType) : product.productType != null) return false;
        if (materialCostPerSquareFoot != null ? !materialCostPerSquareFoot.equals(product.materialCostPerSquareFoot) : product.materialCostPerSquareFoot != null)
            return false;
        return laborCostPerSquareFoot != null ? laborCostPerSquareFoot.equals(product.laborCostPerSquareFoot) : product.laborCostPerSquareFoot == null;
    }

    @Override
    public int hashCode() {
        int result = productType != null ? productType.hashCode() : 0;
        result = 31 * result + (materialCostPerSquareFoot != null ? materialCostPerSquareFoot.hashCode() : 0);
        result = 31 * result + (laborCostPerSquareFoot != null ? laborCostPerSquareFoot.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productType='" + productType + '\'' +
                ", materialCostPerSquareFoot=" + materialCostPerSquareFoot +
                ", laborCostPerSquareFoot=" + laborCostPerSquareFoot +
                '}';
    }
}
