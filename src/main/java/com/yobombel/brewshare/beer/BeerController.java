package com.yobombel.brewshare.beer;

import com.yobombel.brewshare.beer.ingredient.Ingredient;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/beer/")
public class BeerController {

    private static final int BEERS_PER_PAGE = 10;

    private final BeerService beerService;
    private static final Logger log = LoggerFactory.getLogger(BeerController.class);

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    //READ
    @GetMapping("all")
    public String allBeers(Model model, @RequestParam(defaultValue = "1") Integer page) {
        log.info("Request for all beers");
        if(page < 1) page = 1;
        Page<Beer> beers = beerService.findBeerPage(page - 1, BEERS_PER_PAGE);
        model.addAttribute("beers", beers);
        model.addAttribute("threeClosestPages", getClosestPages(page, beers));
        return "allBeers";
    }

    @GetMapping("id/{id}")
    public String viewBeerDetails(Model model, @PathVariable Long id) {
        log.info("Request for beer id: {}", id);
        model.addAttribute("beer", beerService.findById(id));
        return "beerDetails";
    }


    //CREATE
    @GetMapping("new")
    public String newBeerForm(Beer beer) {
        log.info("New beer form request");
        return "newBeer";
    }

    @PostMapping("new")
    public String newBeer(@Valid Beer beer) {
        log.info("Request to post new beer, name: {}", beer.getName());
        Long id = beerService.add(beer).getId();
        return "redirect:/beer/id/" + id;
    }

    @PostMapping(value = "/new", params = {"addIngredient"})
    public String addRow(Beer beer, BindingResult bindingResult) {
        beer.getIngredients().add(new Ingredient());
        return "newBeer";
    }

    @PostMapping(value = "/new", params = {"removeIngredient"})
    public String removeIngredient(
            Beer beer,
            BindingResult bindingResult,
            @RequestParam(value = "removeIngredient", required = false) Integer ingredientId) {
        beer.getIngredients().remove(ingredientId.intValue());
        return "newBeer";
    }

    //EDIT
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

    @PutMapping(value = "/edit/{id}", params = {"addEditIngredient"})
    public String editAddIngredient(@PathVariable Long id, Beer beer, BindingResult bindingResult) {
        beer.getIngredients().add(new Ingredient());
        return "editBeer";
    }

    @PutMapping(value = "/edit/{id}", params = {"removeEditIngredient"})
    public String editRemoveIngredient(@PathVariable Long id,
                                       Beer beer,
                                       BindingResult bindingResult,
                                       @RequestParam(value = "removeEditIngredient", required = false) Integer ingredientId) {
        beer.getIngredients().remove(ingredientId.intValue());
        return "editBeer";
    }

    //DELETE
    @DeleteMapping("id/{id}")
    public String deleteBeerById(@PathVariable Long id) {
        log.info("Request to delete beer, id: {}", id);
        beerService.deleteById(id);
        return "redirect:/beer/all";
    }

    private List<Integer> getClosestPages(Integer page, Page<Beer> beers) {
        int totalPages = beers.getTotalPages();
        List<Integer> threeClosestPage;
        if (beers.getTotalPages() < 3) {
            threeClosestPage = List.of(1, 2);
        } else if (page == 1) {
            threeClosestPage = List.of(page, page + 1, page + 2);
        } else if (page >= totalPages) {
            threeClosestPage = List.of(totalPages - 2, totalPages - 1, totalPages);
        } else {
            threeClosestPage = List.of(page - 1, page, page + 1);
        }
        return threeClosestPage;
    }
}