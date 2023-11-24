package com.yobombel.brewshare.beer;

import com.yobombel.brewshare.beer.exception.BeerNotFoundException;
import com.yobombel.brewshare.beer.ingredient.Ingredient;
import com.yobombel.brewshare.beer.ingredient.IngredientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BeerService {

    private final BeerRepository beerRepository;
    private final IngredientService ingredientService;
    private static final Logger log = LoggerFactory.getLogger(BeerService.class);

    public BeerService(BeerRepository beerRepository, IngredientService ingredientService) {
        this.beerRepository = beerRepository;
        this.ingredientService = ingredientService;
    }

    public Beer add(Beer beer) {
        log.info("Saving beer: {}", beer.getName());
        beer = beerRepository.save(beer);
        setIngredientsForNewBeer(beer);
        return beer;
    }


    public List<Beer> findAll() {
        log.info("Finding all beers");
        return beerRepository.findAll();
    }

    public Beer findById(Long id) {
        log.info("Finding by id: {}", id);
        return beerRepository.findById(id).orElseThrow(() -> new BeerNotFoundException(id));
    }

    public void update(Long id, Beer editedBeer) {
        log.info("Updating beer id: {}", id);
        editedBeer.setId(id);
        beerRepository.save(editedBeer);
    }

    public void deleteById(Long id) {
        Beer beer = findById(id);
        ingredientService.deleteAllFromList(beer.getIngredients());
        log.info("Deleting beer by id: {}", id);
        beerRepository.deleteById(id);
    }

    //TODO: temporary method, delete after implementing better beer examples
    public void deleteAll() {
        log.info("Deleting all beers");
        beerRepository.deleteAll();
    }

    //TODO: REPURPOSE METHOD TO NEW BEER? MAYBE
    private void setIngredientsForNewBeer(Beer beer) {
        List<Ingredient> ingredients = beer.getIngredients();
        if (!ingredients.isEmpty()) {
            log.info("Saving ingredients for beer");
            ingredientService.addAllFromList(ingredients, beer);
        }
    }

}