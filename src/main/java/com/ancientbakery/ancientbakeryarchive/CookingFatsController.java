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

    @FXML
    private VBox leaderboardContainer;

    @FXML
    public void initialize() {
        Map<String, Integer> fatRankings = recipeRepository.getCookingFatRankings();
        leaderboardContainer.getChildren().clear();

        if (fatRankings != null) {
            int displayRank =1;
            int previousValue = -1;
            int index = 0;

            for (Map.Entry<String, Integer> entry : fatRankings.entrySet()) {
                String currentFatName = entry.getKey();

                int currentValue = entry.getValue();
                if (index > 0 && currentValue < previousValue) {
                    displayRank = index + 1;
                }
                previousValue = currentValue;
                index++;

                // Vertical container for item
                VBox fatItemContainer = new VBox(5);
                fatItemContainer.setStyle("-fx-padding: 10; -fx-background-color: rgba(255,255,255,0.5); -fx-background-radius: 6;");

                // horizontal row (The clickable part)
                HBox mainRow = new HBox(20);
                mainRow.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
                mainRow.setStyle("-fx-cursor: hand;"); // Makes the mouse cursor turn into a pointing hand on hover!

                Label fatName = new Label(displayRank + ". " + currentFatName);
                fatName.setPrefWidth(120);
                fatName.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #3b2314;");

                Label countLabel = new Label(entry.getValue() + " Recipes Total (Click to expand)");
                countLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #5c3a21; -fx-font-style: italic;");

                mainRow.getChildren().addAll(fatName, countLabel);
                fatItemContainer.getChildren().add(mainRow);

                // VBox container to hold the era labels
                VBox eraSubList = new VBox(5);
                eraSubList.setVisible(false);
                eraSubList.setManaged(false);

                // Fetch the era data from repository
                Map<String, Integer> eraBreakdown = recipeRepository.getEraBreakdownForFat(currentFatName);
                if (eraBreakdown != null) {
                    for (Map.Entry<String, Integer> eraEntry : eraBreakdown.entrySet()) {
                        Label eraLabel = new Label("   • " + eraEntry.getKey() + ": " + eraEntry.getValue() + " used");
                        eraLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #7a5335; -fx-font-style: italic;");
                        eraSubList.getChildren().add(eraLabel);
                    }
                }
                fatItemContainer.getChildren().add(eraSubList);


                mainRow.setOnMouseClicked(event -> {
                    boolean isExpanded = eraSubList.isVisible();

                    // Toggle values
                    eraSubList.setVisible(!isExpanded);
                    eraSubList.setManaged(!isExpanded);

                    // Update text helper
                    if (!isExpanded) {
                        countLabel.setText(entry.getValue() + " Recipes Total (Click to collapse)");
                        mainRow.setStyle("-fx-background-color: rgba(92, 58, 33, 0.1);");
                    } else {
                        countLabel.setText(entry.getValue() + " Recipes Total (Click to expand)");
                        mainRow.setStyle("-fx-background-color: transparent;");
                    }
                });

                // User can click ti expand/collapse
                mainRow.setOnMouseEntered(e -> mainRow.setStyle("-fx-background-color: rgba(92, 58, 33, 0.05);"));
                mainRow.setOnMouseExited(e -> {
                    if (!eraSubList.isVisible()) {
                        mainRow.setStyle("-fx-background-color: transparent;");
                    } else {
                        mainRow.setStyle("-fx-background-color: rgba(92, 58, 33, 0.1);");
                    }
                });

                leaderboardContainer.getChildren().add(fatItemContainer);
            }
        }
    }
}