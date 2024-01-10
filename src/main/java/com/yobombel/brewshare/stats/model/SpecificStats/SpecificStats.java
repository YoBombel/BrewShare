package com.yobombel.brewshare.stats.model.SpecificStats;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class SpecificStats {
    private BigDecimal average;
    private BigDecimal max;
    private BigDecimal min;

    protected SpecificStats(BigDecimal average, BigDecimal max, BigDecimal min) {
        this.average = average.setScale(2, RoundingMode.HALF_UP);
        this.max = max.setScale(2, RoundingMode.HALF_UP);
        this.min = min.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getAverage() {
        return average;
    }

    public void setAverage(BigDecimal average) {
        this.average = average.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min.setScale(2, RoundingMode.HALF_UP);
    }
}