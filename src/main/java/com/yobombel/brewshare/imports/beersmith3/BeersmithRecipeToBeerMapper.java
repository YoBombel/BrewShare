package com.yobombel.brewshare.imports.beersmith3;

import com.yobombel.brewshare.beer.Beer;
import com.yobombel.brewshare.imports.beersmith3.domain.BeersmithRecipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class BeersmithRecipeToBeerMapper {

    private static final Logger log = LoggerFactory.getLogger(BeersmithRecipeToBeerMapper.class);

    public Beer map(BeersmithRecipe beersmithRecipe) {
        log.info("Maping beersmith recipe: {}", beersmithRecipe.getName() );
        Beer beer = new Beer();
        beer.setName(beersmithRecipe.getName());
        beer.setStyle(beersmithRecipe.getStyle());
        beer.setBatchSize(beersmithRecipe.getBatchSize().doubleValue());
        beer.setOriginalGravity(beersmithRecipe.getOriginalGravity());
        beer.setColor(beersmithRecipe.getColor());
        beer.setAbv(beersmithRecipe.getAbv());
        return beer;
    }
}