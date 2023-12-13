package com.yobombel.brewshare.stats.service.specificStatsServices;

import com.yobombel.brewshare.stats.model.SpecificStats.ColorStats;
import com.yobombel.brewshare.stats.model.SpecificStats.SpecificStats;
import org.springframework.stereotype.Service;

@Service
public class ColorStatsService extends AbstractSpecificStatsService {

    @Override
    public SpecificStats createSpecificStats(double average, double max, double min) {
        return new ColorStats(average, max, min);
    }
}
