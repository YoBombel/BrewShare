package com.yobombel.brewshare.stats.service;

import com.yobombel.brewshare.beer.dto.BeerStatsDto;
import com.yobombel.brewshare.stats.service.specificStatsServices.AlcoholStatsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class AbstractSpecificStatsServicesTest {

    @Test
    void shouldSwapInfinityValuesToZero(){
        //GIVEN
        AlcoholStatsService alcoholStatsService = new AlcoholStatsService();
        List<BeerStatsDto> beerStatsDtos = List.of();

        //WHEN
        var result = alcoholStatsService.calculateStats(beerStatsDtos, BeerStatsDto::abv);

        //THEN
        assertThat(result.getMax()).isZero();
        assertThat(result.getMin()).isZero();
        assertThat(result.getAverage()).isZero();
    }
}