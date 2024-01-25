package com.yobombel.brewshare.stats.model;

import java.math.BigDecimal;

public record BeerSpecDto(
        String style,
        BigDecimal batchSize,
        BigDecimal originalGravity,
        BigDecimal abv,
        BigDecimal ibu,
        BigDecimal color) {
}