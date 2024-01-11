package com.yobombel.brewshare.stats.service;

import com.yobombel.brewshare.beer.BeerRepository;
import com.yobombel.brewshare.stats.model.BeerStatsDto;
import com.yobombel.brewshare.stats.model.Stats;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class StatsService {

    BeerRepository beerRepository;
    AggregateStatsService aggregateStatsService;

    public StatsService(BeerRepository beerRepository, AggregateStatsService aggregateStatsService) {
        this.beerRepository = beerRepository;
        this.aggregateStatsService = aggregateStatsService;
    }

    public Stats createStats() {
        List<BeerStatsDto> beerStatsDtos = beerRepository.findAllByBatchSizeIsNotNull();

        Stats stats = new Stats();
        stats.setBeerCount(beerStatsDtos.size());
        stats.setTotalVolume(countTotalVolume(beerStatsDtos));

        stats.setAggregateStats(
                aggregateStatsService.createAggregateStats(beerStatsDtos));

        return stats;
    }

    private BigDecimal countTotalVolume(List<BeerStatsDto> beerStatsDtos) {
        return beerStatsDtos.stream()
                .map(BeerStatsDto::batchSize)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}