package com.yobombel.brewshare.beer.dto;

import java.math.BigDecimal;

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
        this.batchSize = batchSize;
        this.originalGravity = originalGravity;
        this.abv = abv;
        this.ibu = ibu;
        this.color = color;
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
        this.batchSize = batchSize;
    }

    public void setOriginalGravity(BigDecimal originalGravity) {
        this.originalGravity = originalGravity;
    }

    public void setAbv(BigDecimal abv) {
        this.abv = abv;
    }

    public void setIbu(BigDecimal ibu) {
        this.ibu = ibu;
    }

    public void setColor(BigDecimal color) {
        this.color = color;
    }
}