package com.yobombel.brewshare.beer.dto;

import java.math.BigDecimal;

public record BeerStatsDto
        (BigDecimal batchSize,
         BigDecimal originalGravity,
         BigDecimal abv,
         BigDecimal ibu,
         BigDecimal color) {}