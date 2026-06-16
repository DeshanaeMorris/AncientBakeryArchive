package com.ancientbakery.ancientbakeryarchive;

public final class AppState {
    private static int selectedRecipeId = -1;

    private AppState() {
    }

    public static void selectRecipe(int recipeId) {
        selectedRecipeId = recipeId;
    }

    public static boolean hasSelectedRecipe() {
        return selectedRecipeId > 0;
    }

    public static int getSelectedRecipeId() {
        return selectedRecipeId;
    }
}
