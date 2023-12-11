package com.yobombel.brewshare.imports.beersmith3;

import com.yobombel.brewshare.beer.Beer;
import com.yobombel.brewshare.beer.ingredient.Ingredient;
import com.yobombel.brewshare.imports.beersmith3.domain.BeerXmlObject;
import com.yobombel.brewshare.imports.beersmith3.domain.BeersmithIngredient;
import com.yobombel.brewshare.imports.beersmith3.domain.BeersmithRecipe;
import com.yobombel.brewshare.imports.beersmith3.domain.Hop;
import com.yobombel.brewshare.util.UnitConversion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BeersmithRecipeToBeerMapper {

    private static final Logger log = LoggerFactory.getLogger(BeersmithRecipeToBeerMapper.class);

    public Beer map(BeersmithRecipe beersmithRecipe) {
        log.info("Mapping beersmith recipe: {}", beersmithRecipe.getName());
        Beer beer = new Beer();
        beer.setName(beersmithRecipe.getName());
        beer.setStyle(beersmithRecipe.getStyle());
        beer.setBatchSize(
                UnitConversion.ouncesToLiters(beersmithRecipe.getBatchVolume()));
        beer.setOriginalGravity(
                UnitConversion.gravityToPlato(beersmithRecipe.getOriginalGravityMeasured()));
        beer.setIngredients(mapIngredients(beersmithRecipe.getIngredients()));
        beer.setAbv(calculateAbv(beersmithRecipe));
        beer.setIbu(calculateIbu(beersmithRecipe));
        return beer;
    }

    private double calculateIbu(BeersmithRecipe beersmithRecipe) {
        return beersmithRecipe.getIngredients().stream()
                .filter(i -> i instanceof Hop)
                .map(h -> ((Hop) h).getIbuContribution())
                .reduce(0.0, Double::sum);
    }

    private double calculateAbv(BeersmithRecipe beersmithRecipe) {
        return (beersmithRecipe.getOriginalGravityMeasured() - beersmithRecipe.getFinishingGravityMeasured()) * 131.25;
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
        ingredient.setAmount(beersmithIngredient.getAmount());
        return ingredient;
    }
}