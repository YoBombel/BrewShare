package com.yobombel.brewshare.beer;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
public class BeerAPI {

    private final BeerService beerService;

    public BeerAPI(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping("all")
    public List<Beer> viewAllBeers() {
        return beerService.findAll();
    }

    @GetMapping("id/{id}")
    public Beer findBeerById(@PathVariable Long id) {
        return beerService.findById(id);
    }

    @PostMapping("add")
    public void addBeer(@RequestBody Beer beer) {
        beerService.add(beer);
    }

    @DeleteMapping("id/{id}")
    public void deleteById(@PathVariable Long id){
        beerService.deleteById(id);
    }

}