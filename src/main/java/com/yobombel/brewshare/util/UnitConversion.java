package com.yobombel.brewshare.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class UnitConversion {

    private UnitConversion() {
        throw new IllegalStateException("Utility class shouldn't be initialized.");
    }

    public static BigDecimal fluidOuncesToLiters(BigDecimal ounces) {
        return ounces.multiply(BigDecimal.valueOf(0.0295735));
    }

    public static BigDecimal fluidOuncesToGallons(BigDecimal ounces) {
        return ounces.divide(BigDecimal.valueOf(128), RoundingMode.HALF_UP);
    }

    public static BigDecimal ouncesToGrams(BigDecimal ounces) {
        return ounces.multiply(BigDecimal.valueOf(28.3495));    }

    public static BigDecimal gravityToPlato(BigDecimal gravity) {
        double gravityDouble = gravity.doubleValue();
        double platoGravity = -616.868D + 1111.14D * gravityDouble - 630.262 * Math.pow(gravityDouble, 2) + 135.997 * Math.pow(gravityDouble, 3);
        return BigDecimal.valueOf(platoGravity);
    }

    public static BigDecimal ouncesToPounds(BigDecimal ounces) {
        return ounces.divide(BigDecimal.valueOf(16), RoundingMode.HALF_UP);
    }

    public static BigDecimal srmToEbc(BigDecimal srm){
        return srm.multiply(BigDecimal.valueOf(1.97));
    }

}
