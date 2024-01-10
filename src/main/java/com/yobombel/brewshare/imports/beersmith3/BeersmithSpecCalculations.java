package com.yobombel.brewshare.imports.beersmith3;

import com.yobombel.brewshare.imports.beersmith3.domain.BeersmithRecipe;
import com.yobombel.brewshare.imports.beersmith3.domain.Fermentable;
import com.yobombel.brewshare.imports.beersmith3.domain.Hop;
import com.yobombel.brewshare.util.UnitConversion;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class BeersmithSpecCalculations {

    BigDecimal calculateAbv(BeersmithRecipe beersmithRecipe) {
        BigDecimal abv = beersmithRecipe.getOriginalGravityMeasured();
        abv = abv.subtract(beersmithRecipe.getFinishingGravityMeasured());
        abv = abv.multiply(BigDecimal.valueOf(131.25));
        return abv;

        //return (beersmithRecipe.getOriginalGravityMeasured() - beersmithRecipe.getFinishingGravityMeasured()) * 131.25;
    }

    BigDecimal calculateIbu(BeersmithRecipe beersmithRecipe) {
        return beersmithRecipe.getIngredients().stream()
                .filter(Hop.class::isInstance)
                .map(h -> ((Hop) h).getIbuContribution())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    //MCU = (Weight of grain in lbs) * (Color of grain in degrees lovibond) / (volume in gallons)
    //SRM color = 1.4922 * (MCU ^ 0.6859)
    BigDecimal calculateColor(BeersmithRecipe beersmithRecipe) {
        BigDecimal maltColorUnits = beersmithRecipe.getIngredients().stream()
                .filter(Fermentable.class::isInstance)
                .map((i -> (((Fermentable) i).getColor()
                        .multiply(
                                UnitConversion.ouncesToPounds(i.getAmount()))
                        .divide(
                                UnitConversion.fluidOuncesToGallons(beersmithRecipe.getBatchVolume()), RoundingMode.HALF_UP))))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal maltColorUnitsPower = BigDecimal.valueOf(Math.pow(maltColorUnits.doubleValue(), 0.6859));
        return maltColorUnitsPower.multiply(BigDecimal.valueOf(1.4922));

//        double maltColorUnitsD = beersmithRecipe.getIngredients().stream()
//                .filter(Fermentable.class::isInstance)
//                .map((i -> (((Fermentable) i).getColor() *
//                        UnitConversion.ouncesToPounds(i.getAmount())) /
//                        UnitConversion.fluidOuncesToGallons(beersmithRecipe.getBatchVolume())))
//                .reduce(0.0, Double::sum);
//        return 1.4922 * (Math.pow(maltColorUnits, 0.6859));
    }

}
