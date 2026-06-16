package com.ancientbakery.ancientbakeryarchive;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AncientController {

    @FXML
    private Label recipeNameLabel;
    @FXML
    private Label recipeOriginalLabel;
    @FXML
    private Label recipeModernLabel;
    @FXML
    private ListView<String> recipeListView;
    @FXML
    private Label recipeTempLabel;
    @FXML
    private Label recipeSourceLabel;
    @FXML
    private VBox recipeDetailPane;

    @FXML
    public void initialize() {
        loadRecipeNames();
        recipeListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                handleRecipeClick();
            }
    });
    }

    private void loadRecipeNames() {
        ObservableList<String> names = FXCollections.observableArrayList();
        String query = "SELECT name FROM Recipes WHERE era_id = 1"; // 1 = Ancient

        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                names.add(rs.getString("name"));
            }
            recipeListView.setItems(names);

        } catch (SQLException e) {
            System.err.println("Database error loading names.");
            e.printStackTrace();
        }
    }

    @FXML
    public void handleRecipeClick() {
        String selectedName = recipeListView.getSelectionModel().getSelectedItem();
        if (selectedName == null) return;

        String query = "SELECT * FROM Recipes WHERE name LIKE ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, selectedName.trim() + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    recipeNameLabel.setText(rs.getString("name"));
                    recipeSourceLabel.setText("Source: " + rs.getString("sources"));
                    recipeTempLabel.setText("Temperature: " + rs.getString("temperature_description"));
                    recipeOriginalLabel.setText(rs.getString("original_text"));
                    recipeModernLabel.setText(rs.getString("modernized_text"));

                    recipeDetailPane.setVisible(true); // Reveals text blocks dynamically
                }
            }

        } catch (SQLException e) {
            System.err.println("Database error loading recipe details.");
            e.printStackTrace();
        }
    }

    @FXML
    public void goAncient(ActionEvent event) {
        System.out.println("Go Ancient");
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
    public void goBakeNow(ActionEvent event) {
        try {
            System.out.println("Bake Now clicked!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ancientbakery/ancientbakeryarchive/fxml/bakenow-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading Bake Now: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void handleRecipeClick(MouseEvent mouseEvent) {
    }
}
