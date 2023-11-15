package com.yobombel.brewshare.beer.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = Beer.TABLE_NAME)
public class Beer {

    public static final String TABLE_NAME = "beer";
    public static final String COLUMN_PREFIX = "b_";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = COLUMN_PREFIX + "id")
    private Long id;

    @Column(name = COLUMN_PREFIX + "name")
    private String name;

    @Column(name = COLUMN_PREFIX + "style")
    private String style;

    @Column(name = COLUMN_PREFIX + "batchSize")
    private double batchSize;

    @Column(name = COLUMN_PREFIX + "originalGravity")
    private double originalGravity;

    @Column(name = COLUMN_PREFIX + "abv")
    private double abv;

    @Column(name = COLUMN_PREFIX + "ibu")
    private double ibu;

    @Column(name = COLUMN_PREFIX + "color")
    private double color;

    @OneToMany
    private List<Ingredient> ingredients;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public double getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(double batchSize) {
        this.batchSize = batchSize;
    }

    public double getOriginalGravity() {
        return originalGravity;
    }

    public void setOriginalGravity(double originalGravity) {
        this.originalGravity = originalGravity;
    }

    public double getAbv() {
        return abv;
    }

    public void setAbv(double abv) {
        this.abv = abv;
    }

    public double getIbu() {
        return ibu;
    }

    public void setIbu(double ibu) {
        this.ibu = ibu;
    }

    public double getColor() {
        return color;
    }

    public void setColor(double color) {
        this.color = color;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}