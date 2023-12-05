package com.yobombel.brewshare.imports.beersmith3.domain;

import java.math.BigDecimal;

public class Beersmith3Hop extends BeersmithIngredient {

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
