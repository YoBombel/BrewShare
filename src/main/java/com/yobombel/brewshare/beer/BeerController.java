package com.yobombel.brewshare.beer;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/beer/")
public class BeerController {

    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping("all")
    public String allBeers(Model model) {
        model.addAttribute("allBeers", beerService.findAll());
        return "allBeers";
    }

    @GetMapping("id/{id}")
    public String viewBeerDetails(Model model, @PathVariable Long id) {
        model.addAttribute("beer", beerService.findById(id));
        return "beerDetails";
    }

    @GetMapping("new")
    public String newBeer() {
        return "newBeer";
    }

    @PostMapping("new")
    public String newBeer(@Valid Beer beer) {
        Long id = beerService.add(beer);
        return "redirect:/beer/id/" + id;
    }

    @DeleteMapping("id/{id}")
    public String deleteBeerById(@PathVariable Long id) {
        beerService.deleteById(id);
        return "redirect:/beer/all";
    }

    @GetMapping("edit/{id}")
    public String editBeer(Model model, @PathVariable Long id) {
        model.addAttribute("beer", beerService.findById(id));
        return "editBeer";
    }

    @PutMapping("edit/{id}")
    public String editBeer(@PathVariable Long id, @Valid Beer editedBeer) {
        beerService.update(id, editedBeer);
        return "redirect:/beer/id/" + id;
    }

}