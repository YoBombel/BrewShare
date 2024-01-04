package com.yobombel.brewshare.beer;

import com.yobombel.brewshare.beer.ingredient.Ingredient;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    private BigDecimal batchSize;

    @DecimalMin(value = "0.0", message = "Original gravity cannot be negative")
    @DecimalMax(value = "99.9", message = "Original gravity cannot exceed 99.9")
    @Column(name = COLUMN_PREFIX + "originalGravity")
    private BigDecimal originalGravity;

    @DecimalMin(value = "0.0", message = "Alcohol content cannot be negative")
    @DecimalMax(value = "96", message = "Alcohol content cannot exceed 96")
    @Column(name = COLUMN_PREFIX + "abv")
    private BigDecimal abv;

    @DecimalMin(value = "0.0", message = "IBU cannot be negative")
    @Column(name = COLUMN_PREFIX + "ibu")
    private BigDecimal ibu;

    @DecimalMin(value = "0.0", message = "Color cannot be negative")
    @Column(name = COLUMN_PREFIX + "color")
    private BigDecimal color;

    //TODO FIX - EAGER only for tests
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

    public BigDecimal getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(BigDecimal batchSize) {
        this.batchSize = batchSize.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getOriginalGravity() {
        return originalGravity;
    }

    public void setOriginalGravity(BigDecimal originalGravity) {
        this.originalGravity = originalGravity.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getAbv() {
        return abv;
    }

    public void setAbv(BigDecimal abv) {
        this.abv = abv.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getIbu() {
        return ibu;
    }

    public void setIbu(BigDecimal ibu) {
        this.ibu = ibu.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getColor() {
        return color;
    }

    public void setColor(BigDecimal color) {
        this.color = color.setScale(2, RoundingMode.HALF_UP);
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Beer beer)) return false;

        if (getId() != null ? !getId().equals(beer.getId()) : beer.getId() != null) return false;
        if (!getName().equals(beer.getName())) return false;
        if (getStyle() != null ? !getStyle().equals(beer.getStyle()) : beer.getStyle() != null) return false;
        if (getBatchSize() != null ? !getBatchSize().equals(beer.getBatchSize()) : beer.getBatchSize() != null)
            return false;
        if (getOriginalGravity() != null ? !getOriginalGravity().equals(beer.getOriginalGravity()) : beer.getOriginalGravity() != null)
            return false;
        if (getAbv() != null ? !getAbv().equals(beer.getAbv()) : beer.getAbv() != null) return false;
        if (getIbu() != null ? !getIbu().equals(beer.getIbu()) : beer.getIbu() != null) return false;
        if (getColor() != null ? !getColor().equals(beer.getColor()) : beer.getColor() != null) return false;
        return getIngredients() != null ? getIngredients().equals(beer.getIngredients()) : beer.getIngredients() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + getName().hashCode();
        result = 31 * result + (getStyle() != null ? getStyle().hashCode() : 0);
        result = 31 * result + (getBatchSize() != null ? getBatchSize().hashCode() : 0);
        result = 31 * result + (getOriginalGravity() != null ? getOriginalGravity().hashCode() : 0);
        result = 31 * result + (getAbv() != null ? getAbv().hashCode() : 0);
        result = 31 * result + (getIbu() != null ? getIbu().hashCode() : 0);
        result = 31 * result + (getColor() != null ? getColor().hashCode() : 0);
        result = 31 * result + (getIngredients() != null ? getIngredients().hashCode() : 0);
        return result;
    }
}