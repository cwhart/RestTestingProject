package com.sg.vendingmachinespringmvc.model;

import java.math.BigDecimal;

public class Item {

    private BigDecimal itemPrice;
    private String itemName;
    private int itemQuantity;
    private int itemID;

    public Item(int itemId) {
        this.itemID = itemId;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public int getItemID() {
        return itemID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (itemQuantity != item.itemQuantity) return false;
        if (itemID != item.itemID) return false;
        if (itemPrice != null ? !itemPrice.equals(item.itemPrice) : item.itemPrice != null) return false;
        return itemName != null ? itemName.equals(item.itemName) : item.itemName == null;
    }

    @Override
    public int hashCode() {
        int result = itemPrice != null ? itemPrice.hashCode() : 0;
        result = 31 * result + (itemName != null ? itemName.hashCode() : 0);
        result = 31 * result + itemQuantity;
        result = 31 * result + itemID;
        return result;
    }

    @Override
    public String toString() {
        return "Itemid= " + itemID +
                "itemPrice=" + itemPrice +
                ", itemName='" + itemName + '\'' +
                ", itemQuantity=" + itemQuantity;
    }

    //
}
