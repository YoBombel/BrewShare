package com.yobombel.brewshare.stats.model;

import java.math.BigDecimal;
import java.util.List;

import static com.yobombel.brewshare.config.NumberConfig.setDefaultScale;

public class Stats {

    private int beerCount;
    private BigDecimal totalVolume = BigDecimal.ZERO;
    private List<AggregateStat> aggregateStats;
    private StyleStats styleStats;

    public int getBeerCount() {
        return beerCount;
    }

    public void setBeerCount(int beerCount) {
        this.beerCount = beerCount;
    }

    public BigDecimal getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(BigDecimal totalVolume) {
        this.totalVolume = setDefaultScale(totalVolume);
    }

    public List<AggregateStat> getAggregateStats() {
        return aggregateStats;
    }

    public void setAggregateStats(List<AggregateStat> aggregateStats) {
        this.aggregateStats = aggregateStats;
    }

    public StyleStats getStyleStats() {
        return styleStats;
    }

    public void setStyleStats(StyleStats styleStats) {
        this.styleStats = styleStats;
    }
}