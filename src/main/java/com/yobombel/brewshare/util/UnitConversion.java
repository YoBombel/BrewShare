package com.yobombel.brewshare.util;

public class UnitConversion {

    private UnitConversion() {
        throw new IllegalStateException("Utility class shouldn't be initialized.");
    }

    public static double fluidOuncesToLiters(double ounces) {
        return ounces * 0.0295735;
    }

    public static double fluidOuncesToGallons(double ounces) {
        return ounces / 128;
    }

    public static double ouncesToGrams(double ounces) {
        return ounces * 28.3495;
    }

    public static double gravityToPlato(double gravity) {
        return -616.868D + 1111.14D * gravity - 630.262 * Math.pow(gravity, 2) + 135.997 * Math.pow(gravity, 3);
    }

    public static double ouncesToPounds(double ounces) {
        return ounces / 16.0;
    }

    public static double srmToEbc(double srm){
        return srm * 1.97;
    }

}
