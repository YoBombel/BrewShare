package com.yobombel.brewshare.beersmith3adapter;

import com.yobombel.brewshare.beersmith3adapter.domain.Bs3Beer;
import com.yobombel.brewshare.beersmith3adapter.domain.Bs3Fermentable;
import com.yobombel.brewshare.beersmith3adapter.domain.Bs3Hop;
import com.yobombel.brewshare.beersmith3adapter.domain.Bs3Ingredient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

//TODO refactor this spaghetti class
public class BeerXmlParser {

    private final double OUNCES_TO_LITERS_MULTIPLIER = 0.0295735;
    private final double OUNCES_TO_GRAMS_MULTIPLIER = 28.3495;

    private static final Logger log = LoggerFactory.getLogger(BeerXmlParser.class);

    public List<Bs3Beer> parse(String path) {
        List<Bs3Beer> beerList = new ArrayList<>();
        Bs3Beer beer = new Bs3Beer();

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        xmlInputFactory.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, false);

        try {
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(path));

            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                event.isEntityReference(); //IGNORE ENTITY REFERENCES

                if (event.isStartElement()) {
                    String startElementString = event.asStartElement().getName().toString();

                    switch (startElementString) {
                        case "Recipe" -> {
                            beer = new Bs3Beer();
                            beerList.add(beer);
                        }
                        case "F_R_NAME" -> beer.setName(getData(reader));
                        case "F_S_NAME" -> beer.setStyle(getData(reader));
                        case "F_E_BATCH_VOL" -> beer.setBatchSize(
                                BigDecimal.valueOf(Double.parseDouble(getData(reader)) * OUNCES_TO_LITERS_MULTIPLIER)
                                        .setScale(2, RoundingMode.HALF_EVEN));
                        case "Ingredients" -> beer.setIngredientList(parseIngredients(reader));
                    }
                }
            }
        } catch (
                XMLStreamException xse) {
            log.error("XMLStreamException");
            xse.printStackTrace();
        } catch (
                FileNotFoundException fnfe) {
            log.error("FileNotFoundException");
            fnfe.printStackTrace();
        }
        return beerList;
    }

    private static String getData(XMLEventReader reader) throws XMLStreamException {
        return reader.nextEvent().asCharacters().getData();
    }

    private ArrayList<Bs3Ingredient> parseIngredients(XMLEventReader reader) throws XMLStreamException {
        Bs3Fermentable fermentable = new Bs3Fermentable();
        Bs3Hop hop = new Bs3Hop();
        ArrayList<Bs3Ingredient> ingredients = new ArrayList<>();

        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            event.isEntityReference(); //IGNORE ENTITY REFERENCES

            if (event.isEndElement() && event.asEndElement().getName().toString().equals("Ingredients")) break;

            if (event.isStartElement()) {
                String startElementString = event.asStartElement().getName().toString();

                switch (startElementString) {
                    case "Grain" -> {
                        fermentable = new Bs3Fermentable();
                        ingredients.add(fermentable);
                    }
                    case "F_G_NAME" -> fermentable.setName(getData(reader));
                    case "F_G_AMOUNT" -> fermentable.setAmount(
                            BigDecimal.valueOf(Double.parseDouble(getData(reader)) * OUNCES_TO_GRAMS_MULTIPLIER)
                                    .setScale(2, RoundingMode.HALF_EVEN));
                    case "F_G_COLOR" -> fermentable.setColor(Double.parseDouble(getData(reader)));
                    case "F_G_YIELD" -> fermentable.setYield(Double.parseDouble(getData(reader)));
                    case "Hops" -> {
                        hop = new Bs3Hop();
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
        return ingredients;
    }

}