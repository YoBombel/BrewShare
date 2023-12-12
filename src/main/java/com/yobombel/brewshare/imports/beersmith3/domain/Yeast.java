package com.yobombel.brewshare.imports.beersmith3.domain;

public class Yeast extends BeersmithIngredient {

    private String lab;
    private String productId;
    private double minAttenuation;
    private double maxAttenuation;

    public Yeast() {
        getEndReaderLoopElements().add("Yeast");
        getXmlElementsDictionary().put("F_Y_NAME", this::setName);
        getXmlElementsDictionary().put("F_Y_LAB", this::setLab);
        getXmlElementsDictionary().put("F_Y_PRODUCT_ID", this::setProductId);
        getXmlElementsDictionary().put("F_Y_AMOUNT", s -> this.setAmount(Double.parseDouble(s)));
        getXmlElementsDictionary().put("F_Y_MIN_ATTENUATION", s -> this.setMinAttenuation(Double.parseDouble(s)));
        getXmlElementsDictionary().put("F_Y_MAX_ATTENUATION", s -> this.setMaxAttenuation(Double.parseDouble(s)));
    }

    public String getLab() {
        return lab;
    }

    public void setLab(String lab) {
        this.lab = lab;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getMinAttenuation() {
        return minAttenuation;
    }

    public void setMinAttenuation(double minAttenuation) {
        this.minAttenuation = minAttenuation;
    }

    public double getMaxAttenuation() {
        return maxAttenuation;
    }

    public void setMaxAttenuation(double maxAttenuation) {
        this.maxAttenuation = maxAttenuation;
    }
}
