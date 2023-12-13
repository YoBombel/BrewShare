package com.yobombel.brewshare.stats.service.specificStatsServices;

import com.yobombel.brewshare.beer.dto.BeerStatsDto;
import com.yobombel.brewshare.stats.model.SpecificStats.SpecificStats;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.function.ToDoubleFunction;

public abstract class AbstractSpecificStatsService {

    public abstract SpecificStats createSpecificStats(double average, double max, double min);

    public SpecificStats calculateStats(List<BeerStatsDto> beerStatsDto, ToDoubleFunction<BeerStatsDto> valueExtractor) {
        DoubleSummaryStatistics values = beerStatsDto.stream()
                .mapToDouble(valueExtractor)
                .summaryStatistics();

        SpecificStats specificStats = createSpecificStats(values.getAverage(),
                values.getMax(),
                values.getMin());

        swapInfiniteValuesToZero(specificStats);
        return specificStats;
    }

    private void swapInfiniteValuesToZero(SpecificStats specificStats) {
        if (specificStats.getMax() == Double.NEGATIVE_INFINITY)
            specificStats.setMax(0.0);
        if (specificStats.getMin() == Double.POSITIVE_INFINITY)
            specificStats.setMin(0.0);
    }
}