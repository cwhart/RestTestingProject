package com.sg.hotelreservations.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

public class AddOnBillDetail {

    Long id;
    Tax tax;
    Promo promo;
    AddOnRate addOnRate;
    Bill bill;
    BigDecimal taxAmount;
    BigDecimal price;
    LocalDate transactionDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }

    public Promo getPromo() {
        return promo;
    }

    public void setPromo(Promo promo) {
        this.promo = promo;
    }

    public AddOnRate getAddOnRate() {
        return addOnRate;
    }

    public void setAddOnRate(AddOnRate addOnRate) {
        this.addOnRate = addOnRate;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getPrice() {
        return price.setScale(2, RoundingMode.HALF_UP);
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddOnBillDetail that = (AddOnBillDetail) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(tax, that.tax) &&
                Objects.equals(promo, that.promo) &&
                Objects.equals(addOnRate, that.addOnRate) &&
                Objects.equals(bill, that.bill) &&
                Objects.equals(taxAmount, that.taxAmount) &&
                Objects.equals(price, that.price) &&
                Objects.equals(transactionDate, that.transactionDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, tax, promo, addOnRate, bill, taxAmount, price, transactionDate);
    }
}
