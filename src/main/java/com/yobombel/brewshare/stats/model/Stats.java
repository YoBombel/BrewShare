package com.yobombel.brewshare.stats.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Stats {

    private int beerCount;
    private BigDecimal totalVolume = BigDecimal.ZERO;
    private List<AggregateStat> aggregateStats;

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
        this.totalVolume = totalVolume.setScale(2, RoundingMode.HALF_UP);
    }

    public List<AggregateStat> getAggregateStats() {
        return aggregateStats;
    }

    public void setAggregateStats(List<AggregateStat> aggregateStats) {
        this.aggregateStats = aggregateStats;
    }
}