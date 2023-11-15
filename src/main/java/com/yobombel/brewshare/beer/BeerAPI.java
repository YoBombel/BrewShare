package com.yobombel.brewshare.beer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}