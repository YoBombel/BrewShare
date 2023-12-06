package com.yobombel.brewshare.imports.beersmith3.domain;

public class Hop extends BeersmithIngredient {

    private double alpha;
    private double ibuContribution;
    private double boilTime;
    private boolean isDryAddition;
    private double dryHopTime;

    public Hop() {
        getEndReaderLoopElements().add("Hops");
        getXmlElementsDictionary().put("F_H_NAME", this::setName);
        getXmlElementsDictionary().put("F_H_AMOUNT", s -> this.setAmount(Double.parseDouble(s)));
        getXmlElementsDictionary().put("F_H_ALPHA", s -> this.setAlpha(Double.parseDouble(s)));
        getXmlElementsDictionary().put("F_H_BOIL_TIME", s -> this.setBoilTime(Double.parseDouble(s)));
        getXmlElementsDictionary().put("F_H_IBU_CONTRIB", s -> this.setIbuContribution(Double.parseDouble(s)));
        getXmlElementsDictionary().put("F_H_DRY_PHASE", s -> this.setDryAddition(Boolean.parseBoolean(s)));
        getXmlElementsDictionary().put("F_H_DRY_HOP_TIME", s -> this.setDryHopTime(Double.parseDouble(s)));
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public double getIbuContribution() {
        return ibuContribution;
    }

    public void setIbuContribution(double ibuContribution) {
        this.ibuContribution = ibuContribution;
    }

    public double getBoilTime() {
        return boilTime;
    }

    public void setBoilTime(double boilTime) {
        this.boilTime = boilTime;
    }

    public boolean isDryAddition() {
        return isDryAddition;
    }

    public void setDryAddition(boolean dryAddition) {
        isDryAddition = dryAddition;
    }

    public double getDryHopTime() {
        return dryHopTime;
    }

    public void setDryHopTime(double dryHopTime) {
        this.dryHopTime = dryHopTime;
    }
}
