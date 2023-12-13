package com.yobombel.brewshare.stats.model.SpecificStats;

public abstract class SpecificStats {
    private double average;
    private double max;
    private double min;

    protected SpecificStats(double average, double max, double min) {
        this.average = average;
        this.max = max;
        this.min = min;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }
}