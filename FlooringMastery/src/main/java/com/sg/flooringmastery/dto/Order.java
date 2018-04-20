package com.sg.flooringmastery.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Order {

    private int orderNumber;
    private String customerLastName;
    private LocalDate orderDate;
    private String state;
    private Tax orderTax;
    private Product orderProduct;
    private BigDecimal area;
    private BigDecimal calculatedMaterialCost;
    private BigDecimal calculatedLaborCost;
    private BigDecimal calculatedTaxAmount;
    private BigDecimal totalOrderAmount;

    public Order(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Tax getOrderTax() {
        return orderTax;
    }

    public void setOrderTax(Tax orderTax) {
        this.orderTax = orderTax;
    }

    public Product getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(Product orderProduct) {
        this.orderProduct = orderProduct;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getCalculatedMaterialCost() {
        BigDecimal materialCostPerSquareFoot = orderProduct.getMaterialCostPerSquareFoot();
        return area.multiply(materialCostPerSquareFoot);
    }

    public void setCalculatedMaterialCost(BigDecimal calculatedMaterialCost) {
        this.calculatedMaterialCost = calculatedMaterialCost;
    }

    public BigDecimal getCalculatedLaborCost() {
        BigDecimal laborCostPerSquareFoot = orderProduct.getLaborCostPerSquareFoot();
        return area.multiply(laborCostPerSquareFoot);
    }

    public void setCalculatedLaborCost(BigDecimal calculatedLaborCost) {
        this.calculatedLaborCost = calculatedLaborCost;
    }

    public BigDecimal getCalculatedTaxAmount() {
        BigDecimal taxRate = orderTax.getTaxRate().multiply(BigDecimal.valueOf(.01));
        BigDecimal totalCost = calculatedMaterialCost.add(calculatedLaborCost);
        return totalCost.multiply(taxRate);
    }

    public void setCalculatedTaxAmount(BigDecimal calculatedTaxAmount) {
        this.calculatedTaxAmount = calculatedTaxAmount;
    }

    public BigDecimal getTotalOrderAmount() {
        return calculatedMaterialCost.add(calculatedLaborCost).add(calculatedTaxAmount);
    }

    public void setTotalOrderAmount(BigDecimal totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderNumber != order.orderNumber) return false;
        if (customerLastName != null ? !customerLastName.equals(order.customerLastName) : order.customerLastName != null)
            return false;
        if (orderDate != null ? !orderDate.equals(order.orderDate) : order.orderDate != null) return false;
        if (state != null ? !state.equals(order.state) : order.state != null) return false;
        if (orderTax != null ? !orderTax.equals(order.orderTax) : order.orderTax != null) return false;
        if (orderProduct != null ? !orderProduct.equals(order.orderProduct) : order.orderProduct != null) return false;
        if (area != null ? !area.equals(order.area) : order.area != null) return false;
        if (calculatedMaterialCost != null ? !calculatedMaterialCost.equals(order.calculatedMaterialCost) : order.calculatedMaterialCost != null)
            return false;
        if (calculatedLaborCost != null ? !calculatedLaborCost.equals(order.calculatedLaborCost) : order.calculatedLaborCost != null)
            return false;
        if (calculatedTaxAmount != null ? !calculatedTaxAmount.equals(order.calculatedTaxAmount) : order.calculatedTaxAmount != null)
            return false;
        return totalOrderAmount != null ? totalOrderAmount.equals(order.totalOrderAmount) : order.totalOrderAmount == null;
    }

    @Override
    public int hashCode() {
        int result = orderNumber;
        result = 31 * result + (customerLastName != null ? customerLastName.hashCode() : 0);
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (orderTax != null ? orderTax.hashCode() : 0);
        result = 31 * result + (orderProduct != null ? orderProduct.hashCode() : 0);
        result = 31 * result + (area != null ? area.hashCode() : 0);
        result = 31 * result + (calculatedMaterialCost != null ? calculatedMaterialCost.hashCode() : 0);
        result = 31 * result + (calculatedLaborCost != null ? calculatedLaborCost.hashCode() : 0);
        result = 31 * result + (calculatedTaxAmount != null ? calculatedTaxAmount.hashCode() : 0);
        result = 31 * result + (totalOrderAmount != null ? totalOrderAmount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNumber=" + orderNumber +
                ", customerLastName='" + customerLastName + '\'' +
                ", orderDate=" + orderDate +
                ", state='" + state + '\'' +
                ", orderTax=" + orderTax +
                ", orderProduct=" + orderProduct +
                ", area=" + area.setScale(2) +
                ", calculatedMaterialCost=" + calculatedMaterialCost +
                ", calculatedLaborCost=" + calculatedLaborCost +
                ", calculatedTaxAmount=" + calculatedTaxAmount +
                ", totalOrderAmount=" + totalOrderAmount +
                '}';
    }
}
