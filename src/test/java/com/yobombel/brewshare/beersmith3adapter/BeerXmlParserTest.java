package com.yobombel.brewshare.beersmith3adapter;

import com.yobombel.brewshare.beersmith3adapter.domain.Bs3Beer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.stream.XMLStreamException;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BeerXmlParserTest {

    private String testFilePath;
    private BeerXmlParser beerXmlParser;

    private Bs3Beer testPilsner;

    @BeforeEach
    void setUp() {
        beerXmlParser = new BeerXmlParser();
        testFilePath = "src/test/resources/testBeers.bsmx";
        testPilsner = new Bs3Beer();
        testPilsner.setName("TestPilsner");
    }

    @Test
    void shouldReturnCorrectNumberOfBeers() {
        //GIVEN
        //WHEN
        List<Bs3Beer> result = beerXmlParser.parse(testFilePath);
        //THEN
        assertThat(result).hasSize(2);
    }

}