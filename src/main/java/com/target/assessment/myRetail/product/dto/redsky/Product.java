package com.target.assessment.myRetail.product.dto.redsky;

public class Product {
    private String tcin;
    private Item item;

    public String getTcin() {
        return tcin;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setTcin(String tcin) {
        this.tcin = tcin;
    }
}
