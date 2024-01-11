package com.yobombel.brewshare.stats.service;

import com.yobombel.brewshare.beer.BeerRepository;
import com.yobombel.brewshare.stats.model.BeerStatsDto;
import com.yobombel.brewshare.stats.model.Stats;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StatsServiceTest {

    @Mock
    GravityStatsService gravityStatsService;
    @Mock
    AlcoholStatsService alcoholStatsService;
    @Mock
    ColorStatsService colorStatsService;
    @Mock
    IbuStatsService ibuStatsService;
    @Mock
    BeerRepository beerRepository;

    @Spy
    @InjectMocks
    StatsService statsService;

    @Test
    void shouldCallAllStatServices(){
        //GIVEN
        given(beerRepository.findAllByBatchSizeIsNotNull()).willReturn(List.of(new BeerStatsDto(BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO)));
        //WHEN
        Stats stats = statsService.createStats();
        //THEN
        verify(beerRepository).findAllByBatchSizeIsNotNull();
        verify(gravityStatsService).calculateStats(anyList(), any());
        verify(alcoholStatsService).calculateStats(anyList(), any());
        verify(colorStatsService).calculateStats(anyList(), any());
        verify(ibuStatsService).calculateStats(anyList(), any());

    }

}