package com.ancientbakery.ancientbakeryarchive;

public final class AppState {

    private static int selectedRecipeId = -1;
    private static boolean openedFromBakeNowPictures = false;

    private AppState() {
    }

    public static void selectRecipe(int recipeId) {
        selectedRecipeId = recipeId;
    }

    public static void selectRecipeFromBakeNowPictures(int recipeId) {
        selectedRecipeId = recipeId;
        openedFromBakeNowPictures = true;
    }

    public static boolean hasSelectedRecipe() {
        return selectedRecipeId > 0;
    }

    public static int getSelectedRecipeId() {
        return selectedRecipeId;
    }

    public static boolean wasOpenedFromBakeNowPictures() {
        return openedFromBakeNowPictures;
    }

    public static void clearBakeNowPicturesNavigation() {
        openedFromBakeNowPictures = false;
    }
}