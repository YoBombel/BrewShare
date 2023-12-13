package com.yobombel.brewshare.stats.service.specificStatsServices;

import com.yobombel.brewshare.stats.model.SpecificStats.GravityStats;
import com.yobombel.brewshare.stats.model.SpecificStats.SpecificStats;
import org.springframework.stereotype.Component;

@Component
public class GravityStatsService extends AbstractSpecificStatsService{

    @Override
    public SpecificStats createSpecificStats(double average, double max, double min) {
        return new GravityStats(average, max, min);
    }
}
