package com.yobombel.brewshare.beer.dto;

import java.util.ArrayList;
import java.util.List;

public class AddBeerDto{

        private String name;
        private String style;
        private double batchSize;
        private double originalGravity;
        private double abv;
        private double ibu;
        private double color;
        private List<AddIngredientDto> ingredientDtos = new ArrayList<>();

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

    public double getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(double batchSize) {
        this.batchSize = batchSize;
    }

    public double getOriginalGravity() {
        return originalGravity;
    }

    public void setOriginalGravity(double originalGravity) {
        this.originalGravity = originalGravity;
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

    public double getColor() {
        return color;
    }

    public void setColor(double color) {
        this.color = color;
    }

    public List<AddIngredientDto> getIngredientDtos() {
        return ingredientDtos;
    }

    public void setIngredientDtos(List<AddIngredientDto> ingredientDtos) {
        this.ingredientDtos = ingredientDtos;
    }
}
