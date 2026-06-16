package com.ancientbakery.ancientbakeryarchive;

import com.ancientbakery.ancientbakeryarchive.model.Recipe;

import java.util.List;

public class EraFilterTest {

    public static void main(String[] args) {
        EraFilterService service = new EraFilterService();

        printRecipes("Ancient Recipes:", service.getRecipesByEra(1));
        printRecipes("Renaissance Recipes:", service.getRecipesByEra(3));
        printRecipes("Word Search - cheese:", service.searchRecipesByWord("cheese"));
        printRecipes("Ingredient Search - honey:", service.getRecipesByIngredient("honey"));
    }

    private static void printRecipes(String title, List<Recipe> recipes) {
        System.out.println();
        System.out.println(title);

        for (Recipe recipe : recipes) {
            System.out.println(recipe.getName());
        }
    }
}