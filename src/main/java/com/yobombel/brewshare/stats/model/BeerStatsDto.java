package com.yobombel.brewshare.stats.model;

import java.math.BigDecimal;

public record BeerStatsDto(
        BigDecimal batchSize,
        BigDecimal originalGravity,
        BigDecimal abv,
        BigDecimal ibu,
        BigDecimal color) {
}