package com.yobombel.brewshare.stats.service;

import com.yobombel.brewshare.stats.model.BeerStatsDto;
import com.yobombel.brewshare.stats.model.StyleStats;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StyleStatsService {

    private final BjcpTagService bjcpTagService;

    public StyleStatsService(BjcpTagService bjcpTagService) {
        this.bjcpTagService = bjcpTagService;
    }

    public StyleStats createStyleStats(List<BeerStatsDto> beerStatsDtos) {
        Map<String, Integer> stylesCount = bjcpTagService.countStyles(beerStatsDtos);

        return new StyleStats(
                bjcpTagService.countTagsPercentages(stylesCount, "family"),
                bjcpTagService.countTagsPercentages(stylesCount, "color"),
                bjcpTagService.countTagsPercentages(stylesCount, "ferment")
        );
    }
}