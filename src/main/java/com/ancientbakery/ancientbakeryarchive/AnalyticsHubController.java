package com.ancientbakery.ancientbakeryarchive;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Map;

public class AnalyticsHubController extends BaseNavigator {
    private final RecipeRepository recipeRepository = new RecipeRepository();

    @FXML private Label totalRecipesLabel;
    @FXML private Label totalErasLabel;

    @FXML
    private Label complexRecipeLabel;
    @FXML
    private Label ancientIngredientLabel;

    @FXML
    public void initialize() {
        Map<String, Object> globalStats = recipeRepository.getGlobalArchiveStats();
        if (globalStats != null) {

            if (totalRecipesLabel != null) {
                totalRecipesLabel.setText(String.valueOf(globalStats.getOrDefault("totalRecipes", 0)));
            }
            if (totalErasLabel != null) {
                totalErasLabel.setText(String.valueOf(globalStats.getOrDefault("totalEras", 0)));
            }

            if (complexRecipeLabel != null) {
                complexRecipeLabel.setText(String.valueOf(globalStats.getOrDefault("complexRecipe", "N/A")));
            }

            if (ancientIngredientLabel != null) {
                String ancientIngredient = String.valueOf(globalStats.getOrDefault("mostCommonAncient", "N/A"));
                if (!ancientIngredient.equals("N/A") && !ancientIngredient.isEmpty()) {
                    ancientIngredient = ancientIngredient.substring(0, 1).toUpperCase() + ancientIngredient.substring(1);
                }
                ancientIngredientLabel.setText(ancientIngredient);
            }
        }
    }

    @FXML
    public void goCookingFats(ActionEvent event) {
        goTo(event, "cooking-fats-view.fxml");
    }

    @FXML
    public void goSweeteners(ActionEvent event) {
        goTo(event, "sweetener-graph-view.fxml");
    }

    @FXML
    public void goYeastTimeline(ActionEvent event) {
        goTo(event, "yeast-timeline-view.fxml");
    }
}