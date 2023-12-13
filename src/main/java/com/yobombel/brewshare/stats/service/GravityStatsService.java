package com.yobombel.brewshare.stats.service;

import com.yobombel.brewshare.beer.BeerRepository;
import com.yobombel.brewshare.stats.model.GravityStats;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;

@Service
public class GravityStatsService {

    BeerRepository beerRepository;

    public GravityStatsService(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    GravityStats createGravityStats() {
        DoubleSummaryStatistics values = beerRepository.findOriginalGravities()
                .stream()
                .mapToDouble(Double::doubleValue)
                .summaryStatistics();
        GravityStats gravityStats = new GravityStats(values.getAverage(),
                values.getMax(),
                values.getMin());
        swapInfinityValuesToZero(gravityStats);
        return gravityStats;
    }

    private void swapInfinityValuesToZero(GravityStats gravityStats) {
        if (gravityStats.getMaxOriginalGravity() == Double.NEGATIVE_INFINITY)
            gravityStats.setMaxOriginalGravity(0.0);
        if (gravityStats.getMinOriginalGravity() == Double.POSITIVE_INFINITY)
            gravityStats.setMinOriginalGravity(0.0);
    }
}