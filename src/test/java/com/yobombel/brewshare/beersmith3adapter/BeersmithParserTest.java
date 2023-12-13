package com.yobombel.brewshare.beersmith3adapter;

import com.yobombel.brewshare.imports.beersmith3.BeersmithParser;
import com.yobombel.brewshare.imports.beersmith3.domain.BeersmithRecipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BeersmithParserTest {

    private InputStream inputStream;
    private BeersmithParser beersmithParser;

    private BeersmithRecipe testPilsner;

    @BeforeEach
    void setUp() throws FileNotFoundException {
        beersmithParser = new BeersmithParser();
        inputStream = new FileInputStream(new File("src/test/resources/testBeers.bsmx"));
        testPilsner = new BeersmithRecipe();
        testPilsner.setName("TestPilsner");
    }

    @Test
    void shouldReturnCorrectNumberOfBeers() {
        //GIVEN
        //WHEN
        List<BeersmithRecipe> result = beersmithParser.parse(inputStream);
        //THEN
        assertThat(result).hasSize(2);
    }

}