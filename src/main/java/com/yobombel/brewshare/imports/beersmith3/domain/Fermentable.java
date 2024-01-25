package com.yobombel.brewshare.imports.beersmith3.domain;

import java.math.BigDecimal;

public class Fermentable extends BeersmithIngredient {

    private BigDecimal color = BigDecimal.ZERO; //SRM
    private BigDecimal yield = BigDecimal.ZERO;

    public Fermentable() {
        getEndReaderLoopElements().add("Grain");
        getXmlElementsDictionary().put("F_G_NAME", this::setName);
        getXmlElementsDictionary().put("F_G_AMOUNT", s -> this.setAmount(BigDecimal.valueOf(Double.parseDouble(s))));
        getXmlElementsDictionary().put("F_G_COLOR", s -> this.setColor(BigDecimal.valueOf(Double.parseDouble(s))));
        getXmlElementsDictionary().put("F_G_YIELD", s -> this.setYield(BigDecimal.valueOf(Double.parseDouble(s))));
    }

    public BigDecimal getColor() {
        return color;
    }

    public void setColor(BigDecimal color) {
        this.color = color;
    }

    public BigDecimal getYield() {
        return yield;
    }

    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }
}



