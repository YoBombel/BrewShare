package com.yobombel.brewshare.stats.service;

import com.yobombel.brewshare.beer.BeerRepository;
import com.yobombel.brewshare.stats.model.BeerSpecDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StatsServiceTest {

    @Mock
    BeerRepository beerRepository;
    @Mock
    AggregateStatsService aggregateStatsService;
    @Mock
    StyleStatsService styleStatsService;

    @Spy
    @InjectMocks
    StatsService statsService;

    @Test
    void shouldCountTotalVolume() {
        //GIVEN
        BeerSpecDto beerDto1 = new BeerSpecDto("", BigDecimal.valueOf(25), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        BeerSpecDto beerDto2 = new BeerSpecDto("", BigDecimal.valueOf(30), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        List<BeerSpecDto> beerSpecDtos = List.of(beerDto1, beerDto2);
        BigDecimal expected = BigDecimal.valueOf(55.0);
        given(beerRepository.findAllByBatchSizeIsNotNull()).willReturn(beerSpecDtos);
        //WHEN
        var result = statsService.createStats().getTotalVolume();
        //THEN
        assertThat(expected, Matchers.comparesEqualTo(result));
    }

    @Test
    void shouldCallOtherStatsServices(){
        //GIVEN
        //WHEN
        var result = statsService.createStats();
        //THEN
        verify(aggregateStatsService).createAggregateStats(anyList());
        verify(styleStatsService).createStyleStats(anyList());
    }


}