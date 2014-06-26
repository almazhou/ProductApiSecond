package com.thoughtworks.zhouxuan.domain;

public class Pricing {
    private int id;
    private double amount;
    private int productId;

    public Pricing(double amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }


    public double getAmount() {
        return amount;
    }

    public int getProductId() {
        return productId;
    }
}
