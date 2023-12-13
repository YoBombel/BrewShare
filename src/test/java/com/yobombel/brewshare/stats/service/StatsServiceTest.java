package com.yobombel.brewshare.stats.service;

import com.yobombel.brewshare.beer.BeerRepository;
import com.yobombel.brewshare.stats.model.Stats;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StatsServiceTest {

    @Mock
    BeerRepository beerRepository;
    @Mock
    GravityStatsService gravityStatsService;

    @Spy
    @InjectMocks
    StatsService statsService;

    @Test
    void shouldCallAllStatServices(){
        //GIVEN
        //WHEN
        Stats stats = statsService.getStats();
        //THEN
        verify(beerRepository).findBatchSizes();
        verify(gravityStatsService).createGravityStats();
    }

}