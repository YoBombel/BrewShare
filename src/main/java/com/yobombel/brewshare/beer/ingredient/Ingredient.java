package com.yobombel.brewshare.beer.ingredient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yobombel.brewshare.beer.Beer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Comparator;

@Entity
@Table(name = Ingredient.TABLE_NAME)
public class Ingredient implements Comparable<Ingredient>{

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

    @Column(name = COLUMN_PREFIX + "amount")
    private double amount;

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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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

        if (Double.compare(that.getAmount(), getAmount()) != 0) return false;
        return getName() != null ? getName().equals(that.getName()) : that.getName() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getName() != null ? getName().hashCode() : 0;
        temp = Double.doubleToLongBits(getAmount());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}