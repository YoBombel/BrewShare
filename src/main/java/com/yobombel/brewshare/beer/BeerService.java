package com.yobombel.brewshare.beer;

import com.yobombel.brewshare.CRUDService;
import com.yobombel.brewshare.beer.exception.BeerNotFoundException;
import com.yobombel.brewshare.beer.ingredient.IngredientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BeerService implements CRUDService<Beer, Long> {

    private final BeerRepository beerRepository;
    private final IngredientService ingredientService;
    private static final Logger log = LoggerFactory.getLogger(BeerService.class);

    public BeerService(BeerRepository beerRepository, IngredientService ingredientService) {
        this.beerRepository = beerRepository;
        this.ingredientService = ingredientService;
    }

    @Override
    public Long add(Beer entity) {
        log.info("Saving beer: {}", entity.getName());
        return beerRepository.save(entity)
                .getId();
    }

    @Override
    public List<Beer> findAll() {
        log.info("Finding all beers");
        return beerRepository.findAll();
    }

    @Override
    public Beer findById(Long id) {
        log.info("Finding by id: {}", id);
        return beerRepository.findById(id).orElseThrow(() -> new BeerNotFoundException(id));
    }

    @Override
    public void update(Long id, Beer editedBeer) {
        log.info("Updating beer id: {}", id);
        editedBeer.setId(id);
        beerRepository.save(editedBeer);
    }

    @Override
    public void deleteById(Long id) {
        Beer beer = findById(id);
        ingredientService.deleteAllFromList(beer.getIngredients());
        log.info("Deleting beer by id: {}", id);
        beerRepository.deleteById(id);
    }

    //TODO - temporary method, delete after implementing better beer examples
    public void deleteAll() {
        log.info("Deleting all beers");
        beerRepository.deleteAll();
    }


}