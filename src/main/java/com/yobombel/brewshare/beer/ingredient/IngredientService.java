package com.yobombel.brewshare.beer.ingredient;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }

    //TODO - temporary method, delete after implementing better beer examples
    public void deleteAllIngredients() {
        ingredientRepository.deleteAll();
    }

}