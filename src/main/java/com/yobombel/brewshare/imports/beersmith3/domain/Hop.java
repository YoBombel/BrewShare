package com.yobombel.brewshare.imports.beersmith3.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Hop extends BeersmithIngredient {

    private BigDecimal alpha;
    private BigDecimal ibuContribution;
    private BigDecimal boilTime;
    private boolean isDryAddition;
    private BigDecimal dryHopTime;

    public Hop() {
        getEndReaderLoopElements().add("Hops");
        getXmlElementsDictionary().put("F_H_NAME", this::setName);
        getXmlElementsDictionary().put("F_H_AMOUNT", s -> this.setAmount(BigDecimal.valueOf(Double.parseDouble(s))));
        getXmlElementsDictionary().put("F_H_ALPHA", s -> this.setAlpha(BigDecimal.valueOf(Double.parseDouble(s))));
        getXmlElementsDictionary().put("F_H_BOIL_TIME", s -> this.setBoilTime(BigDecimal.valueOf(Double.parseDouble(s))));
        getXmlElementsDictionary().put("F_H_IBU_CONTRIB", s -> this.setIbuContribution(BigDecimal.valueOf(Double.parseDouble(s))));
        getXmlElementsDictionary().put("F_H_DRY_PHASE", s -> this.setDryAddition(Boolean.parseBoolean(s)));
        getXmlElementsDictionary().put("F_H_DRY_HOP_TIME", s -> this.setDryHopTime(BigDecimal.valueOf(Double.parseDouble(s))));
    }

    public BigDecimal getAlpha() {
        return alpha;
    }

    public void setAlpha(BigDecimal alpha) {
        this.alpha = alpha.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getIbuContribution() {
        return ibuContribution;
    }

    public void setIbuContribution(BigDecimal ibuContribution) {
        this.ibuContribution = ibuContribution.setScale(2, RoundingMode.HALF_UP);;
    }

    public BigDecimal getBoilTime() {
        return boilTime;
    }

    public void setBoilTime(BigDecimal boilTime) {
        this.boilTime = boilTime.setScale(2, RoundingMode.HALF_UP);;
    }

    public boolean isDryAddition() {
        return isDryAddition;
    }

    public void setDryAddition(boolean dryAddition) {
        isDryAddition = dryAddition;
    }

    public BigDecimal getDryHopTime() {
        return dryHopTime;
    }

    public void setDryHopTime(BigDecimal dryHopTime) {
        this.dryHopTime = dryHopTime.setScale(2, RoundingMode.HALF_UP);;
    }
}
