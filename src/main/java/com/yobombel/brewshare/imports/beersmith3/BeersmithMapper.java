package com.yobombel.brewshare.imports.beersmith3;

import com.yobombel.brewshare.beer.Beer;
import com.yobombel.brewshare.beer.ingredient.Ingredient;
import com.yobombel.brewshare.imports.beersmith3.domain.BeersmithIngredient;
import com.yobombel.brewshare.imports.beersmith3.domain.BeersmithRecipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.yobombel.brewshare.util.UnitConversion.*;

@Component
public class BeersmithMapper {

    private static final Logger log = LoggerFactory.getLogger(BeersmithMapper.class);
    private final BeersmithSpecCalculations specCalculations;

    public BeersmithMapper(BeersmithSpecCalculations specCalculations) {
        this.specCalculations = specCalculations;
    }

    public Beer map(BeersmithRecipe beersmithRecipe) {
        log.info("Mapping beersmith recipe: {}", beersmithRecipe.getName());
        Beer beer = new Beer();
        beer.setName(beersmithRecipe.getName());
        beer.setStyle(beersmithRecipe.getStyle());
        beer.setBatchSize(
                fluidOuncesToLiters(beersmithRecipe.getBatchVolume()));
        beer.setOriginalGravity(
                gravityToPlato(beersmithRecipe.getOriginalGravityMeasured()));
        beer.setIngredients(mapIngredients(beersmithRecipe.getIngredients()));
        beer.setAbv(
                specCalculations.calculateAbv(beersmithRecipe));
        beer.setIbu(
                specCalculations.calculateIbu(beersmithRecipe));
        beer.setColor(
                srmToEbc(
                        specCalculations.calculateColor(beersmithRecipe)));
        return beer;
    }

    private List<Ingredient> mapIngredients(List<BeersmithIngredient> beersmithIngredients) {
        return beersmithIngredients.stream()
                .map(this::mapIngredient)
                .toList();
    }

    private Ingredient mapIngredient(BeersmithIngredient beersmithIngredient) {
        log.trace("Mapping ingredient: {}", beersmithIngredient.getName());
        Ingredient ingredient = new Ingredient();
        ingredient.setName(beersmithIngredient.getName());
        ingredient.setAmount(
                ouncesToGrams(beersmithIngredient.getAmount()));
        return ingredient;
    }
}