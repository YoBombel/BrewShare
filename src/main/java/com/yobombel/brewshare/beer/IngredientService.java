package com.yobombel.brewshare.beer;

import com.yobombel.brewshare.beer.domain.Ingredient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    private final com.yobombel.brewshare.beer.IngredientRepository IngredientRepository;

    public IngredientService(IngredientRepository IngredientRepository) {
        this.IngredientRepository = IngredientRepository;
    }

    public List<Ingredient> findAll() {
        return IngredientRepository.findAll();
    }

    public void addIngredient(Ingredient Ingredient) {
        IngredientRepository.save(Ingredient);
    }

    public void deleteAllIngredients() {
        IngredientRepository.deleteAll();
    }

}
