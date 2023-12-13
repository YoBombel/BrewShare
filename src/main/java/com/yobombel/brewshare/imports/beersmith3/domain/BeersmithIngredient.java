package com.yobombel.brewshare.imports.beersmith3.domain;

public abstract class BeersmithIngredient extends BeerXmlObject{

    private String name;
    private double amount; //ounces

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}