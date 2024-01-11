package com.yobombel.brewshare.stats.model;

import com.yobombel.brewshare.stats.model.enums.StatType;

import java.math.BigDecimal;

public record AggregateStat(
        StatType statType,
        BigDecimal[] minMaxAvg) {
}