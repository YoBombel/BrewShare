package com.yobombel.brewshare.stats.model;

public class GravityStats {

    private double averageOriginalGravity;
    private double maxOriginalGravity;
    private double minOriginalGravity;

    public GravityStats(double averageOriginalGravity, double maxOriginalGravity, double minOriginalGravity) {
        this.averageOriginalGravity = averageOriginalGravity;
        this.maxOriginalGravity = maxOriginalGravity;
        this.minOriginalGravity = minOriginalGravity;
    }

    public double getAverageOriginalGravity() {
        return averageOriginalGravity;
    }

    public void setAverageOriginalGravity(double averageOriginalGravity) {
        this.averageOriginalGravity = averageOriginalGravity;
    }

    public double getMaxOriginalGravity() {
        return maxOriginalGravity;
    }

    public void setMaxOriginalGravity(double maxOriginalGravity) {
        this.maxOriginalGravity = maxOriginalGravity;
    }

    public double getMinOriginalGravity() {
        return minOriginalGravity;
    }

    public void setMinOriginalGravity(double minOriginalGravity) {
        this.minOriginalGravity = minOriginalGravity;
    }
}