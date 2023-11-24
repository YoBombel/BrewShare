package com.yobombel.brewshare.beer.ingredient;

import com.yobombel.brewshare.beer.Beer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private static final Logger log = LoggerFactory.getLogger(IngredientService.class);


    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public Long add(Ingredient entity) {
        return ingredientRepository.save(entity)
                .getId();
    }

    public List<Ingredient> findAll() {
        log.info("Finding all ingredients");
        return ingredientRepository.findAll();
    }

    public void addAllFromList(List<Ingredient> ingredients, Beer beer) {
        setIngredientToBeerReference(ingredients, beer);
        log.info("Saving all ingredients from list");
        ingredientRepository.saveAll(ingredients);
    }

    public void deleteAllFromList(List<Ingredient> ingredients) {
        log.info("Deleting all ingredients from list");
        ingredientRepository.deleteAll(ingredients);
    }

    //TODO - temporary method, delete after implementing better beer examples
    public void deleteAllIngredients() {
        ingredientRepository.deleteAll();
    }

    private void setIngredientToBeerReference(List<Ingredient> ingredients, Beer beer) {
        ingredients.forEach(i -> i.setBeer(beer));
    }
}