package com.yobombel.brewshare.imports.beersmith3.domain;

public class BeersmithFermentable extends BeersmithIngredient {

    private double color;
    private double yield;

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