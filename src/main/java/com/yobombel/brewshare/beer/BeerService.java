package com.yobombel.brewshare.beer;

import com.yobombel.brewshare.beer.exception.BeerNotFoundException;
import com.yobombel.brewshare.beer.ingredient.IngredientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        ingredientService.addAllForBeer(beer);
        return beer;
    }

    public List<Beer> addAll(List<Beer> beers){
        log.info("Saving list of beers.");
        List<Beer> savedBeers = new ArrayList<>();
        beers.forEach(beer -> savedBeers.add(add(beer)));
        return savedBeers;
    }

    public List<Beer> findAll() {
        log.info("Finding all beers");
        return beerRepository.findAll();
    }

    public Beer findById(Long id) {
        log.info("Finding by id: {}", id);
        return beerRepository.findById(id).orElseThrow(() -> new BeerNotFoundException(id));
    }

    public Beer update(Long id, Beer updatedBeer) {
        Beer oldBeer = findById(id);
        log.info("Updating beer id: {}", id);
        updatedBeer.setId(id);
        ingredientService.updateEditedIngredients(oldBeer, updatedBeer);
        return beerRepository.save(updatedBeer);
    }

    public void deleteById(Long id) {
        Beer beer = findById(id);
        ingredientService.deleteAllFromList(beer.getIngredients());
        log.info("Deleting beer by id: {}", id);
        beerRepository.deleteById(id);
    }

    public Page<Beer> findBeerPage(Integer page, Integer size){
        log.info("Finding beer page: {}, size: {}", page, size);
        return beerRepository.findAll(PageRequest.of(page, size));
    }

    //TODO: temporary method, delete after implementing better beer examples
    public void deleteAll() {
        log.info("Deleting all beers");
        beerRepository.deleteAll();
    }

}