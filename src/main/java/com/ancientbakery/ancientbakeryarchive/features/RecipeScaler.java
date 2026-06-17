package com.ancientbakery.ancientbakeryarchive.features;

public final class RecipeScaler {
    private RecipeScaler() {
    }

    public static double scaleAmount(double originalAmount, double multiplier) {
        if (multiplier <= 0) {
            throw new IllegalArgumentException("Please enter a positive portion multiplier.");
        }
        return originalAmount * multiplier;
    }
}
