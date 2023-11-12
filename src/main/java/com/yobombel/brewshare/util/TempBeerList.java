package com.yobombel.brewshare.util;

import com.yobombel.brewshare.beer.domain.Beer;
import com.yobombel.brewshare.beer.BeerService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

@Component
public class TempBeerList {

    private final BeerService beerService;
    Random random = new Random();

    public TempBeerList(BeerService beerService) {
        this.beerService = beerService;
    }

    @PostConstruct
    private void createTemporaryBeerList() {
        beerService.deleteAllBeers();

        for (int i = 1; i <= 10; i++) {
            Beer beer = new Beer();
            beer.setId((long) i);
            beer.setName("Test Beer " + i);
            beer.setStyle("Test Style " + i);
            beer.setBatchSize(generate1DecimalPlaceRandom(19, 23));
            beer.setOriginalGravity(generate1DecimalPlaceRandom(7, 30));
            beer.setAbv(generate1DecimalPlaceRandom(3, 13));
            beer.setIbu(generate1DecimalPlaceRandom(0, 100));
            beer.setColor(generate1DecimalPlaceRandom(4, 65));
            beerService.addBeer(beer);
        }
    }

    private double generate1DecimalPlaceRandom(int origin, int bound) {
        return BigDecimal.valueOf(random.nextDouble(origin, bound)).setScale(1, RoundingMode.HALF_UP).doubleValue();
    }
}