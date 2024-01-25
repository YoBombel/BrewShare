package com.yobombel.brewshare.beer.ingredient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yobombel.brewshare.beer.Beer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Comparator;

import static com.yobombel.brewshare.config.NumberConfig.setDefaultScale;

@Entity
@Table(name = Ingredient.TABLE_NAME)
public class Ingredient implements Comparable<Ingredient> {

    public static final String TABLE_NAME = "ingredient";
    public static final String COLUMN_PREFIX = "i_";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = COLUMN_PREFIX + "id")
    private Long id;

    @JsonIgnore
    @ManyToOne
    private Beer beer;

    @Column(name = COLUMN_PREFIX + "name")
    private String name;

    @Column(name = COLUMN_PREFIX + "amount", precision = 10, scale = 1)
    private BigDecimal amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Beer getBeer() {
        return beer;
    }

    public void setBeer(Beer beer) {
        this.beer = beer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = setDefaultScale(amount);
    }

    @Override
    public int compareTo(@NotNull Ingredient ingredient) {
        return Comparator.comparing(Ingredient::getName)
                .thenComparing(Ingredient::getAmount)
                .compare(this, ingredient);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ingredient that)) return false;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getBeer() != null ? !getBeer().equals(that.getBeer()) : that.getBeer() != null) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        return getAmount() != null ? getAmount().equals(that.getAmount()) : that.getAmount() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getBeer() != null ? getBeer().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getAmount() != null ? getAmount().hashCode() : 0);
        return result;
    }
}