package com.yobombel.brewshare.stats.service;

import com.yobombel.brewshare.beer.BeerRepository;
import com.yobombel.brewshare.stats.model.BeerSpecDto;
import com.yobombel.brewshare.stats.model.Stats;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class StatsService {

    BeerRepository beerRepository;
    AggregateStatsService aggregateStatsService;
    StyleStatsService styleStatsService;

    public StatsService(BeerRepository beerRepository, AggregateStatsService aggregateStatsService, StyleStatsService styleStatsService) {
        this.beerRepository = beerRepository;
        this.aggregateStatsService = aggregateStatsService;
        this.styleStatsService = styleStatsService;
    }

    public Stats createStats() {
        List<BeerSpecDto> beerSpecDtos = beerRepository.findAllByBatchSizeIsNotNull();

        Stats stats = new Stats();
        stats.setBeerCount(beerSpecDtos.size());
        stats.setTotalVolume(countTotalVolume(beerSpecDtos));

        stats.setAggregateStats(
                aggregateStatsService.createAggregateStats(beerSpecDtos));

        stats.setStyleStats(
                styleStatsService.createStyleStats(beerSpecDtos));

        return stats;
    }

    private BigDecimal countTotalVolume(List<BeerSpecDto> beerSpecDtos) {
        return beerSpecDtos.stream()
                .map(BeerSpecDto::batchSize)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}