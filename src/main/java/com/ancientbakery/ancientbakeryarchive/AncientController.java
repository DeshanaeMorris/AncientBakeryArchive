package com.ancientbakery.ancientbakeryarchive;

import com.ancientbakery.ancientbakeryarchive.model.Recipe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class AncientController extends EraRecipeController {

    @FXML
    public TextField searchField;

    @FXML
    public ListView<String> searchResultsList;

    private final Map<String, Integer> searchResultMap = new LinkedHashMap<>();

    @Override
    protected int getEraId() {
        return 1;
    }

    @FXML
    public void initialize() {
        // Runs any default initialization set up by the parent class
        super.initialize();

        // Listen for when a user clicks a result in the sidebar search dropdown
        searchResultsList.setOnMouseClicked(event -> {
            String selected = searchResultsList.getSelectionModel().getSelectedItem();
            if (selected == null || !searchResultMap.containsKey(selected)) return;

            int recipeId = searchResultMap.get(selected);
            AppState.selectRecipe(recipeId);
            navigateToRecipeEra(recipeId, event);
        });
    }

    @FXML
    public void handleSearch(ActionEvent event) {
        String query = searchField.getText().trim().toLowerCase();

        if (query.isEmpty()) {
            searchResultsList.setVisible(false);
            searchResultsList.setManaged(false);
            return;
        }

        searchResultMap.clear();
        searchResultsList.getItems().clear();

        String sql = """
        SELECT DISTINCT r.id, r.name, e.name AS era_name
        FROM Recipes r
        JOIN Eras e ON r.era_id = e.id
        WHERE LOWER(r.name) LIKE ?
           OR LOWER(e.name) LIKE ?
           OR r.id IN (
               SELECT ri.Recipes_ID
               FROM Recipe_Ingredients ri
               JOIN Ingredients i ON i.id = ri.Ingredients_ID
               WHERE LOWER(i.name) LIKE ?
           )
        ORDER BY e.name, r.name
        """;

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            String pattern = "%" + query + "%";
            statement.setString(1, pattern);
            statement.setString(2, pattern);
            statement.setString(3, pattern);

            try (ResultSet result = statement.executeQuery()) {
                int rowCount = 0;
                while (result.next()) {
                    rowCount++;
                    String display = result.getString("name") + "  —  " + result.getString("era_name");
                    searchResultMap.put(display, result.getInt("id"));
                    searchResultsList.getItems().add(display);
                    System.out.println("Added to list: " + display);
                }
                System.out.println("Rows found: " + rowCount);
                System.out.println("ListView items size: " + searchResultsList.getItems().size());
            }

            searchResultsList.setVisible(true);
            searchResultsList.setManaged(true);

            boolean hasResults = !searchResultsList.getItems().isEmpty();
            searchResultsList.setVisible(hasResults);
            searchResultsList.setManaged(hasResults);

            int itemCount = searchResultsList.getItems().size();
            double rowHeight = 34;
            double calculatedHeight = Math.min(itemCount * rowHeight + 2, 220);
            searchResultsList.setPrefHeight(calculatedHeight);

            if (!hasResults) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("No Matches");
                alert.setHeaderText(null);
                alert.setContentText("No recipes, eras, or ingredients matched \"" + query + "\". ");
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

        } catch (SQLException e) {
            System.out.println("Search error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Handles the screen transition when clicking a search result in the sidebar
    private void navigateToRecipeEra(int recipeId, MouseEvent event) {
        RecipeRepository repo = new RecipeRepository();
        Recipe recipe = repo.findRecipeById(recipeId);

        if (recipe == null) return;

        String fxmlFile = switch (recipe.getEraId()) {
            case 1 -> "ancient-view.fxml";
            case 2 -> "medieval-view.fxml";
            case 3 -> "renaissance-view.fxml";
            case 4 -> "industrial-view.fxml";
            case 5 -> "century20th-view.fxml";
            case 6 -> "modern-view.fxml";
            default -> "contents-view.fxml";
        };

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/com/ancientbakery/ancientbakeryarchive/fxml/" + fxmlFile));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            BaseNavigator.setResponsiveScene(stage, root);
        } catch (IOException e) {
            System.out.println("Error navigating to recipe era: " + e.getMessage());
            e.printStackTrace();
        }
    }
}