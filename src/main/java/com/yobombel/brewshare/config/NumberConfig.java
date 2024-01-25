package com.yobombel.brewshare.config;

import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Configuration
public class NumberConfig {

    public static BigDecimal setDefaultScale(BigDecimal bigDecimal){
        return bigDecimal.setScale(1, RoundingMode.HALF_UP);
    }

}