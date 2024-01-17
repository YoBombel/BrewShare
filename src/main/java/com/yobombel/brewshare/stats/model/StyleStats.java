package com.yobombel.brewshare.stats.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class StyleStats {

    private Map<String, BigDecimal> familiesPercentage = new HashMap<>();
    private Map<String, BigDecimal> colorsPercentage = new HashMap<>();
    private Map<String, BigDecimal> fermentationTypePercentage = new HashMap<>();

    public StyleStats() {
    }

    public StyleStats(Map<String, BigDecimal> familiesPercentage, Map<String, BigDecimal> colorsPercentage, Map<String, BigDecimal> fermentationTypePercentage) {
        this.familiesPercentage = familiesPercentage;
        this.colorsPercentage = colorsPercentage;
        this.fermentationTypePercentage = fermentationTypePercentage;
    }

    public Map<String, BigDecimal> getFamiliesPercentage() {
        return familiesPercentage;
    }

    public void setFamiliesPercentage(Map<String, BigDecimal> familiesPercentage) {
        this.familiesPercentage = familiesPercentage;
    }

    public Map<String, BigDecimal> getColorsPercentage() {
        return colorsPercentage;
    }

    public void setColorsPercentage(Map<String, BigDecimal> colorsPercentage) {
        this.colorsPercentage = colorsPercentage;
    }

    public Map<String, BigDecimal> getFermentationTypePercentage() {
        return fermentationTypePercentage;
    }

    public void setFermentationTypePercentage(Map<String, BigDecimal> fermentationTypePercentage) {
        this.fermentationTypePercentage = fermentationTypePercentage;
    }

}