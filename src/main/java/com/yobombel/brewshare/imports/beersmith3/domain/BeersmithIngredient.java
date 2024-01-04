package com.yobombel.brewshare.imports.beersmith3.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class BeersmithIngredient extends BeerXmlObject{

    private String name;
    private BigDecimal amount; //ounces

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
        this.amount = amount.setScale(2, RoundingMode.HALF_UP);
    }
}