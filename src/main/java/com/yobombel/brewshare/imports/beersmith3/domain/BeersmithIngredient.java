package com.yobombel.brewshare.imports.beersmith3.domain;

import java.math.BigDecimal;

public abstract class BeersmithIngredient extends BeerXmlObject{

    private String name = "";
    private BigDecimal amount = BigDecimal.ZERO; //ounces

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