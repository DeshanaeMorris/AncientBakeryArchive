package com.ancientbakery.ancientbakeryarchive.features;

public class UnitConverter {

    public record ConvertedMeasurement(double amount, String unit) {}

    public static ConvertedMeasurement toImperial(double amount, String unit) {
        String cleanUnit = unit == null ? "" : unit.trim().toLowerCase();

        return switch (cleanUnit) {
            case "g", "gram", "grams" ->
                    new ConvertedMeasurement(amount / 28.35, "oz");
            case "kg", "kilogram", "kilograms" ->
                    new ConvertedMeasurement(amount * 35.274, "oz");
            case "ml", "milliliter", "milliliters" ->
                    new ConvertedMeasurement(amount / 240.0, "cups");
            case "l", "liter", "liters" ->
                    new ConvertedMeasurement(amount * 4.22675, "cups");
            default ->
                    new ConvertedMeasurement(amount, unit);
        };
    }

    public static ConvertedMeasurement toMetric(double amount, String unit) {
        String cleanUnit = unit == null ? "" : unit.trim().toLowerCase();

        return switch (cleanUnit) {
            case "oz", "ounce", "ounces" ->
                    new ConvertedMeasurement(amount * 28.35, "g");
            case "lb", "lbs", "pound", "pounds" ->
                    new ConvertedMeasurement(amount * 453.592, "g");
            case "cup", "cups" ->
                    new ConvertedMeasurement(amount * 240.0, "ml");
            case "tbsp", "tablespoon", "tablespoons" ->
                    new ConvertedMeasurement(amount * 15.0, "ml");
            case "tsp", "teaspoon", "teaspoons" ->
                    new ConvertedMeasurement(amount * 5.0, "ml");
            default ->
                    new ConvertedMeasurement(amount, unit);
        };
    }
}

