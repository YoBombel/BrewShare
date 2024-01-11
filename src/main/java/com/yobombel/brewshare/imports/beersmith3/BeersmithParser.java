package com.yobombel.brewshare.imports.beersmith3;

import com.yobombel.brewshare.imports.beersmith3.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//TODO: refactor this spaghetti class
@Component
public class BeersmithParser {

    private final XMLInputFactory xmlInputFactory;
    Logger logger = LoggerFactory.getLogger(BeersmithParser.class);

    public BeersmithParser() {
        this.xmlInputFactory = XMLInputFactory.newInstance();
        xmlInputFactory.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, false);
    }

    public List<BeersmithRecipe> parse(InputStream inputStream) {
        XMLEvent event;
        List<BeersmithRecipe> beersmithRecipes = new ArrayList<>();

        try {
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(inputStream);

            while (reader.hasNext()) {
                event = getXmlEvent(reader);

                if (event.isStartElement()) {
                    String startElementString = event.asStartElement().getName().toString();

                    if ("Recipe".equals(startElementString)) {
                        BeersmithRecipe beersmithRecipe = parseRecipe(reader);
                        logger.info("New recipe parsed: {}", beersmithRecipe.getName());
                        beersmithRecipes.add(beersmithRecipe);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("All recipes parsed, count: {}", beersmithRecipes.size());
        return beersmithRecipes;
    }

    private BeersmithRecipe parseRecipe(XMLEventReader reader) throws XMLStreamException {
        BeersmithRecipe beersmithRecipe = new BeersmithRecipe();
        mapRecipeToObjectFields(reader, beersmithRecipe);
        return beersmithRecipe;
    }

    private void mapRecipeToObjectFields(XMLEventReader reader, BeersmithRecipe beersmithRecipe) throws XMLStreamException {
        while (reader.hasNext()) {
            XMLEvent event = getXmlEvent(reader);

            if (checkIngredientsStart(event)) beersmithRecipe.setIngredients(parseIngredients(reader));

            if (checkEndOfBlock(event, beersmithRecipe.getEndReaderLoopElements())) break;

            if (event.isStartElement()) {
                String startElementString = event.asStartElement().getName().toString();
                if (beersmithRecipe.getXmlElementsDictionary().containsKey(startElementString))
                    beersmithRecipe.getXmlElementsDictionary().get(startElementString).accept(getData(reader));
            }
        }
    }

    private BeerXmlObject mapXmlToObjectFields(XMLEventReader reader, BeerXmlObject beerXmlObject) throws XMLStreamException {
        while (reader.hasNext()) {
            XMLEvent event = getXmlEvent(reader);

            if (checkEndOfBlock(event, beerXmlObject.getEndReaderLoopElements())) break;

            if (event.isStartElement()) {
                String startElementString = event.asStartElement().getName().toString(); //TODO - TRAIN WRECK
                if (beerXmlObject.getXmlElementsDictionary().containsKey(startElementString))
                    beerXmlObject.getXmlElementsDictionary().get(startElementString).accept(getData(reader));
            }
        }
        return beerXmlObject;
    }

    private boolean checkIngredientsStart(XMLEvent event) {
        return event.isStartElement() && event.toString().equals("<Ingredients>");
    }

    private ArrayList<BeersmithIngredient> parseIngredients(XMLEventReader reader) throws XMLStreamException {
        ArrayList<BeersmithIngredient> ingredients = new ArrayList<>();
        HashSet<String> ingredientsEndElement = new HashSet<>();
        ingredientsEndElement.add("Ingredients");

        while (reader.hasNext()) {
            XMLEvent event = getXmlEvent(reader);

            if (checkEndOfBlock(event, ingredientsEndElement)) break;

            if (event.isStartElement()) {
                String startElementString = event.asStartElement().getName().toString();

                switch (startElementString) {
                    case "Grain" ->
                            ingredients.add((BeersmithIngredient) mapXmlToObjectFields((reader), new Fermentable()));
                    case "Hops" -> ingredients.add((BeersmithIngredient) mapXmlToObjectFields((reader), new Hop()));
                    case "Yeast" -> ingredients.add((BeersmithIngredient) mapXmlToObjectFields((reader), new Yeast()));
                }
            }
        }
        return ingredients;
    }


    private boolean checkEndOfBlock(XMLEvent event, Set<String> endXmlAttributes) {
        return event.isEndElement() && endXmlAttributes.contains(event.asEndElement().getName().toString());
    }

    private static String getData(XMLEventReader reader) throws XMLStreamException {
        return reader.nextEvent().toString();
    }

    private static XMLEvent getXmlEvent(XMLEventReader reader) throws XMLStreamException {
        XMLEvent event = reader.nextEvent();
        //event.isEntityReference(); //IGNORE ENTITY REFERENCES TODO - REFACTOR, THIS CLASS SEEMS UNNECESSARY NOW
        return event;
    }

}