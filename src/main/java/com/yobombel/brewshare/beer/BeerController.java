package com.yobombel.brewshare.beer;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/beer/")
public class BeerController {

    private final BeerService beerService;
    private static final Logger log = LoggerFactory.getLogger(BeerController.class);

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping("all")
    public String allBeers(Model model) {
        log.info("Request for all beers");
        model.addAttribute("allBeers", beerService.findAll());
        return "allBeers";
    }

    @GetMapping("id/{id}")
    public String viewBeerDetails(Model model, @PathVariable Long id) {
        log.info("Request for beer id: {}", id);
        model.addAttribute("beer", beerService.findById(id));
        return "beerDetails";
    }

    @GetMapping("new")
    public String newBeer() {
        log.info("New beer form request");
        return "newBeer";
    }

    @PostMapping("new")
    public String newBeer(@Valid Beer beer) {
        log.info("Request to post new beer, name: {}", beer.getName());
        Long id = beerService.add(beer);
        return "redirect:/beer/id/" + id;
    }

    @DeleteMapping("id/{id}")
    public String deleteBeerById(@PathVariable Long id) {
        log.info("Request to delete beer, id: {}", id);
        beerService.deleteById(id);
        return "redirect:/beer/all";
    }

    @GetMapping("edit/{id}")
    public String editBeer(Model model, @PathVariable Long id) {
        log.info("Edit beer form request, id: {}", id);
        model.addAttribute("beer", beerService.findById(id));
        return "editBeer";
    }

    @PutMapping("edit/{id}")
    public String editBeer(@PathVariable Long id, @Valid Beer editedBeer) {
        log.info("Request to edit beer, id: {}", id);
        beerService.update(id, editedBeer);
        return "redirect:/beer/id/" + id;
    }

}