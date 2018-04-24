package com.sg.flooringmastery.dto;

import java.math.BigDecimal;

public class Tax {

    private String state;
    private BigDecimal taxRate;

    public Tax(String state) {
        this.state = state;
    }

    public Tax(String state, BigDecimal taxRate) {
        this.state = state;
        this.taxRate = taxRate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tax tax = (Tax) o;

        if (state != null ? !state.equals(tax.state) : tax.state != null) return false;
        return taxRate != null ? taxRate.equals(tax.taxRate) : tax.taxRate == null;
    }

    @Override
    public int hashCode() {
        int result = state != null ? state.hashCode() : 0;
        result = 31 * result + (taxRate != null ? taxRate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Tax{" +
                "state='" + state + '\'' +
                ", taxRate=" + taxRate.setScale(2) +
                '}';
    }

    //...
}
