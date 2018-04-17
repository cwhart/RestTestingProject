package com.sg.flooringmastery.dto;

public class Tax {

    private String state;
    private double taxRate;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tax tax = (Tax) o;

        if (Double.compare(tax.taxRate, taxRate) != 0) return false;
        return state != null ? state.equals(tax.state) : tax.state == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = state != null ? state.hashCode() : 0;
        temp = Double.doubleToLongBits(taxRate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Tax{" +
                "state='" + state + '\'' +
                ", taxRate=" + taxRate +
                '}';
    }
}
