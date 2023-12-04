package com.yobombel.brewshare.beersmith3adapter.domain;

import java.math.BigDecimal;

public class Bs3Hop extends Bs3Ingredient {

    private String name;
    private BigDecimal amount; //grams
    private double alpha;

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

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }
}
