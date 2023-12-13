package com.yobombel.brewshare.stats.model;

import java.math.BigDecimal;

public class Stats {

    private int beerCount;
    private BigDecimal totalVolume;
    private GravityStats gravityStats;

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
        this.totalVolume = totalVolume;
    }

    public GravityStats getGravityStats() {
        return gravityStats;
    }

    public void setGravityStats(GravityStats gravityStats) {
        this.gravityStats = gravityStats;
    }
}