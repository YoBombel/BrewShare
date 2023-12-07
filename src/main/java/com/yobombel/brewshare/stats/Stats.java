package com.yobombel.brewshare.stats;

import java.math.BigDecimal;

public class Stats {

    private int beerCount;
    private BigDecimal totalVolume;

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
}