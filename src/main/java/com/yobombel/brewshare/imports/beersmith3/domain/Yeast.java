package com.yobombel.brewshare.imports.beersmith3.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Yeast extends BeersmithIngredient {

    private String lab;
    private String productId;
    private BigDecimal minAttenuation;
    private BigDecimal maxAttenuation;

    public Yeast() {
        getEndReaderLoopElements().add("Yeast");
        getXmlElementsDictionary().put("F_Y_NAME", this::setName);
        getXmlElementsDictionary().put("F_Y_LAB", this::setLab);
        getXmlElementsDictionary().put("F_Y_PRODUCT_ID", this::setProductId);
        getXmlElementsDictionary().put("F_Y_AMOUNT", s -> this.setAmount(BigDecimal.valueOf(Double.parseDouble(s))));
        getXmlElementsDictionary().put("F_Y_MIN_ATTENUATION", s -> this.setMinAttenuation(BigDecimal.valueOf(Double.parseDouble(s))));
        getXmlElementsDictionary().put("F_Y_MAX_ATTENUATION", s -> this.setMaxAttenuation(BigDecimal.valueOf(Double.parseDouble(s))));
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

    public BigDecimal getMinAttenuation() {
        return minAttenuation;
    }

    public void setMinAttenuation(BigDecimal minAttenuation) {
        this.minAttenuation = minAttenuation.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getMaxAttenuation() {
        return maxAttenuation;
    }

    public void setMaxAttenuation(BigDecimal maxAttenuation) {
        this.maxAttenuation = maxAttenuation.setScale(2, RoundingMode.HALF_UP);
    }
}
