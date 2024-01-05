package com.yobombel.brewshare.imports.beersmith3.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BeersmithRecipe extends BeerXmlObject {

    private String name;
    private String style;
    private String brewer;
    private LocalDate date;
    private BigDecimal batchVolume;
    private BigDecimal volumeMeasured;
    private BigDecimal finalVolumeMeasured;
    private BigDecimal desiredOriginalGravity;
    private BigDecimal originalGravityMeasured;
    private BigDecimal finishingGravityMeasured;
    private BigDecimal boilVolumeMeasured;
    private BigDecimal efficiency;
    private List<BeersmithIngredient> ingredients;

    public BeersmithRecipe() {
        getEndReaderLoopElements().add("Recipe");
        this.ingredients = new ArrayList<>();
        getXmlElementsDictionary().put("F_R_NAME", this::setName);
        getXmlElementsDictionary().put("F_S_NAME", this::setStyle);
        getXmlElementsDictionary().put("F_R_BREWER", this::setBrewer);
        getXmlElementsDictionary().put("F_R_DATE", d -> this.setDate(LocalDate.parse(d)));
        getXmlElementsDictionary().put("F_R_VOLUME_MEASURED", s -> this.setVolumeMeasured(BigDecimal.valueOf(Double.parseDouble(s))));
        getXmlElementsDictionary().put("F_R_FINAL_VOL_MEASURED", s -> this.setFinalVolumeMeasured(BigDecimal.valueOf(Double.parseDouble(s))));
        getXmlElementsDictionary().put("F_R_OG_MEASURED", s -> this.setOriginalGravityMeasured(BigDecimal.valueOf(Double.parseDouble(s))));
        getXmlElementsDictionary().put("F_R_FG_MEASURED", s -> this.setFinishingGravityMeasured(BigDecimal.valueOf(Double.parseDouble(s))));
        getXmlElementsDictionary().put("F_R_BOIL_VOL_MEASURED", s -> this.setBoilVolumeMeasured(BigDecimal.valueOf(Double.parseDouble(s))));
        getXmlElementsDictionary().put("F_R_DESIRED_OG", s -> this.setDesiredOriginalGravity(BigDecimal.valueOf(Double.parseDouble(s))));
        getXmlElementsDictionary().put("F_E_BATCH_VOL", s -> this.setBatchVolume(BigDecimal.valueOf(Double.parseDouble(s))));
        getXmlElementsDictionary().put("F_E_EFFICIENCY", s -> this.setEfficiency(BigDecimal.valueOf(Double.parseDouble(s))));
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

    public BigDecimal getVolumeMeasured() {
        return volumeMeasured;
    }

    public void setVolumeMeasured(BigDecimal volumeMeasured) {
        this.volumeMeasured = volumeMeasured;
    }

    public BigDecimal getFinalVolumeMeasured() {
        return finalVolumeMeasured;
    }

    public void setFinalVolumeMeasured(BigDecimal finalVolumeMeasured) {
        this.finalVolumeMeasured = finalVolumeMeasured;
    }

    public BigDecimal getOriginalGravityMeasured() {
        return originalGravityMeasured;
    }

    public void setOriginalGravityMeasured(BigDecimal originalGravityMeasured) {
        this.originalGravityMeasured = originalGravityMeasured;
    }

    public BigDecimal getFinishingGravityMeasured() {
        return finishingGravityMeasured;
    }

    public void setFinishingGravityMeasured(BigDecimal finishingGravityMeasured) {
        this.finishingGravityMeasured = finishingGravityMeasured;
    }

    public BigDecimal getBoilVolumeMeasured() {
        return boilVolumeMeasured;
    }

    public void setBoilVolumeMeasured(BigDecimal boilVolumeMeasured) {
        this.boilVolumeMeasured = boilVolumeMeasured;
    }

    public BigDecimal getDesiredOriginalGravity() {
        return desiredOriginalGravity;
    }

    public void setDesiredOriginalGravity(BigDecimal desiredOriginalGravity) {
        this.desiredOriginalGravity = desiredOriginalGravity;
    }

    public BigDecimal getBatchVolume() {
        return batchVolume;
    }

    public void setBatchVolume(BigDecimal batchVolume) {
        this.batchVolume = batchVolume;
    }

    public BigDecimal getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(BigDecimal efficiency) {
        this.efficiency = efficiency;
    }

    public List<BeersmithIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<BeersmithIngredient> ingredients) {
        this.ingredients = ingredients;
    }

}