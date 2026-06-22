package com.ancientbakery.ancientbakeryarchive;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class StartController {

    @FXML
    private Button welcomeButton;

    @FXML
    private void onWelcomeClick() {
        try {
            // Table of Contents page
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/ancientbakery/ancientbakeryarchive/fxml/contents-view.fxml"));
            Parent root = loader.load();

            // Get the current stage and set the new scene
            Stage stage = (Stage) welcomeButton.getScene().getWindow();
            BaseNavigator.setResponsiveScene(stage, root);
            stage.setTitle("Ancient Bakery Archive — Table of Contents");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading contents page: " + e.getMessage());
        }
    }
}
