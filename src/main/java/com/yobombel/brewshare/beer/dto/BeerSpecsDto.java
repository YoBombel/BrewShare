package com.yobombel.brewshare.beer.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BeerSpecsDto {

    private Long id;
    private String name;
    private String style;
    private BigDecimal batchSize;
    private BigDecimal originalGravity;
    private BigDecimal abv;
    private BigDecimal ibu;
    private BigDecimal color;

    public BeerSpecsDto(Long id, String name, String style, BigDecimal batchSize, BigDecimal originalGravity, BigDecimal abv, BigDecimal ibu, BigDecimal color) {
        this.id = id;
        this.name = name;
        this.style = style;
        this.batchSize = batchSize.setScale(2, RoundingMode.HALF_UP);
        this.originalGravity = originalGravity.setScale(2, RoundingMode.HALF_UP);
        this.abv = abv.setScale(2, RoundingMode.HALF_UP);
        this.ibu = ibu.setScale(2, RoundingMode.HALF_UP);
        this.color = color.setScale(2, RoundingMode.HALF_UP);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStyle() {
        return style;
    }

    public BigDecimal getBatchSize() {
        return batchSize;
    }

    public BigDecimal getOriginalGravity() {
        return originalGravity;
    }

    public BigDecimal getAbv() {
        return abv;
    }

    public BigDecimal getIbu() {
        return ibu;
    }

    public BigDecimal getColor() {
        return color;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void setBatchSize(BigDecimal batchSize) {
        this.batchSize = batchSize.setScale(2, RoundingMode.HALF_UP);
    }

    public void setOriginalGravity(BigDecimal originalGravity) {
        this.originalGravity = originalGravity.setScale(2, RoundingMode.HALF_UP);
    }

    public void setAbv(BigDecimal abv) {
        this.abv = abv.setScale(2, RoundingMode.HALF_UP);
    }

    public void setIbu(BigDecimal ibu) {
        this.ibu = ibu.setScale(2, RoundingMode.HALF_UP);
    }

    public void setColor(BigDecimal color) {
        this.color = color.setScale(2, RoundingMode.HALF_UP);
    }
}