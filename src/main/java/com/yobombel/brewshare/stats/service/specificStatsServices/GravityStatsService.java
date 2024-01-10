package com.yobombel.brewshare.stats.service.specificStatsServices;

import com.yobombel.brewshare.stats.model.SpecificStats.GravityStats;
import com.yobombel.brewshare.stats.model.SpecificStats.SpecificStats;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class GravityStatsService extends AbstractSpecificStatsService{

    @Override
    public SpecificStats createSpecificStats(BigDecimal average, BigDecimal max, BigDecimal min) {
        return new GravityStats(average, max, min);
    }

}
