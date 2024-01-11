package com.yobombel.brewshare.stats.model.enums;

public enum StatType {

    OG("Original gravity", "°Plato"),
    ABV("Alcohol by volume", "%"),
    IBU("International Bitterness Units", "IBU"),
    COLOR("Color ", "EBC");

    private final String value;
    private final String unit;

    StatType(String value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public String getValue() {
        return value;
    }
}