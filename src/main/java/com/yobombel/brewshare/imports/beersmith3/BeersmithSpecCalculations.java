package com.yobombel.brewshare.imports.beersmith3;

import com.yobombel.brewshare.imports.beersmith3.domain.BeersmithRecipe;
import com.yobombel.brewshare.imports.beersmith3.domain.Fermentable;
import com.yobombel.brewshare.imports.beersmith3.domain.Hop;
import com.yobombel.brewshare.util.UnitConversion;
import org.springframework.stereotype.Component;

@Component
public class BeersmithSpecCalculations {

    double calculateAbv(BeersmithRecipe beersmithRecipe) {
        return (beersmithRecipe.getOriginalGravityMeasured() - beersmithRecipe.getFinishingGravityMeasured()) * 131.25;
    }

    double calculateIbu(BeersmithRecipe beersmithRecipe) {
        return beersmithRecipe.getIngredients().stream()
                .filter(Hop.class::isInstance)
                .map(h -> ((Hop) h).getIbuContribution())
                .reduce(0.0, Double::sum);
    }

    //MCU = (Weight of grain in lbs) * (Color of grain in degrees lovibond) / (volume in gallons)
    //SRM color = 1.4922 * (MCU ^ 0.6859)
    double calculateColor(BeersmithRecipe beersmithRecipe) {
        double maltColorUnits = beersmithRecipe.getIngredients().stream()
                .filter(Fermentable.class::isInstance)
                .map((i -> (((Fermentable) i).getColor() *
                        UnitConversion.ouncesToPounds(i.getAmount())) /
                        UnitConversion.fluidOuncesToGallons(beersmithRecipe.getBatchVolume())))
                .reduce(0.0, Double::sum);
        return 1.4922 * (Math.pow(maltColorUnits, 0.6859));
    }

}
