package com.yobombel.brewshare.imports.beersmith3.domain;

import java.math.BigDecimal;
import java.util.List;

public class BeersmithRecipe {

    private String name;
    private String style;
    private BigDecimal batchSize; //liters
    private double originalGravity; //plato
    private double color; //ebc
    private double abv;
    private double ibu;

    private double efficiency; //batch efficiency
    private List<BeersmithIngredient> ingredientList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public BigDecimal getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(BigDecimal batchSize) {
        this.batchSize = batchSize;
    }

    public double getOriginalGravity() {
        return originalGravity;
    }

    public void setOriginalGravity(double originalGravity) {
        this.originalGravity = originalGravity;
    }

    public double getColor() {
        return color;
    }

    public void setColor(double color) {
        this.color = color;
    }

    public double getAbv() {
        return abv;
    }

    public void setAbv(double abv) {
        this.abv = abv;
    }

    public double getIbu() {
        return ibu;
    }

    public void setIbu(double ibu) {
        this.ibu = ibu;
    }

    public double getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(double efficiency) {
        this.efficiency = efficiency;
    }

    public List<BeersmithIngredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<BeersmithIngredient> ingredientList) {
        this.ingredientList = ingredientList;
    }
}