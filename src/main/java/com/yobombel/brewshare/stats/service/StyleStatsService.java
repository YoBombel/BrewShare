package com.yobombel.brewshare.stats.service;

import com.yobombel.brewshare.stats.model.BeerSpecDto;
import com.yobombel.brewshare.stats.model.StyleStats;
import com.yobombel.brewshare.stats.service.bjcp.BjcpTagService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StyleStatsService {

    private final BjcpTagService bjcpTagService;

    public StyleStatsService(BjcpTagService bjcpTagService) {
        this.bjcpTagService = bjcpTagService;
    }

    public StyleStats createStyleStats(List<BeerSpecDto> beerSpecDtos) {
        Map<String, Integer> stylesCount = bjcpTagService.countStyles(beerSpecDtos);

        return new StyleStats(
                bjcpTagService.countTagsPercentages(stylesCount, List.of("family")),
                bjcpTagService.countTagsPercentages(stylesCount, List.of("color")),
                bjcpTagService.countTagsPercentages(stylesCount, List.of("ferment")),
                bjcpTagService.countTagsPercentages(stylesCount, List.of("bitter", "malty", "balanced"))
        );
    }
}