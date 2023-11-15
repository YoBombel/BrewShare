package com.yobombel.brewshare.beer.domain;

import jakarta.persistence.*;

import static com.yobombel.brewshare.beer.domain.Ingredient.TABLE_NAME;

@Entity
@Table(name = TABLE_NAME)
public class Ingredient {

    public static final String TABLE_NAME = "ingredient";
    public static final String COLUMN_PREFIX = "i_";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = COLUMN_PREFIX + "id")
    private Long id;

    @Column(name = COLUMN_PREFIX + "name")
    private String name;

    @Column(name = COLUMN_PREFIX + "amount")
    private double amount;

    public Ingredient() {
    }

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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}