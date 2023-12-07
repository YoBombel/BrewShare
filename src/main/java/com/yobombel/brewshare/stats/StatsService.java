package com.yobombel.brewshare.stats;

import com.yobombel.brewshare.beer.BeerRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class StatsService {

    BeerRepository beerRepository;

    public StatsService(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    public Stats getStats() {
        Stats stats = new Stats();
        stats.setBeerCount(
                countBeers());
        stats.setTotalVolume(
                countTotalVolume());
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