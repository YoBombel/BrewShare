package com.yobombel.brewshare.beer.dto;

public record BeerStatsDto
        (double batchSize,
         double originalGravity,
         double abv,
         double ibu,
         double color) {}