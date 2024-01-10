//package com.yobombel.brewshare.util;
//
//import com.yobombel.brewshare.beer.Beer;
//import com.yobombel.brewshare.beer.BeerService;
//import com.yobombel.brewshare.beer.ingredient.Ingredient;
//import com.yobombel.brewshare.beer.ingredient.IngredientService;
//import jakarta.annotation.PostConstruct;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Profile;
//import org.springframework.stereotype.Component;
//
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//@Component
//@Profile("liveTest")
//public class TempBeerList {
//
//    private final BeerService beerService;
//    private final IngredientService ingredientService;
//    private static final Logger log = LoggerFactory.getLogger(TempBeerList.class);
//
//    Random random = new Random();
//
//    public TempBeerList(BeerService beerService, IngredientService ingredientService) {
//        this.beerService = beerService;
//        this.ingredientService = ingredientService;
//    }
//
//    @PostConstruct
//    private void createTemporaryBeerList() {
//        ingredientService.deleteAllIngredients();
//        beerService.deleteAll();
//
//        log.info("Creating temporary beer list");
//
//        for (int i = 1; i <= 100; i++) {
//            Beer beer = new Beer();
//            beer.setName("TestBeer" + i);
//            Long id = beerService.add(beer).getId();
//            beer.setStyle("TestStyle" + i);
//            beer.setBatchSize(generate1DecimalPlaceRandom(19, 23));
//            beer.setOriginalGravity(generate1DecimalPlaceRandom(7, 30));
//            beer.setAbv(generate1DecimalPlaceRandom(3, 13));
//            beer.setIbu(generate1DecimalPlaceRandom(0, 100));
//            beer.setColor(generate1DecimalPlaceRandom(4, 65));
//            beer.setIngredients(generateIngredients((i + 3), beer));
//            beerService.update(id, beer);
//        }
//    }
//
//    private List<Ingredient> generateIngredients(int i, Beer beer) {
//        List<Ingredient> ingredients = new ArrayList<>();
//        for (int j = 0; j < i; j++) {
//            Ingredient ingredient = new Ingredient();
//            ingredient.setName("Ingredient " + (j + 1));
//            ingredient.setAmount(generate1DecimalPlaceRandom(10, 5000));
//            ingredient.setBeer(beer);
//            ingredients.add(ingredient);
//            ingredientService.add(ingredient);
//        }
//        return ingredients;
//    }
//
//    private double generate1DecimalPlaceRandom(int origin, int bound) {
//        return BigDecimal.valueOf(random.nextDouble(origin, bound)).setScale(1, RoundingMode.HALF_UP).doubleValue();
//    }
//}