package com.yobombel.brewshare.beer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
public class BeerAPI {

    private final BeerService beerService;
    private static final Logger log = LoggerFactory.getLogger(BeerAPI.class);


    public BeerAPI(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping("all")
    public List<Beer> viewAllBeers() {
        log.info("GET request view all beers");
        return beerService.findAll();
    }

    @GetMapping("id/{id}")
    public Beer findBeerById(@PathVariable Long id) {
        log.info("GET request find beer by id: {}", id);
        return beerService.findById(id);
    }

    @PostMapping("add")
    public void addBeer(@RequestBody Beer beer) {
        log.info("POST request add beer, name: {}", beer.getName());
        beerService.add(beer);
    }

    @DeleteMapping("id/{id}")
    public void deleteById(@PathVariable Long id){
        log.info("DELETE request beer, id: {}", id);
        beerService.deleteById(id);
    }

}