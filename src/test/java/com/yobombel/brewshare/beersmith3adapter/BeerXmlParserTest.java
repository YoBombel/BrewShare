package com.yobombel.brewshare.beersmith3adapter;

import com.yobombel.brewshare.imports.beersmith3.BeerXmlParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BeerXmlParserTest {

    private InputStream inputStream;
    private BeerXmlParser beerXmlParser;

    private BeersmithRecipe testPilsner;

    @BeforeEach
    void setUp() throws FileNotFoundException {
        beerXmlParser = new BeerXmlParser();
        inputStream = new FileInputStream(new File("src/test/resources/testBeers.bsmx"));
        testPilsner = new BeersmithRecipe();
        testPilsner.setName("TestPilsner");
    }

    @Test
    void shouldReturnCorrectNumberOfBeers() {
        //GIVEN
        //WHEN
        List<BeersmithRecipe> result = beerXmlParser.parse(inputStream);
        //THEN
        assertThat(result).hasSize(2);
    }

}