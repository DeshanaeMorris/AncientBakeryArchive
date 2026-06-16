package com.ancientbakery.ancientbakeryarchive;

import com.ancientbakery.ancientbakeryarchive.model.Era;
import com.ancientbakery.ancientbakeryarchive.model.Recipe;
import com.ancientbakery.ancientbakeryarchive.model.RecipeIngredient;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.stream.Collectors;

public abstract class EraRecipeController extends BaseNavigator {
    @FXML
    private VBox eraTitleBox;

    @FXML
    private VBox recipeCardsBox;

    private final RecipeRepository recipeRepository = new RecipeRepository();
    private VBox selectedCard;

    protected abstract int getEraId();

    @FXML
    public void initialize() {
        loadRecipeTab();
    }

    private void loadRecipeTab() {
        Era era = recipeRepository.findEraById(getEraId());
        loadEraTitle(era);
        loadRecipeCards();
    }

    private void loadEraTitle(Era era) {
        if (eraTitleBox == null) {
            return;
        }

        eraTitleBox.getChildren().clear();

        String eraName = era == null ? "Historical Era" : safeText(era.getName(), "Historical Era");
        String timePeriod = era == null ? "" : safeText(era.getTimePeriod(), "");

        Label title = new Label(eraName + " Recipes");
        title.getStyleClass().add("era-intro-title");
        title.setWrapText(true);
        title.setMaxWidth(340);

        Label dates = new Label(timePeriod);
        dates.getStyleClass().add("era-intro-dates");
        dates.setWrapText(true);
        dates.setMaxWidth(340);
        dates.setVisible(!timePeriod.isBlank());
        dates.setManaged(!timePeriod.isBlank());

        eraTitleBox.getChildren().addAll(title, dates);
    }

    private void loadRecipeCards() {
        if (recipeCardsBox == null) {
            return;
        }

        recipeCardsBox.getChildren().clear();
        List<Recipe> recipes = recipeRepository.findRecipesByEra(getEraId());

        if (recipes.isEmpty()) {
            Label emptyMessage = new Label("No recipes have been added for this era yet.");
            emptyMessage.getStyleClass().add("recipe-card");
            emptyMessage.setPadding(new Insets(10));
            emptyMessage.setWrapText(true);
            recipeCardsBox.getChildren().add(emptyMessage);
            return;
        }

        for (Recipe recipe : recipes) {
            recipeCardsBox.getChildren().add(createRecipeCard(recipe));
        }
    }

    private VBox createRecipeCard(Recipe recipe) {
        VBox card = new VBox(7);
        card.setPadding(new Insets(10));
        card.getStyleClass().add("recipe-card");
        card.setMaxWidth(380);

        Label title = new Label(recipe.getName());
        title.getStyleClass().add("recipe-card-title");
        title.setWrapText(true);
        title.setMaxWidth(350);

        Label historicalHeader = new Label("Original Text:");
        historicalHeader.getStyleClass().add("recipe-meta");

        Label historicalText = new Label(safeText(recipe.getOriginalText(), "Not available"));
        historicalText.getStyleClass().add("recipe-body");
        historicalText.setWrapText(true);
        historicalText.setMaxWidth(350);

        Label ingredientsHeader = new Label("Ingredients:");
        ingredientsHeader.getStyleClass().add("recipe-meta");

        Label ingredients = new Label(formatIngredients(recipe.getId()));
        ingredients.getStyleClass().add("recipe-body");
        ingredients.setWrapText(true);
        ingredients.setMaxWidth(350);

        card.setOnMouseClicked(event -> selectRecipe(recipe, card));
        card.getChildren().addAll(title, historicalHeader, historicalText, ingredientsHeader, ingredients);
        return card;
    }

    private String formatIngredients(int recipeId) {
        List<RecipeIngredient> ingredients = recipeRepository.findIngredientsByRecipeId(recipeId);

        if (ingredients.isEmpty()) {
            return "No ingredients listed.";
        }

        return ingredients.stream()
                .map(ingredient -> "• " + safeText(ingredient.getIngredientName(), "Ingredient") +
                        " — " + format(ingredient.getQuantity()) + " " + safeText(ingredient.getUnit(), ""))
                .collect(Collectors.joining("\n"));
    }

    private void selectRecipe(Recipe recipe, VBox card) {
        if (selectedCard != null) {
            selectedCard.getStyleClass().remove("recipe-card-selected");
        }

        selectedCard = card;
        if (!card.getStyleClass().contains("recipe-card-selected")) {
            card.getStyleClass().add("recipe-card-selected");
        }

        AppState.selectRecipe(recipe.getId());
    }

    private String format(double value) {
        if (value == Math.rint(value)) {
            return String.valueOf((int) value);
        }
        return String.format("%.2f", value);
    }

    private String safeText(String value, String fallback) {
        return value == null || value.isBlank() ? fallback : value;
    }
}
