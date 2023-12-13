package com.yobombel.brewshare.stats.service;

import com.yobombel.brewshare.beer.BeerRepository;
import com.yobombel.brewshare.stats.model.GravityStats;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class GravityStatsServiceTest {

    @Mock
    BeerRepository beerRepository;

    @Spy
    @InjectMocks
    GravityStatsService gravityStatsService;

    @Test
    void shouldSwapInfinityValuesToZero() {
        //GIVEN
        given(beerRepository.findOriginalGravities()).willReturn(List.of());
        //WHEN
        GravityStats gravityStats = gravityStatsService.createGravityStats();
        //THEN
        assertThat(gravityStats.getMaxOriginalGravity()).isZero();
        assertThat(gravityStats.getMinOriginalGravity()).isZero();
    }
}