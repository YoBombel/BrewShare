package com.yobombel.brewshare.beer.ingredient;

import com.yobombel.brewshare.beer.Beer;
import jakarta.persistence.*;

@Entity
@Table(name = Ingredient.TABLE_NAME)
public class Ingredient {

    public static final String TABLE_NAME = "ingredient";
    public static final String COLUMN_PREFIX = "i_";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = COLUMN_PREFIX + "id")
    private Long id;

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
}