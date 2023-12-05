package com.yobombel.brewshare.imports.beersmith3.domain;

import java.math.BigDecimal;

public class BeersmithFermentable extends BeersmithIngredient {

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