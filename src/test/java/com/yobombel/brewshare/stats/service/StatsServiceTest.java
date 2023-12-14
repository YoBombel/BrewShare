package com.yobombel.brewshare.stats.service;

import com.yobombel.brewshare.beer.Beer;
import com.yobombel.brewshare.beer.BeerRepository;
import com.yobombel.brewshare.beer.dto.BeerStatsDto;
import com.yobombel.brewshare.stats.model.Stats;
import com.yobombel.brewshare.stats.service.specificStatsServices.AlcoholStatsService;
import com.yobombel.brewshare.stats.service.specificStatsServices.ColorStatsService;
import com.yobombel.brewshare.stats.service.specificStatsServices.GravityStatsService;
import com.yobombel.brewshare.stats.service.specificStatsServices.IbuStatsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

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
        given(beerRepository.findAllByBatchSizeIsNotNull()).willReturn(List.of(new BeerStatsDto(0.0,0.0,0.0,0.0,0.0)));
        //WHEN
        Stats stats = statsService.getStats();
        //THEN
        verify(beerRepository).findAllByBatchSizeIsNotNull();
        verify(gravityStatsService).calculateStats(anyList(), any());
        verify(alcoholStatsService).calculateStats(anyList(), any());
        verify(colorStatsService).calculateStats(anyList(), any());
        verify(ibuStatsService).calculateStats(anyList(), any());

    }

}