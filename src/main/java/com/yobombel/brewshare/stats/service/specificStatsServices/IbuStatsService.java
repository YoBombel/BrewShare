package com.yobombel.brewshare.stats.service.specificStatsServices;

import com.yobombel.brewshare.stats.model.SpecificStats.IbuStats;
import com.yobombel.brewshare.stats.model.SpecificStats.SpecificStats;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class IbuStatsService extends AbstractSpecificStatsService {

    @Override
    public SpecificStats createSpecificStats(BigDecimal average, BigDecimal max, BigDecimal min) {
        return new IbuStats(average, max, min);
    }
}
