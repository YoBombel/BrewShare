package com.yobombel.brewshare.imports.beersmith3.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Fermentable extends BeersmithIngredient {

    private BigDecimal color; //SRM
    private BigDecimal yield;

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
        this.color = color.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getYield() {
        return yield;
    }

    public void setYield(BigDecimal yield) {
        this.yield = yield.setScale(2, RoundingMode.HALF_UP);
    }
}



