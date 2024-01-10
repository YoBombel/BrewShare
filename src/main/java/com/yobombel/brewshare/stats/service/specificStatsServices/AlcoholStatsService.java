package com.yobombel.brewshare.stats.service.specificStatsServices;

import com.yobombel.brewshare.stats.model.SpecificStats.AlcoholStats;
import com.yobombel.brewshare.stats.model.SpecificStats.IbuStats;
import com.yobombel.brewshare.stats.model.SpecificStats.SpecificStats;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AlcoholStatsService extends AbstractSpecificStatsService {

    @Override
    public SpecificStats createSpecificStats(BigDecimal average, BigDecimal max, BigDecimal min) {
        return new AlcoholStats(average, max, min);
    }
}