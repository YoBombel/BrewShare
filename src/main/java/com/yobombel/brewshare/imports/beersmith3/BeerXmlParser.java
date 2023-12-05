package com.yobombel.brewshare.imports.beersmith3;

import com.yobombel.brewshare.imports.beersmith3.domain.Beersmith3Hop;
import com.yobombel.brewshare.imports.beersmith3.domain.BeersmithFermentable;
import com.yobombel.brewshare.imports.beersmith3.domain.BeersmithIngredient;
import com.yobombel.brewshare.imports.beersmith3.domain.BeersmithRecipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

//TODO: refactor this spaghetti class
@Component
public class BeerXmlParser {

    private final double OUNCES_TO_LITERS_MULTIPLIER = 0.0295735;
    private final double OUNCES_TO_GRAMS_MULTIPLIER = 28.3495;

    private static final Logger log = LoggerFactory.getLogger(BeerXmlParser.class);

    public List<BeersmithRecipe> parse(InputStream inputStream) {
        List<BeersmithRecipe> beersmithRecipes = new ArrayList<>();
        BeersmithRecipe beersmithRecipe = new BeersmithRecipe();

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        xmlInputFactory.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, false);

        try {
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(inputStream);
            log.info("Parsing file");
            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                event.isEntityReference(); //IGNORE ENTITY REFERENCES

                if (event.isStartElement()) {
                    String startElementString = event.asStartElement().getName().toString();

                    switch (startElementString) {
                        case "Recipe" -> {
                            beersmithRecipe = new BeersmithRecipe();
                            beersmithRecipes.add(beersmithRecipe);
                            log.trace("Parsing new recipe");
                        }
                        case "F_R_NAME" -> beersmithRecipe.setName(getData(reader));
                        case "F_S_NAME" -> beersmithRecipe.setStyle(getData(reader));
                        case "F_E_BATCH_VOL" -> beersmithRecipe.setBatchSize(
                                BigDecimal.valueOf(Double.parseDouble(getData(reader)) * OUNCES_TO_LITERS_MULTIPLIER)
                                        .setScale(2, RoundingMode.HALF_EVEN));
                        case "Ingredients" -> beersmithRecipe.setIngredientList(parseIngredients(reader));
                    }
                }
            }
        } catch (
                XMLStreamException xse) {
            log.error("XMLStreamException");
            xse.printStackTrace();
        }
        log.info("Parsing complete, returning Beersmith recipe list");
        return beersmithRecipes;
    }

    private static String getData(XMLEventReader reader) throws XMLStreamException {
        return reader.nextEvent().asCharacters().getData();
    }

    private ArrayList<BeersmithIngredient> parseIngredients(XMLEventReader reader) throws XMLStreamException {
        log.trace("Parsing ingredients");
        BeersmithFermentable fermentable = new BeersmithFermentable();
        Beersmith3Hop hop = new Beersmith3Hop();
        ArrayList<BeersmithIngredient> ingredients = new ArrayList<>();

        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            event.isEntityReference(); //IGNORE ENTITY REFERENCES

            if (event.isEndElement() && event.asEndElement().getName().toString().equals("Ingredients")) break;

            if (event.isStartElement()) {
                String startElementString = event.asStartElement().getName().toString();

                switch (startElementString) {
                    case "Grain" -> {
                        fermentable = new BeersmithFermentable();
                        ingredients.add(fermentable);
                    }
                    case "F_G_NAME" -> fermentable.setName(getData(reader));
                    case "F_G_AMOUNT" -> fermentable.setAmount(
                            BigDecimal.valueOf(Double.parseDouble(getData(reader)) * OUNCES_TO_GRAMS_MULTIPLIER)
                                    .setScale(2, RoundingMode.HALF_EVEN));
                    case "F_G_COLOR" -> fermentable.setColor(Double.parseDouble(getData(reader)));
                    case "F_G_YIELD" -> fermentable.setYield(Double.parseDouble(getData(reader)));
                    case "Hops" -> {
                        hop = new Beersmith3Hop();
                        ingredients.add(hop);
                    }
                    case "F_H_NAME" -> hop.setName(getData(reader));
                    case "F_H_AMOUNT" -> hop.setAmount(
                            BigDecimal.valueOf(Double.parseDouble(getData(reader)) * OUNCES_TO_GRAMS_MULTIPLIER)
                                    .setScale(2, RoundingMode.HALF_EVEN));
                    case "F_H_ALPHA" -> hop.setAlpha(Double.parseDouble(getData(reader)));
                }
            }
        }
        log.trace("Ingredients parse finished");
        return ingredients;
    }
}