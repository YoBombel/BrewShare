package com.yobombel.brewshare.stats.service;

import com.yobombel.brewshare.config.NumberConfig;
import com.yobombel.brewshare.stats.model.AggregateStat;
import com.yobombel.brewshare.stats.model.BeerSpecDto;
import com.yobombel.brewshare.stats.model.enums.StatType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import static com.yobombel.brewshare.config.NumberConfig.setDefaultScale;

@Service
public class AggregateStatsService {

    private List<BeerSpecDto> beerSpecDtos;

    public List<AggregateStat> createAggregateStats(List<BeerSpecDto> beerSpecDtos) {
        this.beerSpecDtos = beerSpecDtos;

        List<AggregateStat> aggregateStats = new ArrayList<>();

        AggregateStat og = new AggregateStat(StatType.OG,
                createMinMaxAvg(BeerSpecDto::originalGravity));
        aggregateStats.add(og);

        AggregateStat abv = new AggregateStat(StatType.ABV,
                createMinMaxAvg(BeerSpecDto::abv));
        aggregateStats.add(abv);

        AggregateStat ibu = new AggregateStat(StatType.IBU,
                createMinMaxAvg(BeerSpecDto::ibu));
        aggregateStats.add(ibu);

        AggregateStat color = new AggregateStat(StatType.COLOR,
                createMinMaxAvg(BeerSpecDto::color));
        aggregateStats.add(color);

        return aggregateStats;
    }

    private BigDecimal[] createMinMaxAvg(Function<BeerSpecDto, BigDecimal> valueExtractor) {
        BigDecimal[] minMaxAvg = new BigDecimal[3];
        minMaxAvg[0] = calculateMin(valueExtractor);
        minMaxAvg[1] = calculateMax(valueExtractor);
        minMaxAvg[2] = calculateAverage(valueExtractor);
        return minMaxAvg;
    }

    private BigDecimal calculateAverage(Function<BeerSpecDto, BigDecimal> valueExtractor) {
        List<BigDecimal> values = beerSpecDtos.stream()
                .map(valueExtractor)
                .filter(Objects::nonNull)
                .toList();
        BigDecimal sum =
                values.stream()
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (sum.equals(BigDecimal.ZERO))
            return BigDecimal.ZERO;

        return setDefaultScale(
                sum.divide(new BigDecimal(values.size()), RoundingMode.HALF_UP));
    }

    private BigDecimal calculateMin(Function<BeerSpecDto, BigDecimal> valueExtractor) {
        return beerSpecDtos.stream()
                .map(valueExtractor)
                .reduce(BigDecimal::min)
                .map(NumberConfig::setDefaultScale)
                .orElse(BigDecimal.ZERO);
    }

    private BigDecimal calculateMax(Function<BeerSpecDto, BigDecimal> valueExtractor) {
        return beerSpecDtos.stream()
                .map(valueExtractor)
                .reduce(BigDecimal::max)
                .map(NumberConfig::setDefaultScale)
                .orElse(BigDecimal.ZERO);
    }

}