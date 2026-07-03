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
    public void initialize() {
        Map<String, Integer> globalStats = recipeRepository.getGlobalArchiveStats();
        if (globalStats != null) {
            totalRecipesLabel.setText(String.valueOf(globalStats.getOrDefault("totalRecipes", 0)));
            totalErasLabel.setText(String.valueOf(globalStats.getOrDefault("totalEras", 0)));
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

    @FXML
    public void goBack(ActionEvent event) {
        if (!AppState.hasSelectedRecipe()) {
            goTo(event, "contents-view.fxml");
            return;
        }
        RecipeRepository repo = new RecipeRepository();
        var recipe = repo.findRecipeById(AppState.getSelectedRecipeId());
        if (recipe == null) {
            goTo(event, "contents-view.fxml");
            return;
        }
        String fxml = switch (recipe.getEraId()) {
            case 1 -> "ancient-view.fxml";
            case 2 -> "medieval-view.fxml";
            case 3 -> "renaissance-view.fxml";
            case 4 -> "industrial-view.fxml";
            case 5 -> "century20th-view.fxml";
            case 6 -> "modern-view.fxml";
            default -> "contents-view.fxml";
        };
        goTo(event, fxml);
    }

}