package com.yobombel.brewshare.stats.service.bjcp;

import com.yobombel.brewshare.stats.model.BeerSpecDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BjcpTagServiceTest {

    BjcpTagService bjcpTagService;
    List<BeerSpecDto> beerSpecDtos;
    BjcpGuidelinesService bjcpGuidelinesService;

    @BeforeEach
    void setUp(){
        bjcpGuidelinesService = new BjcpGuidelinesService();
        bjcpTagService = new BjcpTagService(bjcpGuidelinesService);
        BeerSpecDto beerSpecDto1 = new BeerSpecDto("Altbier", BigDecimal.ZERO, BigDecimal.valueOf(10.0), BigDecimal.valueOf(10.0), BigDecimal.valueOf(10.0), BigDecimal.valueOf(10.0));
        BeerSpecDto beerSpecDto2 = new BeerSpecDto("Altbier", BigDecimal.ZERO, BigDecimal.valueOf(20.0), BigDecimal.valueOf(20.0), BigDecimal.valueOf(20.0), BigDecimal.valueOf(20.0));
        BeerSpecDto beerSpecDto3 = new BeerSpecDto("American Lager", BigDecimal.ZERO, BigDecimal.valueOf(10.0), BigDecimal.valueOf(10.0), BigDecimal.valueOf(10.0), BigDecimal.valueOf(10.0));
        BeerSpecDto beerSpecDto4 = new BeerSpecDto("American Porter", BigDecimal.ZERO, BigDecimal.valueOf(10.0), BigDecimal.valueOf(10.0), BigDecimal.valueOf(10.0), BigDecimal.valueOf(10.0));
        beerSpecDtos = List.of(beerSpecDto1, beerSpecDto2, beerSpecDto3, beerSpecDto4);
    }

    @Test
    void shouldCountStyles(){
        //GIVEN
        var altbierCount = 2;
        var otherCounts = 1;
        //WHEN
        Map<String, Integer> result = bjcpTagService.countStyles(beerSpecDtos);
        //THEN
        assertEquals(result.get("Altbier"), altbierCount);
        assertEquals(result.get("American Lager"), otherCounts);
        assertEquals(result.get("American Porter"), otherCounts);
    }

    @Test
    void shouldCountPercentages(){
        //GIVEN
        List<String> tags = List.of("color");
        var expectedAmberColorPercentage = BigDecimal.valueOf(50.0);
        var expectedOtherColorsPercentages = BigDecimal.valueOf(25.0);
        var stylesCount = bjcpTagService.countStyles(beerSpecDtos);

        //WHEN
        var resultMap = bjcpTagService.countTagsPercentages(stylesCount, tags);
        var resultAmber = resultMap.get("Amber color");
        var resultPale = resultMap.get("Pale color");
        var resultDark = resultMap.get("Dark color");
        //THEN
        assertThat(expectedAmberColorPercentage, Matchers.comparesEqualTo(resultAmber));
        assertThat(expectedOtherColorsPercentages, Matchers.comparesEqualTo(resultPale));
        assertThat(expectedOtherColorsPercentages, Matchers.comparesEqualTo(resultDark));
    }

}