package com.yobombel.brewshare.stats.service;

import com.yobombel.brewshare.beer.BeerRepository;
import com.yobombel.brewshare.stats.model.Stats;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class StatsService {

    BeerRepository beerRepository;
    GravityStatsService gravityStatsService;

    public StatsService(BeerRepository beerRepository, GravityStatsService gravityStatsService) {
        this.beerRepository = beerRepository;
        this.gravityStatsService = gravityStatsService;
    }

    public Stats getStats() {
        Stats stats = new Stats();
        stats.setBeerCount(
                countBeers());
        stats.setTotalVolume(
                countTotalVolume());
        stats.setGravityStats(gravityStatsService
                .createGravityStats());
        return stats;
    }

    private int countBeers() {
        return (int) beerRepository.count();
    }

    private BigDecimal countTotalVolume() {
        return beerRepository.findBatchSizes().stream()
                .map(BigDecimal::valueOf)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

}