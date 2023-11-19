package com.yobombel.brewshare.beer.ingredient;

import com.yobombel.brewshare.CRUDService;
import org.springframework.stereotype.Service;

@Service
public class IngredientService implements CRUDService<Ingredient, Long> {

    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Long add(Ingredient entity) {
        return ingredientRepository.save(entity)
                .getId();
    }

    public void addIngredient(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }

    //TODO - temporary method, delete after implementing better beer examples
    public void deleteAllIngredients() {
        ingredientRepository.deleteAll();
    }

}