package com.yobombel.brewshare.beer.ingredient;

import com.yobombel.brewshare.beer.Beer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private static final Logger log = LoggerFactory.getLogger(IngredientService.class);


    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public Ingredient add(Ingredient entity) {
        return ingredientRepository.save(entity);
    }

    public List<Ingredient> findAll() {
        log.info("Finding all ingredients");
        return ingredientRepository.findAll();
    }

    public void addAllFromList(Beer beer) {
        setIngredientToBeerReference(beer.getIngredients(), beer);
        log.info("Saving all ingredients from list");
        ingredientRepository.saveAll(beer.getIngredients());
    }

    public void updateEditedIngredients(Beer oldBeer, Beer editedBeer) {
        deleteAllFromList(oldBeer.getIngredients());
        setIngredientToBeerReference(editedBeer.getIngredients(), editedBeer);
        ingredientRepository.saveAll(editedBeer.getIngredients());
    }

    public void delete(Ingredient ingredient) {
        log.info("Deleting ingredient, id: {}", ingredient.getId());
        ingredientRepository.delete(ingredient);
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