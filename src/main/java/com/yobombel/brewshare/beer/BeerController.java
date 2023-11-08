package com.yobombel.brewshare.beer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BeerController {

    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping("")
    public String allBeers(Model model) {
        model.addAttribute("allBeers", beerService.findAll());
        return "allBeers";
    }

}