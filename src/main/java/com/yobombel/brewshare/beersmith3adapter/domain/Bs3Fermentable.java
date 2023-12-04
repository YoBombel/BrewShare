package com.yobombel.brewshare.beersmith3adapter.domain;

import java.math.BigDecimal;

public class Bs3Fermentable extends Bs3Ingredient {

    private String name;
    private BigDecimal amount; //grams
    private double color;
    private double yield;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public double getColor() {
        return color;
    }

    public void setColor(double color) {
        this.color = color;
    }

    public double getYield() {
        return yield;
    }

    public void setYield(double yield) {
        this.yield = yield;
    }
}