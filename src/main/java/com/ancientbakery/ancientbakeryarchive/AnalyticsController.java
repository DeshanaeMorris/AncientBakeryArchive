package com.ancientbakery.ancientbakeryarchive;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Map;

public class AnalyticsController extends BaseNavigator {
    private final RecipeRepository recipeRepository = new RecipeRepository();

    @FXML private Label totalRecipesLabel;
    @FXML private Label totalErasLabel;
    @FXML private VBox leaderboardContainer;

    @FXML
    public void initialize() {
        System.out.println("Analytics Dashboard loading real-time data...");

        // Summary KPI widget
        Map<String, Integer> globalStats = recipeRepository.getGlobalArchiveStats();
        if (globalStats != null) {
            totalRecipesLabel.setText(String.valueOf(globalStats.getOrDefault("totalRecipes", 0)));
            totalErasLabel.setText(String.valueOf(globalStats.getOrDefault("totalEras", 0)));
        }

        //Cooking fat leaderboard
        Map<String, Integer> fatRankings = recipeRepository.getCookingFatRankings();
        leaderboardContainer.getChildren().clear(); // Clear any old preview boxes

        if (fatRankings != null) {
            for (Map.Entry<String, Integer> entry : fatRankings.entrySet()) {
                // Building container for each item
                HBox row = new HBox();
                row.setSpacing(20);
                row.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
                row.setStyle("-fx-padding: 10; -fx-background-color: rgba(255,255,255,0.5); -fx-background-radius: 6;");

                // IngredientName Label
                Label fatName = new Label(entry.getKey());
                fatName.setPrefWidth(120);
                fatName.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #3b2314;");

                // IngredientCount Label
                Label countLabel = new Label(entry.getValue() + " Recipes");
                countLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #5c3a21;");

                row.getChildren().addAll(fatName, countLabel);
                leaderboardContainer.getChildren().add(row);
            }
        }
    }
}