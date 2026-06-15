package dea.ancientbakery.features;

import dea.ancientbakery.model.RecipeIngredient;

import java.util.ArrayList;
import java.util.List;

public class UnitConverter {
    private static final double GRAMS_PER_OUNCE = 28.3495;
    private static final double MILLILITERS_PER_CUP = 236.588;

    public static double gramsToOunces(double grams) {
        return grams / GRAMS_PER_OUNCE;
    }

    public static double ouncesToGrams(double ounces) {
        return ounces * GRAMS_PER_OUNCE;
    }

    public static double millilitersToCups(double milliliters) {
        return milliliters / MILLILITERS_PER_CUP;
    }

    public static double cupsToMilliliters(double cups) {
        return cups * MILLILITERS_PER_CUP;
    }

    public static RecipeIngredient convertIngredient(RecipeIngredient ingredient, String targetSystem) {
        String unit = ingredient.getUnit().toLowerCase();
        double quantity = ingredient.getQuantity();

        if (targetSystem.equalsIgnoreCase("imperial")) {
            if (unit.equals("g") || unit.equals("gram") || unit.equals("grams")) {
                return copyWithNewMeasurement(ingredient, gramsToOunces(quantity), "oz");
            }

            if (unit.equals("ml") || unit.equals("milliliter") || unit.equals("milliliters")) {
                return copyWithNewMeasurement(ingredient, millilitersToCups(quantity), "cups");
            }
        }

        if (targetSystem.equalsIgnoreCase("metric")) {
            if (unit.equals("oz") || unit.equals("ounce") || unit.equals("ounces")) {
                return copyWithNewMeasurement(ingredient, ouncesToGrams(quantity), "g");
            }

            if (unit.equals("cup") || unit.equals("cups")) {
                return copyWithNewMeasurement(ingredient, cupsToMilliliters(quantity), "ml");
            }
        }

        return ingredient;
    }

    public static List<RecipeIngredient> convertRecipe(List<RecipeIngredient> ingredients, String targetSystem) {
        List<RecipeIngredient> convertedIngredients = new ArrayList<>();

        for (RecipeIngredient ingredient : ingredients) {
            convertedIngredients.add(convertIngredient(ingredient, targetSystem));
        }

        return convertedIngredients;
    }

    private static RecipeIngredient copyWithNewMeasurement(RecipeIngredient ingredient, double quantity, String unit) {
        return new RecipeIngredient(
                ingredient.getRecipeId(),
                ingredient.getIngredientId(),
                ingredient.getIngredientName(),
                quantity,
                unit
        );
    }
}