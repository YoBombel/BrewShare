package com.yobombel.brewshare.imports.beersmith3;

import com.yobombel.brewshare.imports.beersmith3.domain.BeersmithRecipe;
import com.yobombel.brewshare.imports.beersmith3.domain.Fermentable;
import com.yobombel.brewshare.imports.beersmith3.domain.Hop;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BeersmithSpecCalculationsTest {

    BeersmithSpecCalculations beersmithSpecCalculations;
    BeersmithRecipe recipe;

    @BeforeEach
    void setUp() {
        beersmithSpecCalculations = new BeersmithSpecCalculations();
        recipe = new BeersmithRecipe();

        Fermentable fermentable1 = new Fermentable();
        Fermentable fermentable2 = new Fermentable();
        fermentable1.setColor(BigDecimal.valueOf(1.7));
        fermentable1.setAmount(BigDecimal.valueOf(134.0));
        fermentable2.setColor(BigDecimal.valueOf(51.0));
        fermentable2.setAmount(BigDecimal.valueOf(17.6));

        Hop hop1 = new Hop();
        Hop hop2 = new Hop();
        hop1.setIbuContribution(BigDecimal.valueOf(10));
        hop2.setIbuContribution(BigDecimal.valueOf(15));
        recipe.setIngredients(List.of(fermentable1, fermentable2, hop1, hop2));
    }

    @Test
    void shouldCalculateAbv() {
        //GIVEN
        BigDecimal expected = BigDecimal.valueOf(5.25);
        recipe.setOriginalGravityMeasured(BigDecimal.valueOf(1.048));
        recipe.setFinishingGravityMeasured(BigDecimal.valueOf(1.008));
        //WHEN
        var result = beersmithSpecCalculations.calculateAbv(recipe);
        //THEN
        assertThat(result).isCloseTo(expected, Percentage.withPercentage(0.01));
    }

    @Test
    void shouldCalculateIbu() {
        //GIVEN
        BigDecimal expected = BigDecimal.valueOf(25.0);
        //WHEN
        var result = beersmithSpecCalculations.calculateIbu(recipe);
        //THEN
        assertThat(result).isCloseTo(expected, Percentage.withPercentage(0.1));
    }

    @Test
    void shouldCalculateColor() {
        //GIVEN
        recipe.setBatchVolume(BigDecimal.valueOf(743.9));
        BigDecimal expected = BigDecimal.valueOf(8.3);
        //WHEN
        var result = beersmithSpecCalculations.calculateColor(recipe);
        //THEN
        assertThat(result).isCloseTo(expected, Percentage.withPercentage(1));
    }

}