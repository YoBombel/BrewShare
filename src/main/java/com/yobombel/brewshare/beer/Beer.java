package com.yobombel.brewshare.beer;

import com.yobombel.brewshare.beer.ingredient.Ingredient;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
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

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 64, message = "Name cannot be longer than 64 characters")
    @Column(name = COLUMN_PREFIX + "name")
    private String name;

    @Size(max = 64, message = "Style cannot be longer than 64 characters")
    @Column(name = COLUMN_PREFIX + "style")
    private String style;

    @DecimalMin(value = "0.0", message = "Batch size cannot be negative")
    @Column(name = COLUMN_PREFIX + "batchSize")
    private double batchSize;

    @DecimalMin(value = "0.0", message = "Original gravity cannot be negative")
    @DecimalMax(value = "99.9", message = "Original gravity cannot exceed 99.9")
    @Column(name = COLUMN_PREFIX + "originalGravity")
    private double originalGravity;

    @DecimalMin(value = "0.0", message = "Alcohol content cannot be negative")
    @DecimalMax(value = "96", message = "Alcohol content cannot exceed 96")
    @Column(name = COLUMN_PREFIX + "abv")
    private double abv;

    @DecimalMin(value = "0.0", message = "IBU cannot be negative")
    @Column(name = COLUMN_PREFIX + "ibu")
    private double ibu;

    @DecimalMin(value = "0.0", message = "Color cannot be negative")
    @Column(name = COLUMN_PREFIX + "color")
    private double color;

    @OneToMany(mappedBy = "beer", fetch = FetchType.EAGER)
    private List<Ingredient> ingredients = new ArrayList<>();

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