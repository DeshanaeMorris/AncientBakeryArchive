package com.ancientbakery.ancientbakeryarchive;

import com.ancientbakery.ancientbakeryarchive.model.Recipe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ContentsController {

    @FXML
    public void goAncient(ActionEvent event) {
        try {
            System.out.println("Ancient clicked!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ancientbakery/ancientbakeryarchive/fxml/ancient-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading Ancient: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void goMedieval(ActionEvent event) {
        try {
            System.out.println("Medieval clicked!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ancientbakery/ancientbakeryarchive/fxml/medieval-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading Medieval: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void goRenaissance(ActionEvent event) {
        try {
            System.out.println("Renaissance clicked!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ancientbakery/ancientbakeryarchive/fxml/renaissance-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading Renaissance: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void goIndustrial(ActionEvent event) {
        try {
            System.out.println("Industrial clicked!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ancientbakery/ancientbakeryarchive/fxml/industrial-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading Industrial: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void go20thCent(ActionEvent event) {
        try {
            System.out.println("20th Century clicked!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ancientbakery/ancientbakeryarchive/fxml/century20th-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading 20th Century: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void goModern(ActionEvent event) {
        try {
            System.out.println("Modern clicked!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ancientbakery/ancientbakeryarchive/fxml/modern-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading Modern: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void goToPantry(ActionEvent event) {
        try {
            System.out.println("Pantry clicked!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ancientbakery/ancientbakeryarchive/fxml/pantry-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e){
            System.out.println("Error loading Pantry Matcher: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private TextField searchField;

    @FXML
    private ListView<String> searchResultsList;

    private final Map<String, Integer> searchResultMap = new LinkedHashMap<>();

    @FXML
    private void handleSearch(ActionEvent event) {
        System.out.println("handleSearch triggered, searchResultsList is: " + searchResultsList);

        String query = searchField.getText().trim().toLowerCase();

        System.out.println("Searching for: " + query);

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

        System.out.println("Generated SQL: " + sql);

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

            if (!hasResults) {
                searchResultsList.getItems().add("No results found.");
            }

        } catch (SQLException e) {
            System.out.println("Search error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        searchResultsList.setOnMouseClicked(event -> {
            String selected = searchResultsList.getSelectionModel().getSelectedItem();
            if (selected == null || !searchResultMap.containsKey(selected)) return;

            int recipeId = searchResultMap.get(selected);
            AppState.selectRecipe(recipeId);
            navigateToRecipeEra(recipeId, event);
        });
    }

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
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.out.println("Error navigating to recipe era: " + e.getMessage());
            e.printStackTrace();
        }
    }
}