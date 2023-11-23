package com.yobombel.brewshare.beer.ingredient;

import com.yobombel.brewshare.CRUDService;
import com.yobombel.brewshare.beer.BeerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService implements CRUDService<Ingredient, Long> {

    private final IngredientRepository ingredientRepository;
    private static final Logger log = LoggerFactory.getLogger(BeerService.class);


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
        log.info("Finding all ingredients");
        return ingredientRepository.findAll();
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

    public void deleteAllFromList(List<Ingredient> ingredients){
        log.info("Deleting all ingredients from list");
        ingredientRepository.deleteAll(ingredients);
    }

    //TODO - temporary method, delete after implementing better beer examples
    public void deleteAllIngredients() {
        ingredientRepository.deleteAll();
    }

}