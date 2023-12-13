package com.yobombel.brewshare.imports.beersmith3;

import com.yobombel.brewshare.imports.beersmith3.domain.BeersmithRecipe;
import com.yobombel.brewshare.imports.beersmith3.domain.Fermentable;
import com.yobombel.brewshare.imports.beersmith3.domain.Hop;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BeersmithSpecCalculationsTest {

    BeersmithSpecCalculations beersmithSpecCalculations;
    BeersmithRecipe recipe;

    @BeforeEach
    void setUp(){
        beersmithSpecCalculations = new BeersmithSpecCalculations();
        recipe = new BeersmithRecipe();

        Fermentable fermentable1 = new Fermentable();
        Fermentable fermentable2 = new Fermentable();
        fermentable1.setColor(1.7);
        fermentable1.setAmount(134.0);
        fermentable2.setColor(51.0);
        fermentable2.setAmount(17.6);

        Hop hop1 = new Hop();
        Hop hop2 = new Hop();
        hop1.setIbuContribution(10);
        hop2.setIbuContribution(15);
        recipe.setIngredients(List.of(fermentable1, fermentable2, hop1, hop2));
    }

    @Test
    void shouldCalculateAbv(){
        //GIVEN
        double expected = 5.25;
        recipe.setOriginalGravityMeasured(1.048);
        recipe.setFinishingGravityMeasured(1.008);
        //WHEN
        double result = beersmithSpecCalculations.calculateAbv(recipe);
        //THEN
        assertThat(result).isCloseTo(expected, Percentage.withPercentage(0.01));
    }

    @Test
    void shouldCalculateIbu(){
        //GIVEN
        double expected = 25.0;
        //WHEN
        double result = beersmithSpecCalculations.calculateIbu(recipe);
        //THEN
        assertThat(result).isCloseTo(expected, Percentage.withPercentage(0.1));
    }

    @Test
    void shouldCalculateColor(){
        //GIVEN
        recipe.setBatchVolume(743.9);
        double expected = 8.3;
        //WHEN
        double result = beersmithSpecCalculations.calculateColor(recipe);
        //THEN
        assertThat(result).isCloseTo(expected, Percentage.withPercentage(1));
    }

}