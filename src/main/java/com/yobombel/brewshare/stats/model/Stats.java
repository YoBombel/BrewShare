package com.yobombel.brewshare.stats.model;

import com.yobombel.brewshare.stats.model.SpecificStats.AlcoholStats;
import com.yobombel.brewshare.stats.model.SpecificStats.ColorStats;
import com.yobombel.brewshare.stats.model.SpecificStats.GravityStats;
import com.yobombel.brewshare.stats.model.SpecificStats.IbuStats;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Stats {

    private int beerCount;
    private BigDecimal totalVolume;
    private GravityStats gravityStats;
    private AlcoholStats alcoholStats;
    private IbuStats ibuStats;
    private ColorStats colorStats;

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

    public GravityStats getGravityStats() {
        return gravityStats;
    }

    public void setGravityStats(GravityStats gravityStats) {
        this.gravityStats = gravityStats;
    }

    public AlcoholStats getAlcoholStats() {
        return alcoholStats;
    }

    public void setAlcoholStats(AlcoholStats alcoholStats) {
        this.alcoholStats = alcoholStats;
    }

    public IbuStats getIbuStats() {
        return ibuStats;
    }

    public void setIbuStats(IbuStats ibuStats) {
        this.ibuStats = ibuStats;
    }

    public ColorStats getColorStats() {
        return colorStats;
    }

    public void setColorStats(ColorStats colorStats) {
        this.colorStats = colorStats;
    }
}