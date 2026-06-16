package com.ancientbakery.ancientbakeryarchive.features;

public final class UnitConverter {
    private static final double GRAMS_PER_OUNCE = 28.3495;
    private static final double MILLILITERS_PER_CUP = 236.588;
    private static final double OUNCES_PER_POUND = 16.0;

    private UnitConverter() {
    }

    public record ConvertedMeasurement(double amount, String unit) {
    }

    public static ConvertedMeasurement toImperial(double amount, String unit) {
        if (unit == null || unit.isBlank()) {
            return new ConvertedMeasurement(amount, "");
        }

        String normalized = unit.trim().toLowerCase();

        return switch (normalized) {
            case "g", "gram", "grams" -> new ConvertedMeasurement(amount / GRAMS_PER_OUNCE, "oz");
            case "kg", "kilogram", "kilograms" -> new ConvertedMeasurement((amount * 1000) / GRAMS_PER_OUNCE, "oz");
            case "ml", "milliliter", "milliliters" -> new ConvertedMeasurement(amount / MILLILITERS_PER_CUP, "cups");
            case "l", "liter", "liters" -> new ConvertedMeasurement((amount * 1000) / MILLILITERS_PER_CUP, "cups");
            case "lb", "lbs", "pound", "pounds" -> new ConvertedMeasurement(amount * OUNCES_PER_POUND, "oz");
            default -> new ConvertedMeasurement(amount, unit);
        };
    }
}
