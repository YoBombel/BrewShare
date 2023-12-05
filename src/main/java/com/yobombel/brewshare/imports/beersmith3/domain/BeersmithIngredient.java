package com.yobombel.brewshare.imports.beersmith3.domain;

import java.math.BigDecimal;

public class BeersmithIngredient {

    private String name;
    private BigDecimal amount; //grams

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
}