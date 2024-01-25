package com.yobombel.brewshare.stats.service;

import com.yobombel.brewshare.stats.model.BeerSpecDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
class AggregateStatsServiceTest {

    List<BeerSpecDto> beerSpecDtos;
    AggregateStatsService aggregateStatsService;
    BigDecimal[] firstAggregateStats;

    @BeforeEach
    void setUp() {
        aggregateStatsService = new AggregateStatsService();
        BeerSpecDto beerSpecDto1 = new BeerSpecDto("", BigDecimal.ZERO, BigDecimal.valueOf(10.0), BigDecimal.valueOf(10.0), BigDecimal.valueOf(10.0), BigDecimal.valueOf(10.0));
        BeerSpecDto beerSpecDto2 = new BeerSpecDto("", BigDecimal.ZERO, BigDecimal.valueOf(20.0), BigDecimal.valueOf(20.0), BigDecimal.valueOf(20.0), BigDecimal.valueOf(20.0));
        beerSpecDtos = List.of(beerSpecDto1, beerSpecDto2);
        var aggregateStats = aggregateStatsService.createAggregateStats(beerSpecDtos);
        firstAggregateStats = aggregateStats.get(0).minMaxAvg();
    }

    @Test
    void shouldCalculateMin() {
        //GIVEN
        BigDecimal expected = BigDecimal.valueOf(10.0);
        //WHEN
        //THEN
        assertThat(expected, Matchers.comparesEqualTo(firstAggregateStats[0]));
    }

    @Test
    void shouldCalculateMax() {
        //GIVEN
        BigDecimal expected = BigDecimal.valueOf(20.0);
        //WHEN
        //THEN
        assertThat(expected, Matchers.comparesEqualTo(firstAggregateStats[1]));
    }

    @Test
    void shouldCalculateAvg() {
        //GIVEN
        BigDecimal expected = BigDecimal.valueOf(15.0);
        //WHEN
        //THEN
        assertThat(expected, Matchers.comparesEqualTo(firstAggregateStats[2]));
    }

}