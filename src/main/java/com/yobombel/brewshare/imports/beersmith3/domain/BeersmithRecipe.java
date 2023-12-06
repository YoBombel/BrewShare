package com.yobombel.brewshare.imports.beersmith3.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BeersmithRecipe extends BeerXmlObject {

    private String name;
    private String style;
    private String brewer;
    private LocalDate date;
    private double batchVolume;
    private double volumeMeasured;
    private double finalVolumeMeasured;
    private double desiredOriginalGravity;
    private double originalGravityMeasured;
    private double finishingGravityMeasured;
    private double boilVolumeMeasured;
    private double efficiency;
    private List<BeersmithIngredient> ingredients;

    public BeersmithRecipe() {
        getEndReaderLoopElements().add("Recipe");
        this.ingredients = new ArrayList<>();
        getXmlElementsDictionary().put("F_R_NAME", this::setName);
        getXmlElementsDictionary().put("F_S_NAME", this::setStyle);
        getXmlElementsDictionary().put("F_R_BREWER", this::setBrewer);
        getXmlElementsDictionary().put("F_R_DATE", d -> this.setDate(LocalDate.parse(d)));
        getXmlElementsDictionary().put("F_R_VOLUME_MEASURED", s -> this.setVolumeMeasured(Double.parseDouble(s)));
        getXmlElementsDictionary().put("F_R_FINAL_VOL_MEASURED", s -> this.setFinalVolumeMeasured(Double.parseDouble(s)));
        getXmlElementsDictionary().put("F_R_OG_MEASURED", s -> this.setOriginalGravityMeasured(Double.parseDouble(s)));
        getXmlElementsDictionary().put("F_R_FG_MEASURED", s -> this.setFinishingGravityMeasured(Double.parseDouble(s)));
        getXmlElementsDictionary().put("F_R_BOIL_VOL_MEASURED", s -> this.setBoilVolumeMeasured(Double.parseDouble(s)));
        getXmlElementsDictionary().put("F_R_DESIRED_OG", s -> this.setDesiredOriginalGravity(Double.parseDouble(s)));
        getXmlElementsDictionary().put("F_E_BATCH_VOL", s -> this.setBatchVolume(Double.parseDouble(s)));
        getXmlElementsDictionary().put("F_E_EFFICIENCY", s -> this.setEfficiency(Double.parseDouble(s)));
    }

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

    public String getBrewer() {
        return brewer;
    }

    public void setBrewer(String brewer) {
        this.brewer = brewer;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getVolumeMeasured() {
        return volumeMeasured;
    }

    public void setVolumeMeasured(double volumeMeasured) {
        this.volumeMeasured = volumeMeasured;
    }

    public double getFinalVolumeMeasured() {
        return finalVolumeMeasured;
    }

    public void setFinalVolumeMeasured(double finalVolumeMeasured) {
        this.finalVolumeMeasured = finalVolumeMeasured;
    }

    public double getOriginalGravityMeasured() {
        return originalGravityMeasured;
    }

    public void setOriginalGravityMeasured(double originalGravityMeasured) {
        this.originalGravityMeasured = originalGravityMeasured;
    }

    public double getFinishingGravityMeasured() {
        return finishingGravityMeasured;
    }

    public void setFinishingGravityMeasured(double finishingGravityMeasured) {
        this.finishingGravityMeasured = finishingGravityMeasured;
    }

    public double getBoilVolumeMeasured() {
        return boilVolumeMeasured;
    }

    public void setBoilVolumeMeasured(double boilVolumeMeasured) {
        this.boilVolumeMeasured = boilVolumeMeasured;
    }

    public double getDesiredOriginalGravity() {
        return desiredOriginalGravity;
    }

    public void setDesiredOriginalGravity(double desiredOriginalGravity) {
        this.desiredOriginalGravity = desiredOriginalGravity;
    }

    public double getBatchVolume() {
        return batchVolume;
    }

    public void setBatchVolume(double batchVolume) {
        this.batchVolume = batchVolume;
    }

    public double getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(double efficiency) {
        this.efficiency = efficiency;
    }

    public List<BeersmithIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<BeersmithIngredient> ingredients) {
        this.ingredients = ingredients;
    }


}
