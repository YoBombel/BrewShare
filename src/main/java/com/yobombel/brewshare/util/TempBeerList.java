package com.yobombel.brewshare.util;

import com.yobombel.brewshare.beer.Beer;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class TempBeerList {

    private final List<Beer> temporaryBeerList;
    Random random = new Random();

    public TempBeerList() {
        this.temporaryBeerList = createTemporaryBeerList();
    }

    private List<Beer> createTemporaryBeerList(){
        List<Beer> beers = new ArrayList<>();

        for (int i =1; i <= 10; i++) {
            Beer beer = new Beer();
            beer.setId((long) i);
            beer.setName("Test Beer " + i);
            beer.setStyle("Test Style " + i);
            beer.setBatchSize(generate1DecimalPlaceRandom(19,23));
            beer.setOriginalGravity(generate1DecimalPlaceRandom(7, 30));
            beer.setAbv(generate1DecimalPlaceRandom(3, 13));
            beer.setIbu(generate1DecimalPlaceRandom(0,100));
            beer.setColor(generate1DecimalPlaceRandom(4, 65));
            beers.add(beer);
        }
        
        return beers;
    }
    
    private double generate1DecimalPlaceRandom(int origin, int bound){
        return BigDecimal.valueOf(random.nextDouble(origin,bound)).setScale(1, RoundingMode.HALF_UP).doubleValue();
    }    

    public List<Beer> getTemporaryBeerList() {
        return temporaryBeerList;
    }
}