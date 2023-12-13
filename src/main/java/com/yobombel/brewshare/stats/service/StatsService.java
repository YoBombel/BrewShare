package com.yobombel.brewshare.stats.service;

import com.yobombel.brewshare.beer.BeerRepository;
import com.yobombel.brewshare.beer.dto.BeerStatsDto;
import com.yobombel.brewshare.stats.model.SpecificStats.AlcoholStats;
import com.yobombel.brewshare.stats.model.SpecificStats.GravityStats;
import com.yobombel.brewshare.stats.model.Stats;
import com.yobombel.brewshare.stats.service.specificStatsServices.AlcoholStatsService;
import com.yobombel.brewshare.stats.service.specificStatsServices.GravityStatsService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class StatsService {

    BeerRepository beerRepository;
    GravityStatsService gravityStatsService;
    AlcoholStatsService alcoholStatsService;

    public StatsService(BeerRepository beerRepository, GravityStatsService gravityStatsService, AlcoholStatsService alcoholStatsService) {
        this.beerRepository = beerRepository;
        this.gravityStatsService = gravityStatsService;
        this.alcoholStatsService = alcoholStatsService;
    }

    public Stats getStats() {
        List<BeerStatsDto> beerStatsDtos = beerRepository.findAllByBatchSizeIsNotNull();
        Stats stats = new Stats();
        stats.setBeerCount(countBeers());
        stats.setTotalVolume(countTotalVolume());
        stats.setGravityStats((GravityStats) gravityStatsService.calculateStats(beerStatsDtos, BeerStatsDto::originalGravity));
        stats.setAlcoholStats((AlcoholStats) alcoholStatsService.calculateStats(beerStatsDtos, BeerStatsDto::abv));
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