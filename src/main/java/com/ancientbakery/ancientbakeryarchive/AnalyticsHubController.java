package com.ancientbakery.ancientbakeryarchive;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;

import java.util.Map;

public class AnalyticsHubController extends BaseNavigator {
    private final RecipeRepository recipeRepository = new RecipeRepository();

    public static String sourceEra = "Ancient"; // fallback default

    @FXML private TabPane standaloneTabPane;
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
        if (standaloneTabPane != null) {
            standaloneTabPane.getSelectionModel().selectLast();

            standaloneTabPane.getSelectionModel().selectedIndexProperty().addListener(
                    (obs, oldIndex, newIndex) -> {
                        if (newIndex.intValue() < 3) {
                            try {
                                javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(
                                        getClass().getResource(
                                                "/com/ancientbakery/ancientbakeryarchive/fxml/ancient-view.fxml"));
                                javafx.scene.Parent root = loader.load();
                                javafx.stage.Stage stage = (javafx.stage.Stage) standaloneTabPane.getScene().getWindow();
                                BaseNavigator.setResponsiveScene(stage, root);
                            } catch (java.io.IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
            );
        }
    }

    @FXML
    public void goEraBreakdown(ActionEvent event) {
        goTo(event, "era-breakdown-view.fxml");
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

    public void handleSearch(ActionEvent actionEvent) {
    }
}