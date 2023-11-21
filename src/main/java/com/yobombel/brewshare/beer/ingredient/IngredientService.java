package com.yobombel.brewshare.beer.ingredient;

import com.yobombel.brewshare.CRUDService;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Ingredient> findAll() {
        return null;
    }

    @Override
    public Ingredient findById(Long aLong) {
        return null;
    }

    @Override
    public void update(Long aLong, Ingredient entity) {

    }

    @Override
    public void deleteById(Long aLong) {

    }

    //TODO - temporary method, delete after implementing better beer examples
    public void deleteAllIngredients() {
        ingredientRepository.deleteAll();
    }

}