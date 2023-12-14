package com.yobombel.brewshare.stats.service;

import com.yobombel.brewshare.beer.BeerRepository;
import com.yobombel.brewshare.beer.dto.BeerStatsDto;
import com.yobombel.brewshare.stats.model.SpecificStats.AlcoholStats;
import com.yobombel.brewshare.stats.model.SpecificStats.ColorStats;
import com.yobombel.brewshare.stats.model.SpecificStats.GravityStats;
import com.yobombel.brewshare.stats.model.SpecificStats.IbuStats;
import com.yobombel.brewshare.stats.model.Stats;
import com.yobombel.brewshare.stats.service.specificStatsServices.AlcoholStatsService;
import com.yobombel.brewshare.stats.service.specificStatsServices.ColorStatsService;
import com.yobombel.brewshare.stats.service.specificStatsServices.GravityStatsService;
import com.yobombel.brewshare.stats.service.specificStatsServices.IbuStatsService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class StatsService {

    BeerRepository beerRepository;
    GravityStatsService gravityStatsService;
    AlcoholStatsService alcoholStatsService;
    ColorStatsService colorStatsService;
    IbuStatsService ibuStatsService;

    public StatsService(BeerRepository beerRepository, GravityStatsService gravityStatsService, AlcoholStatsService alcoholStatsService, ColorStatsService colorStatsService, IbuStatsService ibuStatsService) {
        this.beerRepository = beerRepository;
        this.gravityStatsService = gravityStatsService;
        this.alcoholStatsService = alcoholStatsService;
        this.colorStatsService = colorStatsService;
        this.ibuStatsService = ibuStatsService;
    }

    public Stats getStats() {
        List<BeerStatsDto> beerStatsDtos = beerRepository.findAllByBatchSizeIsNotNull();
        Stats stats = new Stats();
        stats.setBeerCount(beerStatsDtos.size());
        stats.setTotalVolume(countTotalVolume(beerStatsDtos));
        stats.setGravityStats((GravityStats) gravityStatsService.calculateStats(beerStatsDtos, BeerStatsDto::originalGravity));
        stats.setAlcoholStats((AlcoholStats) alcoholStatsService.calculateStats(beerStatsDtos, BeerStatsDto::abv));
        stats.setIbuStats((IbuStats) ibuStatsService.calculateStats(beerStatsDtos, BeerStatsDto::ibu));
        stats.setColorStats((ColorStats) colorStatsService.calculateStats(beerStatsDtos, BeerStatsDto::color));
        return stats;
    }

    private BigDecimal countTotalVolume(List<BeerStatsDto> beerStatsDtos) {
        return beerStatsDtos.stream()
                .map(BeerStatsDto::batchSize)
                .map(BigDecimal::valueOf)
                .reduce(BigDecimal.ZERO, BigDecimal::add);



    }
}