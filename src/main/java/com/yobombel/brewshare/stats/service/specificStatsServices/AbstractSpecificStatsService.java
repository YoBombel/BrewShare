package com.yobombel.brewshare.stats.service.specificStatsServices;

import com.yobombel.brewshare.beer.dto.BeerStatsDto;
import com.yobombel.brewshare.stats.model.SpecificStats.SpecificStats;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.function.Function;

public abstract class AbstractSpecificStatsService {

    public abstract SpecificStats createSpecificStats(BigDecimal average, BigDecimal max, BigDecimal min);

    public SpecificStats calculateStats(List<BeerStatsDto> beerStatsDto, Function<BeerStatsDto, BigDecimal> valueExtractor) {
        if(beerStatsDto.isEmpty())
            return createSpecificStats(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);

        DoubleSummaryStatistics values = beerStatsDto.stream()
                .map(valueExtractor)
                .mapToDouble(BigDecimal::doubleValue)
                .summaryStatistics();

        double max = swapInfinityValuesToZero(values.getMax());
        double min = swapInfinityValuesToZero(values.getMin());

       return createSpecificStats(BigDecimal.valueOf(values.getAverage()).setScale(2, RoundingMode.HALF_UP),
                BigDecimal.valueOf(max).setScale(2, RoundingMode.HALF_UP),
                BigDecimal.valueOf(min).setScale(2, RoundingMode.HALF_UP));
    }

    private double swapInfinityValuesToZero(double d) {
        if(d == Double.NEGATIVE_INFINITY || d == Double.POSITIVE_INFINITY)
            return 0.0;
        return d;
    }
}