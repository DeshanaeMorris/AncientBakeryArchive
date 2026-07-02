package com.ancientbakery.ancientbakeryarchive;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.event.ActionEvent;
import java.util.Map;

public class CookingFatsController extends BaseNavigator {
    private final RecipeRepository recipeRepository = new RecipeRepository();

    @FXML
    public void goBack(ActionEvent event) {
        goTo(event, "analytics-hub-standalone-view.fxml");
    }

    @FXML private VBox leaderboardContainer;

    @FXML
    public void initialize() {
        Map<String, Integer> fatRankings = recipeRepository.getCookingFatRankings();
        leaderboardContainer.getChildren().clear();

        if (fatRankings != null) {
            for (Map.Entry<String, Integer> entry : fatRankings.entrySet()) {
                HBox row = new HBox();
                row.setSpacing(20);
                row.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
                row.setStyle("-fx-padding: 10; -fx-background-color: rgba(255,255,255,0.5); -fx-background-radius: 6;");

                Label fatName = new Label(entry.getKey());
                fatName.setPrefWidth(120);
                fatName.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #3b2314;");

                Label countLabel = new Label(entry.getValue() + " Recipes");
                countLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #5c3a21;");

                row.getChildren().addAll(fatName, countLabel);
                leaderboardContainer.getChildren().add(row);
            }
        }
    }
}