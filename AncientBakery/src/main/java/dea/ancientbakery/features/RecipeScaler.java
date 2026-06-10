package dea.ancientbakery.features;

import dea.ancientbakery.model.RecipeIngredient;

import java.util.ArrayList;
import java.util.List;

public class RecipeScaler {

    public static double scaleAmount(double originalAmount, double multiplier) {
        if (originalAmount < 0) {
            throw new IllegalArgumentException("Original amount cannot be negative.");
        }

        if (multiplier <= 0) {
            throw new IllegalArgumentException("Multiplier must be greater than zero.");
        }

        return originalAmount * multiplier;
    }

    public static RecipeIngredient scaleIngredient(RecipeIngredient ingredient, double multiplier) {
        double scaledQuantity = scaleAmount(ingredient.getQuantity(), multiplier);

        return new RecipeIngredient(
                ingredient.getRecipeId(),
                ingredient.getIngredientId(),
                ingredient.getIngredientName(),
                scaledQuantity,
                ingredient.getUnit()
        );
    }

    public static List<RecipeIngredient> scaleRecipe(List<RecipeIngredient> ingredients, double multiplier) {
        List<RecipeIngredient> scaledIngredients = new ArrayList<>();

        for (RecipeIngredient ingredient : ingredients) {
            scaledIngredients.add(scaleIngredient(ingredient, multiplier));
        }

        return scaledIngredients;
    }
}
