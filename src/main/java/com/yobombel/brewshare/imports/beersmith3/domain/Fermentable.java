package com.yobombel.brewshare.imports.beersmith3.domain;

public class Fermentable extends BeersmithIngredient {

    private double color; //SRM
    private double yield;

    public Fermentable() {
        getEndReaderLoopElements().add("Grain");
        getXmlElementsDictionary().put("F_G_NAME", this::setName);
        getXmlElementsDictionary().put("F_G_AMOUNT", s -> this.setAmount(Double.parseDouble(s)));
        getXmlElementsDictionary().put("F_G_COLOR", s -> this.setColor(Double.parseDouble(s)));
        getXmlElementsDictionary().put("F_G_YIELD", s -> this.setYield(Double.parseDouble(s)));
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



