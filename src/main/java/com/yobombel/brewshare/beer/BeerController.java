package com.yobombel.brewshare.beer;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/beer/id/{id}")
    public String viewBeerDetails(Model model, @PathVariable Long id) {
        model.addAttribute("beer", beerService.findById(id));
        return "beerDetails";
    }

    @GetMapping("/beer/new")
    public String newBeer(){
        return "newBeer";
    }

    @PostMapping("/beer/new")
    public String newBeer(@Valid Beer beer){
        Long id = beerService.addBeer(beer);
        return "redirect:/beer/id/" + id;
    }

}