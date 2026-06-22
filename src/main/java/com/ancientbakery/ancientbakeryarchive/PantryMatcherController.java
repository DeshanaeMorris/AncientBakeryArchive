package com.ancientbakery.ancientbakeryarchive;

import com.ancientbakery.ancientbakeryarchive.model.Era;
import com.ancientbakery.ancientbakeryarchive.model.Recipe;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.*;
import javafx.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.List;

public class PantryMatcherController extends BaseNavigator {

    @FXML
    private VBox ingredientCheckboxPane;
    @FXML
    private FlowPane recipeResultsPane;
    @FXML
    private Label matchCountLabel;

    private final RecipeRepository recipeRepository = new RecipeRepository();
    private final List<Integer> selectedIngredientIds = new ArrayList<>();

    @FXML
    public void initialize() {
        loadIngredients();
    }

    private void loadIngredients() {
        String sql = "SELECT id, name FROM Ingredients ORDER BY name";

        try (Connection connect = DatabaseManager.getConnection();
             PreparedStatement statement = connect.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");

                CheckBox cb = new CheckBox(name);
                cb.getStyleClass().add("ingredient-cb");
                cb.setOnAction(event -> {
                    if (cb.isSelected()) selectedIngredientIds.add(id);
                    else selectedIngredientIds.remove(Integer.valueOf(id));
                    refreshResults();
                });
                ingredientCheckboxPane.getChildren().add(cb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void refreshResults() {
        recipeResultsPane.getChildren().clear();

        if (selectedIngredientIds.isEmpty()) {
            matchCountLabel.setText("0 matches");
            return;
        }

        String placeholder = String.join(",", Collections.nCopies(selectedIngredientIds.size(), "?"));

        String sql =
                """
                SELECT r.id, r.name, r.era_id, COUNT(DISTINCT ri.Ingredients_ID) AS matched_count
                FROM Recipes r
                JOIN Recipe_Ingredients ri ON ri.Recipes_ID = r.id
                WHERE ri.Ingredients_ID IN (%s)
                GROUP BY r.id
                ORDER BY matched_count DESC, r.name
                """.formatted(placeholder);

        try (Connection connect = DatabaseManager.getConnection();
             PreparedStatement statement = connect.prepareStatement(sql)) {

            for (int i = 0; i < selectedIngredientIds.size(); i++) {
                statement.setInt(i + 1, selectedIngredientIds.get(i));
            }

            try (ResultSet result = statement.executeQuery()) {
                int count = 0;
                while (result.next()) {
                    Recipe recipe = new Recipe();
                    recipe.setId(result.getInt("id"));
                    recipe.setName(result.getString("name"));
                    recipe.setEraId(result.getInt("era_id"));
                    int matchedCount = result.getInt("matched_count");
                    recipeResultsPane.getChildren().add(buildRecipeCard(recipe, matchedCount));
                    count++;
                }
                matchCountLabel.setText(count + (count == 1 ? " match" : " matches"));

                if (count == 0){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("No Matches");
                    alert.setHeaderText(null);
                    alert.setContentText("None of the selected ingredients match a recipe in the archive. Try again.");
                    alert.setGraphic(null);

                    alert.getDialogPane().setStyle("-fx-background-color: #f7f1e3; -fx-font-family: 'Georgia';");

                    Label contentLabel = (Label) alert.getDialogPane().lookup(".content.label");
                    if (contentLabel != null) {
                        contentLabel.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 14px; -fx-text-fill: #2b1d12;");
                    }

                    alert.getDialogPane().getButtonTypes().forEach(type -> {
                        var button = alert.getDialogPane().lookupButton(type);
                        button.setStyle("-fx-background-color: #271d15; -fx-text-fill: white; -fx-font-family: 'Georgia'; -fx-background-radius: 6;");
                    });

                    alert.showAndWait();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Node buildRecipeCard(Recipe recipe, int matchedCount) {
        VBox card = new VBox(7);
        card.setPadding(new Insets(10));
        card.getStyleClass().add("recipe-card");

        Era era = recipeRepository.findEraById(recipe.getEraId());
        String eraName = era != null ? era.getName().toUpperCase() : "UNKNOWN ERA";

        Label eraLabel = new Label(eraName);
        eraLabel.getStyleClass().add("card-era");

        Label nameLabel = new Label(recipe.getName());
        nameLabel.getStyleClass().add("recipe-card-title");
        nameLabel.setWrapText(true);

        Label matchLabel = new Label(matchedCount + " ingredient" + (matchedCount == 1 ? "" : "s") + " matched");
        matchLabel.getStyleClass().add("recipe-meta");

        card.getChildren().addAll(eraLabel, nameLabel, matchLabel);
        card.setOnMouseClicked(e -> {
            AppState.selectRecipe(recipe.getId());
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(
                        "/com/ancientbakery/ancientbakeryarchive/fxml/bakenow-view.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) card.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        return card;
    }

    @FXML
    private void handleClear(ActionEvent event) {
        selectedIngredientIds.clear();
        ingredientCheckboxPane.getChildren().forEach(node -> {
            if (node instanceof CheckBox cb) cb.setSelected(false);
        });
        refreshResults();
    }

    @FXML
    private void goToContents(ActionEvent event) {
        goTo(event, "contents-view.fxml");
    }


}
